//package com.nixsolutions.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class Index {
//
//    @GetMapping("/")
//    public String index() {
//        return "login";
//    }
//
//    @PostMapping("/")
//    public String indexPost(
//            @RequestParam(value = "error", required = false)
//                    String error, Model model) {
//        if (error != null) {
//            model.addAttribute("error", error);
//        }
//        return "login";
//    }
//}


