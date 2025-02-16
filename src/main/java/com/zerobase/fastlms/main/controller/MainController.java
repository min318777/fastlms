package com.zerobase.fastlms.main.controller;


// MainPage 클래스를 만든 목적!!
// 매핑하기 위해서
// 주소와(논리적인주소인터넷주소) 물리적인파일(프로그래밍을 해야하니까) 매핑

// https://www.naver.com/new/list.do
// 하나의 주소에 대해서
// 어디서 머팽? 누가 매핑?
// 후보군? 클래스, 속성, 메소드
// http://localhost:8080/

import com.zerobase.fastlms.component.MailComponent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.PrintWriter;


@Controller
@RequiredArgsConstructor
public class MainController {

    private final MailComponent mailComponent;

    @RequestMapping("/")
    public String index(){

        /*
        String mail = "min318777@naver.com";
        String subject = "안녕하세요.";
        String text = "<p> 안녕하세요.</p><p>반갑습니다.</p>";
        mailComponent.sendMail(mail, subject, text);
        */

        return "index";
    }

    // request -> WEB -> server로 보내는 객체
    // response -> server -> WEB으로 보내는 객체
    @RequestMapping("/hello")
    public void hello(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter printWriter = response.getWriter();

        String msg = "hello world";
        printWriter.write(msg);
        printWriter.close();
    }


}
