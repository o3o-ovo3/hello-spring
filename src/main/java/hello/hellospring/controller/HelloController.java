package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-string")
    @ResponseBody // http 프로토콜의 body 부에 데이터를 직접 넣어주겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // name 을 spring으로 했을 경우, hello spring으로 넘어간다.
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) { // 객체를 하나 만들어준다.
        Hello hello = new Hello(); // 객체를 생성한다.
        hello.setName(name); // 파라미터로 받아온 name을 객체의 name으로 설정해준다.
        // 처음으로 문자가 아닌 객체가 넘겨졌다.
        return hello;
    }

    static class Hello {
       // static 클래스를 만들면 클래스 안에서 이 클래스를 사용할 수 있다.
        private String name;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }
    }
}
