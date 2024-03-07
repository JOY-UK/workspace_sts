package com.green.Board.security;
import com.green.Board.vo.MemberVO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//스프링 시큐리티 인증, 인가에 대한 프로세스를 정의
@EnableWebSecurity //<- 이 클래스가 시큐리티 설정파일임을 인지시켜주는 역할
@Configuration     //<- 객체 생성 어노테이션 (@Controller, @Service)
public class SecurityConfig {

    // @Bean : 객체 생성 어노테이션
    // 메소드의 실행 결과 리턴되는 데이터를 개체로 생성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        //csrf : csrf 공격에 대해 어떻게 대처할지 설정
        security.csrf(AbstractHttpConfigurer::disable) //메소드참조
                //아래 메소드 안에 인증, 인가에 대한 모든 설정 작성
                .authorizeHttpRequests(
                    c -> {
                        c.requestMatchers(
                                new AntPathRequestMatcher("/"),
                                new AntPathRequestMatcher("/loginForm"),
                                new AntPathRequestMatcher("/joinForm")
                        ).permitAll().anyRequest().authenticated();
                    }
                )
                //로그인 설정 + 로그인 form 방식으로 쓰겠다.
                .formLogin(
                    formLogin -> {
                        //로그인 페이지로 이동하는 url 설정
                        formLogin.loginPage("/loginForm")
                                //실제 로그인 기능을 실행하는 url 설정
                                .loginProcessingUrl("/login")
                                //로그인 성공 시 기본적으로 이동하는 페이지
                                .defaultSuccessUrl("/");
                    }
                );

        return security.build();
    }


}
