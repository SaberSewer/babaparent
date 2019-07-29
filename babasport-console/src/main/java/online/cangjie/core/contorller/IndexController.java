package online.cangjie.core.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping(value = "index")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "main")
    public String main() {
        return "main";
    }

    @RequestMapping(value = "left")
    public String left() {
        return "left";
    }

    @RequestMapping(value = "right")
    public String right() {
        return "right";
    }
    @RequestMapping(value = "top")
    public String top(){
        return "top";
    }
}
