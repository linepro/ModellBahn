package com.linepro.modellbahn.security.user;

import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.linepro.modellbahn.io.ResourceEndpoints;

import lombok.RequiredArgsConstructor;

/**
 * Supplies the thymeleaf views for the user flows:
 * 
 * <ul>
 * <li><h3>Registration</h3></li>
 * <li><h3>Account</h3></li>
 * <li><h3>Forgot</h3></li>
 * <li><h3>Login</h3></li>
 * <li><h3>Logout</h3></li>
 * </ul>
 * 
 * @author JohnG
 */
@Controller("UserController")
@RequiredArgsConstructor
public class UserController {

    protected static final String MESSAGE_DELIMITER = "\n";
    protected static final String PARAM_PASSWORD = "password";
    protected static final String PARAM_TOKEN = "token";
    protected static final String PARAM_USER = "user";

    protected static final String VIEW_MESSAGES = "messages";
    protected static final String VIEW_TOKEN = "token";
    protected static final String VIEW_USER = "user";

    @Autowired
    private final UserService userService;

    private final ResourceEndpoints resourceEndpoints;
    
    @GetMapping({ "/", "/index.*" })
    public String home() {
        return resourceEndpoints.getHomePage();
    }

    @GetMapping({ "/about", "/about.*" })
    public String about() {
        return "about";
    }

