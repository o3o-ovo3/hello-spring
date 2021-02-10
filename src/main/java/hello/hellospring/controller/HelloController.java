package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Controller 임을 알려줌
public class HelloController {

    @GetMapping("hello") // 웹 애플리케이션에서 /hello로 들어오면 이 메소드를 호출해준다.
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }
}
