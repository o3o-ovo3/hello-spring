package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // 첫 화면에서 home.html 이 실행되도록 함
    public String home(){
        return "home";
    }
}
