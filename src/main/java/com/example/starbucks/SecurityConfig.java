package com.example.starbucks;


import com.example.starbucks.token.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//spring mvc -> controller
//spring jpa -> repository
//spring security -> SecurityConfig 설정
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    // 패스워드 암호화 해주는 함수
    // qwer1234 -> abc!
    // qwer1234 -> bc12!
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //SecurityFilterChain -> Http 요청을 처리 할 때, 적용되는 보안필터 (인증, 권한부여 , 세션)
    //HttpSecurity -> Http 보안 구성해주는 객체 [권한 부여 규칙, Form 태그 설정, ...]
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        //csrf(사이트 간 요청 위조)
        http
            .csrf(x-> x.disable()) // csrf off 설정
            .sessionManagement(x-> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //세션 기반 안함
            .authorizeHttpRequests(x ->
                    x.requestMatchers("**").permitAll() // 누구든지 우리의 starbucks 모든 파일(**) 접근 가능(permitAll)
                     .anyRequest().authenticated())// 아무 request를 인증해야함
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
