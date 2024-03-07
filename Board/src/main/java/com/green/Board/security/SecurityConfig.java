package com.green.Board.security;
import com.green.Board.vo.MemberVO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.AbstractSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//스프링 시큐리티 인증, 인가에 대한 프로세스를 정의
@EnableWebSecurity //<- 이 클래스가 시큐리티 설정파일임을 인지시켜주는 역할
@Configuration     //<- 객체 생성 어노테이션 (@Controller, @Service)
public class SecurityConfig {

    //암호화에 사용하는 객체 생성
    @Bean
    public BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }

    // @Bean : 객체 생성 어노테이션
    // 메소드의 실행 결과 리턴되는 데이터를 개체로 생성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security.csrf(AbstractHttpConfigurer::disable)
                //csrf 공격에 대한 방어를 해지 하겠다는 뜻

                .authorizeHttpRequests(
                //authorizeHttpRequests 메소드 안에서 인증 및 인가 관리
                        c -> {
                            c.requestMatchers(
                                    new AntPathRequestMatcher("/")
                                    , new AntPathRequestMatcher("/loginForm")
                                    , new AntPathRequestMatcher("/joinForm")
                                    , new AntPathRequestMatcher("/join")
                                    , new AntPathRequestMatcher("/login")
                            ).permitAll().anyRequest().authenticated();
                        }
                )

                .formLogin(
                // 로그인 form을 활용해서 할 것이고, 로그인 설정내용도 포함
                    formLogin -> {
                        formLogin.loginPage("/loginForm")
                        // 로그인 페이지 url 설정
                                .usernameParameter("memberId")
                                .passwordParameter("memberPw")
                                //로그인 시 전달되는 id 및 pw의 name 속성값을 지정
                        .loginProcessingUrl("/login")
                        // 로그인 기능이 실행되는 url
                        .defaultSuccessUrl("/")
                        // 로그인 성공 시 이동할 url
                        // 두번째 매개밴수로 true를 넣으면 항상 지정한 url로 이동, 두번째 매개변수 없을 시 이전 페이지이동
                        // 이전페이지가 없다면 지정한 url로 이동
                        .failureUrl("/loginForm");
//                      // 로그인 실패시 이동할 url
                    }
                )
                .logout(
                        logout -> {
                            logout.logoutUrl("/logout")
                            //해당 url 요청이 들어오면 시큐리티가 로그아웃 진행
                                    .logoutSuccessUrl("/")
                                    //로그아웃 성공시 보낼 url
                                    .invalidateHttpSession(true);
                                    //로그아웃 세션데이터 삭제
                        }
                );
        return security.build();
    }
            // memeber/* : ex - /member/a 하위폴더 안 파일만 선택가능
            // member/** : ex - /member/a/c 하위폴더 안 하위폴더에 파일 선택가능 (전체선택가능)

}
