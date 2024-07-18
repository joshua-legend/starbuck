package com.example.starbucks.controller;

import com.example.starbucks.dto.ApiResponse;
import com.example.starbucks.model.UserCustom;
import com.example.starbucks.service.UserDetailServiceImpl;
import com.example.starbucks.service.UserService;
import com.example.starbucks.status.ResponseStatus;
import com.example.starbucks.status.ResultStatus;
import com.example.starbucks.token.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserDetailServiceImpl userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> registerUser(@RequestBody UserCustom userCustom) {
        // 비밀번호 암호화 코드!
        userCustom.setPassword(passwordEncoder.encode(userCustom.getPassword()));
        // 저장
        userService.saveUser(userCustom);
        ApiResponse apiResponse = new ApiResponse(ResponseStatus.SUCCESS, "성공 했음", null);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody UserCustom userCustom) {
        //id와 pw를 체크해주는 security 라이브러리 활용해야함
        try {
            // userDetail에서 제공한 클래스임
            // db에서 쉽게 해당 id 가져오는 역할함
            UserDetails userDetails = userDetailService.loadUserByUsername(userCustom.getUserId());

            // 가져온 id가 null 이거나 post로 보낸 비번이랑 일치 하지 않으면 if 실행
            if(userDetails == null || !passwordEncoder.matches(userCustom.getPassword(), userDetails.getPassword())){
                throw new BadCredentialsException("그런 아이디 없거나 비번이 이상함 ㅅㄱ");
            }
            // 성공
            // 토큰 발급
            String token = JwtUtil.generateToken(userCustom);

            //response header
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", "Bearer " + token);

            // 출입증 객체
            Authentication authentication =  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            // 출입관리부에 작성
            // 카카오톡: 대화리스트화면[액티비티] + 상세친구대화톡방[액티비티]
            SecurityContextHolder.getContext().setAuthentication(authentication);


            //response body
            ApiResponse apiResponse = new ApiResponse(ResponseStatus.SUCCESS, "로그인 성공 했음", null);
            return ResponseEntity.ok().headers(httpHeaders).body(apiResponse);

        } catch (UsernameNotFoundException | BadCredentialsException e) {
            System.out.println("그런 아이디 없음 ㅅㄱ");
            ApiResponse apiResponse = new ApiResponse(ResponseStatus.UNAUTHORIZED, "님 누구임?", null);
            return ResponseEntity.ok(apiResponse);
        }
    }


    // ? wildcard -> any
    public ApiResponse<?> validateApiResponse(ResultStatus status) {
        ResponseStatus resultStatus = ResultStatus.FAIL.equals(status) ? ResponseStatus.FAIL : ResponseStatus.SUCCESS;
        String message = ResultStatus.FAIL.equals(status) ? "실패 했음" : "성공 했음";
        return new ApiResponse(resultStatus, message, null);
    }


}
