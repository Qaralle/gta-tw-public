package itmo.p33101.gtav_territorywar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/application")
    public String forwardApp() {
        return "forward:/index.html";
    }

}