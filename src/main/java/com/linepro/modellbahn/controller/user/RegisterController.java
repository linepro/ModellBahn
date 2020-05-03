package com.linepro.modellbahn.controller.user;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.linepro.modellbahn.entity.User;
import com.linepro.modellbahn.service.EmailService;
import com.linepro.modellbahn.service.UserService;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

@Controller
public class RegisterController {

    protected static final String CONFIRM = "confirm";

    protected static final String PASSWORD = "password";

    protected static final String TOKEN = "token";

    protected static final String USER = "user";

    protected static final String REGISTER = "register";

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserService userService;

    private EmailService emailService;

    @Autowired
    public RegisterController(BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService,
            EmailService emailService) {

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
        this.emailService = emailService;
    }

    // Return registration form template
    @GetMapping(value = "/register")
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, User user) {
        modelAndView.addObject(USER, user);
        modelAndView.setViewName(REGISTER);
        return modelAndView;
    }

    // Process form input data
    @PostMapping(value = "/register")
    public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid User user,
            BindingResult bindingResult, HttpServletRequest request) {

        // Lookup user in database by e-mail
        Optional<User> userExists = userService.findByEmail(user.getEmail());

        if (userExists.isPresent()) {
            modelAndView.addObject("alreadyRegisteredMessage",
                    "Oops!  There is already a user registered with the email provided.");
            modelAndView.setViewName(REGISTER);
            bindingResult.reject("email");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(REGISTER);
        } else { // new user so we create user and send confirmation e-mail

            // Disable user until they click on confirmation link in email
            user.setEnabled(false);

            // Generate random 36-character string token for confirmation link
            user.setConfirmationToken(UUID.randomUUID().toString());

            userService.saveUser(user);

            String appUrl = request.getScheme() + "://" + request.getServerName();

            SimpleMailMessage registrationEmail = new SimpleMailMessage();
            registrationEmail.setTo(user.getEmail());
            registrationEmail.setSubject("Registration Confirmation");
            registrationEmail.setText("To confirm your e-mail address, please click the link below:\n" + appUrl
                    + "/confirm?token=" + user.getConfirmationToken());
            registrationEmail.setFrom("noreply@domain.com");

            emailService.sendEmail(registrationEmail);

            modelAndView.addObject("confirmationMessage", "A confirmation e-mail has been sent to " + user.getEmail());
            modelAndView.setViewName(REGISTER);
        }

        return modelAndView;
    }

    // Process confirmation link
    @GetMapping(value = "/confirm")
    public ModelAndView showConfirmationPage(ModelAndView modelAndView, @RequestParam(TOKEN) String token) {

        Optional<User> user = userService.findByConfirmationToken(token);

        if (!user.isPresent()) { // No token found in DB
            modelAndView.addObject("invalidToken", "Oops!  This is an invalid confirmation link.");
        } else { // Token found
            modelAndView.addObject("confirmationToken", user.get().getConfirmationToken());
        }

        modelAndView.setViewName(CONFIRM);
        return modelAndView;
    }

    // Process confirmation link
    @PostMapping(value = "/confirm")
    public ModelAndView processConfirmationForm(ModelAndView modelAndView, BindingResult bindingResult,
            @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {

        modelAndView.setViewName(CONFIRM);

        String password = requestParams.get(PASSWORD);
        String token = requestParams.get(TOKEN);

        Strength strength = new Zxcvbn().measure(password);
        if (strength.getScore() < 3) {
            bindingResult.reject(PASSWORD);

            redir.addFlashAttribute("errorMessage", "Your password is too weak.  Choose a stronger one.");

            modelAndView.setViewName("redirect:confirm?token=" + token);

            return modelAndView;
        }

        // Find the user associated with the reset token
        Optional<User> user = userService.findByConfirmationToken(token);

        if (user.isPresent()) {
            // Set new password
            user.get().setPassword(bCryptPasswordEncoder.encode(password));

            // Set user to enabled
            user.get().setEnabled(true);

            // Save user
            userService.saveUser(user.get());

            modelAndView.addObject("successMessage", "Your password has been set!");
        }
        return modelAndView;
    }

}
