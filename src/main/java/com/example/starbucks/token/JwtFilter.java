package com.example.starbucks.token;

import com.example.starbucks.service.UserDetailServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    private JwtUtil jwtUtil;
    private UserDetailServiceImpl userDetailService;


    public JwtFilter(JwtUtil jwtUtil, UserDetailServiceImpl userDetailService) {
        this.jwtUtil = jwtUtil;
        this.userDetailService = userDetailService;
    }

    // 스프링부트에 요청하면 무조건 걸치는 입구
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        //Bearer
        boolean headerIsEmpty = authorizationHeader == null;
        boolean doNotHaveBearer = !authorizationHeader.startsWith("Bearer");

        //token 아예 없음
        if(headerIsEmpty || doNotHaveBearer){
            return;
        }
        String token = authorizationHeader.substring(7);
        //token 유효기간 or 싸인불일치
        if(jwtUtil.validToken(token)){
            return;
        }
        // token 괜춘
        String name = jwtUtil.extractToken(token).getSubject();

        //
    }

}
