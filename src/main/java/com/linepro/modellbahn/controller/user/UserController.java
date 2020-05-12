package com.linepro.modellbahn.controller.user;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.linepro.modellbahn.entity.User;
import com.linepro.modellbahn.service.impl.EmailService;
import com.linepro.modellbahn.service.impl.UserService;
import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

@Controller
public class UserController {

    protected static final String CONFIRM = "confirm";

    protected static final String PASSWORD = "password";

    protected static final String TOKEN = "token";

    protected static final String USER = "user";

    protected static final String REGISTER = "register";

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final EmailService emailService;

    @Autowired
    public UserController(PasswordEncoder bCryptPasswordEncoder, UserService userService, EmailService emailService) {

        this.passwordEncoder = bCryptPasswordEncoder;
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
            user.get().setPassword(passwordEncoder.encode(password));

            // Set user to enabled
            user.get().setEnabled(true);

            // Save user
            userService.saveUser(user.get());

            modelAndView.addObject("successMessage", "Your password has been set!");
        }
        return modelAndView;
    }
    
    // Display forgotPassword page
    @GetMapping(value = "/forgot")
    public ModelAndView displayForgotPasswordPage() {
        return new ModelAndView("forgotPassword");
    }
    
    // Process form submission from forgotPassword page
    @PostMapping(value = "/forgot")
    public ModelAndView processForgotPasswordForm(ModelAndView modelAndView, @RequestParam("email") String userEmail, HttpServletRequest request) {

        // Lookup user in database by e-mail
        Optional<User> optional = userService.findByEmail(userEmail);

        if (!optional.isPresent()) {
            modelAndView.addObject("errorMessage", "We didn't find an account for that e-mail address.");
        } else {
            
            // Generate random 36-character string token for reset password 
            User user = optional.get();
            user.setResetToken(UUID.randomUUID().toString());

            // Save token to database
            userService.saveUser(user);

            String appUrl = request.getScheme() + "://" + request.getServerName();
            
            // Email message
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("support@demo.com");
            passwordResetEmail.setTo(user.getEmail());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
                    + "/reset?token=" + user.getResetToken());
            
            emailService.sendEmail(passwordResetEmail);

            // Add success message to view
            modelAndView.addObject("successMessage", "A password reset link has been sent to " + userEmail);
        }

        modelAndView.setViewName("forgotPassword");
        return modelAndView;

    }

    // Display form to reset password
    @GetMapping(value = "/reset")
    public ModelAndView displayResetPasswordPage(ModelAndView modelAndView, @RequestParam("token") String token) {
        
        Optional<User> user = userService.findByResetToken(token);

        if (user.isPresent()) { // Token found in DB
            modelAndView.addObject("resetToken", token);
        } else { // Token not found in DB
            modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
        }

        modelAndView.setViewName("resetPassword");
        return modelAndView;
    }

    // Process reset password form
    @PostMapping(value = "/reset")
    public ModelAndView setNewPassword(ModelAndView modelAndView, @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {

        // Find the user associated with the reset token
        Optional<User> user = userService.findByResetToken(requestParams.get("token"));

        // This should always be non-null but we check just in case
        if (user.isPresent()) {
            
            User resetUser = user.get(); 
            
            // Set new password    
            resetUser.setPassword(passwordEncoder.encode(requestParams.get("password")));
            
            // Set the reset token to null so it cannot be used again
            resetUser.setResetToken(null);

            // Save user
            userService.saveUser(resetUser);

            // In order to set a model attribute on a redirect, we must use
            // RedirectAttributes
            redir.addFlashAttribute("successMessage", "You have successfully reset your password.  You may now login.");

            modelAndView.setViewName("redirect:login");
            return modelAndView;
            
        } else {
            modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
            modelAndView.setViewName("resetPassword");  
        }
        
        return modelAndView;
   }
   
    // Going to reset page without a token redirects to login page
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
        return new ModelAndView("redirect:login");
    }
}
