package com.wgc.shiro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShiroController {

    @GetMapping(value = "/login", produces = "application/json; charset=utf-8")
    public String login() {
        System.out.println("Sdfsdfs");
        return "你要去登陆";
    }

    @GetMapping(value = "/policy/view", produces = "application/json; charset=utf-8")
    public String policy() {
        return "自己都不知道做什么";
    }
}
