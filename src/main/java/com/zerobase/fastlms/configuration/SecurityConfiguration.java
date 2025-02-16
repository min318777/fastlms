package com.zerobase.fastlms.configuration;


import com.zerobase.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final MemberService memberService;

    @Bean
    UserAuthenticationFailureHandler getFailureHandler() {

        return new UserAuthenticationFailureHandler();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()  // 5.0 이후, 'authorizeRequests()'는 deprecated 됨
                .requestMatchers(
                        "/"
                        , "member/register"
                        , "member/email-auth"
                        )  // 모든 요청에 대해 접근을 허용
                .permitAll()
                .anyRequest().authenticated()  // 나머지 요청은 인증 필요
                .and()
                .formLogin()  // 로그인 설정
                .permitAll()  // 로그인 페이지에 대한 접근을 허용
                .and()
                .logout()  // 로그아웃 설정
                .permitAll();

        http.formLogin()
                .loginPage("/member/login")
                .failureHandler(getFailureHandler())
                .permitAll();
        return http.build();  // 필수! http 객체를 반환하여 SecurityFilterChain을 생성

    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(memberService)
                .passwordEncoder(getPasswordEncorder());
    }

    @Bean
    PasswordEncoder getPasswordEncorder() {

        return new BCryptPasswordEncoder();
    }
}
