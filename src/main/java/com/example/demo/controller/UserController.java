package com.example.demo.controller;


import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.model.User;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    public UserRepository userRepository;


    @Autowired
    public UserService userService;

    @GetMapping("/login")
    public String showLoginPage()
    {
        return "login";
    }

    @GetMapping("/success")
    public String showLoginPage1()
    {
        return "success";
    }

    @PostMapping("/saveUser")
    public String saveUser(@RequestParam String username, @RequestParam String password)
    {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        return "redirect:/login";

    }


    @PostMapping("/login")
    public ModelAndView login(@RequestParam String username, @RequestParam String password) {
        User user = userService.findUserByUsername(username);
        ModelAndView modelAndView = new ModelAndView();
        if (user != null && user.getPassword().equals(password)) {

            modelAndView.setViewName("redirect:/success");
        } else {

            modelAndView.setViewName("redirect:/login");
            System.out.println("Böyle bir kullanıcı yok");
        }
        return modelAndView;
    }
}
