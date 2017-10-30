package co.kr.umbbalook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by whydda on 2017-08-02.
 */
@Controller
public class CommonController {

    @RequestMapping("/")
    public String index() {

        return "login";
    }

    // Login form
    @RequestMapping("/login/form")
    public String login(ModelMap model) {
        model.addAttribute("USER_ID", "whydda");
        model.addAttribute("USER_PASSWORD", "1234");
        return "login";
    }

    @RequestMapping("/login/pros")
    public String loginPros() {
        return "main";
    }

    // Login form with error
    @RequestMapping("/logout")
    public String loginError() {
        return "login";
    }
}
