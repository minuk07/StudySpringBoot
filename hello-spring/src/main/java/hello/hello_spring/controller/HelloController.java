package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("data", "Hello!!");
        //스프링부트가 템플릿엔진(Thymeleaf)를 통해 templates 폴더에서 해당하는 view 파일(hello.html)을 찾음.
        //data에 Hello! 랜더링
        return "hello";
        //컨트롤러에서 문자열("hello")를 리턴하면 스프링부트가 hello를 뷰 이름으로 간주.
    }

    @GetMapping("/hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template"; //hello-template.html을 찾아감. (templates 폴더안에)
    }

    @GetMapping("hello-string")
    @ResponseBody //http의 response Body에 return 내용을 직접 넣어주겠다.
    public String helloString(
            @RequestParam("name") String name
    ){
        return "hello " + name; //hello name이 client에게 문자가 그대로 내려감.
    }

    @GetMapping("/hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);

        return hello; //객체를 넘김. -> Json으로 반환.
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
