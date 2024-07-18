package com.example.starbucks;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //CORS: 교차 요청 안됨 원칙
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 주소 적용됨
                .allowedOrigins("http://localhost:3000") // 해당 주소에 된 사람 허용됨
                .allowedMethods("GET","POST","PUT","DELETE") // Https request 메소드 허용
                .allowedHeaders("*") // request의 모든 header 허용
                .exposedHeaders("Authorization") // 클라이언트가 Authorization은 볼수있게 해줌
                .allowCredentials(true); // 클라이언트가 서버에 접속 or 자원에 접근을 허용하는 증명
                //비밀번호 인증 or API KEY or Token or Cookie
    }
}
