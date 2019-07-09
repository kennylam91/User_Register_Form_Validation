package com.codegym.ums.controller;

import com.codegym.ums.model.User;
import com.codegym.ums.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/create")
    public ModelAndView getCreateForm(@Validated @ModelAttribute("user")User user,BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("/user/create");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createUser(@Validated @ModelAttribute User user, Pageable pageable, BindingResult bindingResult){
        new User().validate(user,bindingResult);
        if(bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("/user/create");
            return modelAndView;
        }
        else {
            ModelAndView modelAndView = new ModelAndView("redirect:/user/list");
            userService.save(user);
            return modelAndView;
        }
    }

    @GetMapping("/list")
    public ModelAndView listUser(@PageableDefault(size=3) Pageable pageable){
        ModelAndView modelAndView= new ModelAndView("/user/list");
        modelAndView.addObject("users",userService.findAll(pageable));
        return modelAndView;
    }
}