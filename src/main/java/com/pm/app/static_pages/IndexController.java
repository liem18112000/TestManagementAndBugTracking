package com.pm.app.static_pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(path="/", name="index")
    public String index(){
        return "index.html";
    }
}
