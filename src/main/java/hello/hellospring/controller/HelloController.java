package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // Controller 임을 알려줌
public class HelloController {

    @GetMapping("hello") // 웹 애플리케이션에서 /hello로 들어오면 이 메소드를 호출해준다.
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
                            // 외부에서 파라미터를 받을 것이다.
    public String helloMvc(@RequestParam("name") String name, Model model){
        // 파라미터로 받아온 name 값을 model에 담는다.
        model.addAttribute("name", name);
        return "hello-template";
    }
}
