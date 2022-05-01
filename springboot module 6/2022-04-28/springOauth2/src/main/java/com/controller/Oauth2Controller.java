package com.controller;

import com.dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizedClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("oauth2-test")
public class Oauth2Controller {

    @RequestMapping("get-redirect")
    @ResponseBody
    public Object getRedirect(HttpServletRequest req) {
        return req.getRequestURI();
    }

    @GetMapping("signin")
    public String signin() {
        return "signin";
    }

    @PostMapping("signin")
    public String signinProcessing(UserDto userDto) {

        return "signin";
    }

    @ResponseBody
    @GetMapping("current-auth")
    public Object currentAuth(HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       Object oauth2Auth =  session.getAttribute(HttpSessionOAuth2AuthorizedClientRepository.class
                .getName() + ".AUTHORIZED_CLIENTS");
        return auth;
    }
}