    /**
     * <h3>Registration</h3>
     * <ul>
     *   <li><strong>GET</strong> /register
     *     <ul>
     *       <li>templates/register.html</li>
     *     </ul>
     *   </li>
     * </ul>
     */
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * <h3>Registration</h3>
     * <ul>
     *   <li><strong>POST</strong> /register [usermodel] -&gt;
     *     <ul>
     *       <li><strong>Error:</strong> templates/register.html (message=validation fails, preload)</li>
     *       <li><strong>OK:</strong> templates/confirmSent.html (message=confirm)</li>
     *     </ul>
     *   </li>
     * </ul>
     */
    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute(PARAM_USER) UserModel userModel, HttpSession session) {
        UserResponse response = userService.register(session, userModel);

        return modelAndView(response, "confirmSent", "register");
    }

    /**
     * <h3>Registration</h3>
     * <ul>
     *   <li><strong>GET</strong> /confirm/{token} -&gt;
     *     <ul>
     *       <li><strong>Error:</strong> templates/error/confirm.html (message=invalid/expired)</li>
     *       <li><strong>OK:</strong> templates/login.html (message, preload)</li>
     *     </ul>
     *   </li>
     * </ul>
     * </ul>
     */
    @GetMapping("/confirm")
    public ModelAndView confirm(@RequestParam(PARAM_TOKEN) String token) {
        UserResponse response = userService.confirmRegistration(token);

        return modelAndView(response, "login", "error/confirm");
    }

    /**
     * <h3>Account</h3>
     * <ul>
     *   <li><strong>GET</strong> /account [authorization] -&gt;
     *     <ul>
     *       <li><strong>Error:</strong> templates/account.html (message=validation fails/invalid, preload)</li>
     *       <li><strong>OK:</strong> templates/account.html (preload)</li>
     *     </ul>
     *   </li>
     * </ul>
     * </ul>
     */
    @GetMapping("/account")
    public ModelAndView account(Authentication authentication, HttpSession session) {
        return userService.get(authentication.getName(), authentication)
                          .map(user -> {
                              session.setAttribute("locale", user.getLocale());

                              return new ModelAndView("account", VIEW_USER, user);
                          })
                          .orElse(new ModelAndView("account", HttpStatus.NOT_FOUND));
    }

    /**
     * <h3>Account</h3>
     * <ul>
     *   <li><strong>POST</strong> /account [authorization] -&gt;
     *     <ul>
     *       <li><strong>Error:</strong> templates/account.html (message=validation fails, preload)</li>
     *       <li><strong>OK:</strong> templates/account.html (message=saved, preload)</li>
     *     </ul>
     *   </li>
     * </ul>
     * </ul>
     */
    @PostMapping("/account")
    public ModelAndView account(@ModelAttribute(PARAM_USER) UserModel userModel, Authentication authentication, HttpSession session) {
        ModelAndView mav = new ModelAndView("account");

        try {
            mav.addObject(VIEW_USER, userService.update(authentication.getName(), userModel, authentication).orElse(userModel));
        } catch (ConstraintViolationException ex) {
            addUser(mav, userModel);
            mav.addObject(VIEW_MESSAGES, ex.getConstraintViolations()
                                       .stream()
                                       .map(v -> v.getMessage())
                                       .collect(Collectors.joining(MESSAGE_DELIMITER)));
        }

        return mav;
    }

    /**
     * <h3>Account</h3>
     * <ul>
     *   <li><strong>POST</strong> /changePassword [authorization] -&gt;
     *     <ul>
     *       <li><strong>Error:</strong> templates/account.html (message=validation fails, preload)</li>
     *       <li><strong>OK:</strong> templates/account.html (message=saved, preload)</li>
     *     </ul>
     *   </li>
     * </ul>
     * </ul>
     */
    @PostMapping("/changePassword")
    public ModelAndView changePassword(@RequestParam(PARAM_PASSWORD) String password, Authentication authentication, HttpSession session) {
        ModelAndView mav = new ModelAndView("account");

        mav.addObject(VIEW_USER, userService.get(authentication.getName(), authentication).get());

        try {
            mav.addObject(VIEW_MESSAGES, userService.changePassword(authentication.getName(), password, authentication).getMessage());
        } catch (ConstraintViolationException ex) {
            mav.addObject(VIEW_MESSAGES, ex.getConstraintViolations()
                                       .stream()
                                       .map(v -> v.getMessage())
                                       .collect(Collectors.joining(MESSAGE_DELIMITER)));
        }

        return mav;
    }

    /**
     * <h3>Login</h3>
     * <ul>
     *   <li><strong>GET</strong> /login -&gt;
     *     <ul>
     *       <li><strong>Any:</strong> templates/login.html</li>
     *     </ul>
     *   </li>
     * </ul>
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * <h3>Login</h3>
     * <ul>
     *   <li><strong>GET</strong> /loginFailure -&gt;
     *     <ul>
     *       <li><strong>Any:</strong> templates/error/login.html</li>
     *     </ul>
     *   </li>
     * </ul>
     */
    @GetMapping("/loginFailure")
    public String loginFailure() {
        return "error/login";
    }

    /**
     * <h3>Logout</h3>
     * <ul>
     *   <li><strong>POST</strong> /logout [authorization] -&gt;
     *     <ul>
     *       <li><strong>Any:</strong> templates/logoutSuccess.html (message)</li>
     *     </ul>
     *   </li>
     * </ul>
     * 
     * @author JohnG
     */
    @GetMapping("/logoutSuccess")
    public String logoutSuccess() {
        return "logoutSuccess";
    }

    /**
     * <h3>Forgot</h3>
     * <ul>
     *   <li><strong>GET</strong> /forgot -&gt;
     *     <ul>
     *       <li><strong>Any:</strong> templates/forgot.html</li>
     *     </ul>
     * </ul>
     */
    @GetMapping("/forgot")
    public String forgot() {
        return "forgot";
    }

    /**
     * <h3>Forgot</h3>
     * <ul>
     *   <li><strong>POST</strong> /forgot [email] -&gt;
     *     <ul>
     *       <li><strong>Any:</strong> templates/resetSent.html (message)</li>
     *     </ul>
     *   </li>
     * </ul>
     */
    @PostMapping("/forgot")
    public ModelAndView forgot(@RequestParam("email") String email) {
        UserResponse response = userService.forgotPassword(email);

        return new ModelAndView("resetSent", VIEW_MESSAGES, response.getMessage());
    }

    /**
     * <h3>Forgot</h3>
     * <ul>
     *   <li><strong>GET </strong>/reset/{token} -&gt;
     *     <ul>
     *       <li><strong>Any: </strong>templates/reset.html (token)</li>
     *     </ul>
     *   </li>
     * </ul>
     */
    @GetMapping("/reset")
    public ModelAndView reset(@RequestParam(PARAM_TOKEN) String token) {
        return new ModelAndView("reset", VIEW_TOKEN, token);
    }

    /**
     * <h3>Forgot</h3>
     * <ul>
     *   <li><strong>POST</strong> /reset/{token} [password] -&gt;
     *     <ul>
     *       <li><strong>Error:</strong> templates/error/reset.html (message)</li>
     *       <li><strong>OK:</strong> templates/login.html (message, preload)</li>
     *     </ul>
     *   </li>
     * </ul>
     */
    @PostMapping("/reset")
    public ModelAndView resetPassword(@RequestParam(PARAM_TOKEN) String token, @RequestParam(PARAM_PASSWORD) String password) {
        UserResponse response = userService.resetPassword(token, password);

        return modelAndView(response, "login", "error/reset");
    }

    protected ModelAndView modelAndView(UserResponse response, String accepted, String rejected) {
        ModelAndView mav = new ModelAndView(response.isAccepted() ? accepted : rejected);
        addResponse(mav, response);
        return mav;
    }

    protected ModelAndView addResponse(ModelAndView mav, UserResponse response) {
        addMessage(mav, response.getMessage());
        return addUser(mav, response.getUser());
    }

    protected ModelAndView addUser(ModelAndView mav, UserModel userModel) {
        if (userModel != null) {
            userModel.setPassword(null);
            mav.addObject(VIEW_USER, userModel);
        }
        return mav;
    }

    protected ModelAndView addMessage(ModelAndView mav, String message) {
        if (StringUtils.hasText(message)) {
            mav.addObject(VIEW_MESSAGES, message);
        }
        return mav;
    }
}
