package com.linepro.modellbahn.security.user;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.linepro.modellbahn.configuration.UserMessage;

import lombok.RequiredArgsConstructor;

@Controller("UserController")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping({ "/about", "/about.*" })
    public String about() {
        return "about";
    }

    @GetMapping("/account")
    public ModelAndView account(Authentication authentication, HttpSession session) {
        return userService.get(authentication.getName(), authentication)
                          .map(user -> {
                              session.setAttribute("locale", user.getLocale());
                              return new ModelAndView("account", "user", user);
                          })
                          .orElse(new ModelAndView("login", HttpStatus.NOT_FOUND));
    }

    @PostMapping("/account")
    public ModelAndView account(@ModelAttribute("user") UserModel userModel, Model model, Authentication authentication, HttpSession session) {
        return new ModelAndView("account", "user", userService.update(authentication.getName(), userModel, authentication));
    }

    @GetMapping("/login")
    public ModelAndView login(ServletRequest request, Authentication authentication, HttpSession session) {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @GetMapping("/loginFailure")
    public ModelAndView login(Authentication authentication, HttpSession session) {
        ModelAndView mav = new ModelAndView("loginFailure");
        mav.addObject("error", true);
        return mav;
    }

    @GetMapping("/logoutSuccess")
    public ModelAndView logoutSuccess() {
        ModelAndView mav = new ModelAndView("logoutSuccess");
        mav.addObject("logout", true);
        return mav;
    }

    @GetMapping("/password")
    public ModelAndView changePassword(Authentication authentication, HttpSession session) {
        ModelAndView mav = new ModelAndView("password");
        mav.addObject("user", UserModel.builder().username(authentication.getName()).build());
        return mav;
    }

    @PatchMapping("/password")
    public ModelAndView changePassword(@RequestParam("password") String password, Authentication authentication, HttpSession session) {
        ModelAndView mav = new ModelAndView("password");
        mav.addObject("user", UserModel.builder().username(authentication.getName()).build());
        mav.addObject("message", userService.changePassword(authentication.getName(), password, authentication).getMessage());
        return mav;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserModel userModel, Model model, HttpSession session) {
        UserMessage message = userService.register(session, userModel);
        userModel.setPassword(null);
        model.addAttribute("user", userModel);
        model.addAttribute("message", message.getMessage());
        return "register";
    }

    @GetMapping("/confirm")
    public ModelAndView confirm(@RequestParam("token") String token) {
        UserMessage message = userService.confirmRegistration(token);

        if (message.getStatus() == HttpStatus.ACCEPTED.value()) {
            return new ModelAndView("login", "message", message.getMessage());
        }

        return new ModelAndView("confirm", "message", message.getMessage());
    }

    @GetMapping("/forgot")
    public String forgot() {
        return "forgot";
    }

    @PostMapping("/forgot")
    public ModelAndView forgot(@RequestParam("email") String email) {
        UserMessage message = userService.forgotPassword(email);
        return new ModelAndView("forgot", "message", message.getMessage());
    }

    @GetMapping("/reset")
    public String resetPassword() {
        return "reset";
    }

    @PostMapping("/reset")
    public ModelAndView resetPassword(@RequestParam("token") String token, @RequestParam("password") String password) {
        return new ModelAndView("reset", "message", userService.resetPassword(token, password));
    }
}
