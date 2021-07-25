package com.linepro.modellbahn.security.user;

import static com.linepro.modellbahn.security.WebSecurityConfig.ADMIN;
import static com.linepro.modellbahn.security.WebSecurityConfig.USER;

import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.linepro.modellbahn.controller.impl.ApiNames;
import com.linepro.modellbahn.io.ResourceEndpoints;

import lombok.RequiredArgsConstructor;

/**
 * Supplies the thymeleaf views for the user flows:
 * <ul>
 * <li>
 * <h3>Registration</h3></li>
 * <li>
 * <h3>Account</h3></li>
 * <li>
 * <h3>Forgot</h3></li>
 * <li>
 * <h3>Login</h3></li>
 * <li>
 * <h3>Logout</h3></li>
 * </ul>
 * @author JohnG
 */
@Controller("UserController")
@RequiredArgsConstructor
public class UserController {

    protected static final String ABOUT = "about";

    protected static final String ACCOUNT = "account";

    protected static final String CHANGE_PASSWORD = "changePassword";

    protected static final String CONFIRM_EMAIL_SENT = "confirmSent";

    protected static final String CONFIRM_REGISTRATION = "confirm";

    protected static final String FORGOT_PASSWORD = "forgot";

    protected static final String INDEX = "index";

    protected static final String LOGIN = "login";

    protected static final String LOGIN_FAILURE = "loginFailure";

    protected static final String LOGOUT = "logout";

    protected static final String LOGOUT_SUCCESS = "logoutSuccess";

    protected static final String REGISTER = "register";

    protected static final String RESET_EMAIL_SENT = "resetSent";

    protected static final String RESET_PASSWORD = "reset";

    public static final String HOME = "/";

    public static final String ABOUT_ENDPOINT = "/" + ABOUT;

    protected static final String ABOUT_PAGE = ABOUT_ENDPOINT + ".*";

    public static final String ACCOUNT_ENDPOINT = "/" + ACCOUNT;

    public static final String CHANGE_PASSWORD_ENDPOINT = "/" + CHANGE_PASSWORD;

    public static final String CONFIRM_REGISTRATION_ENDPOINT = "/" + CONFIRM_REGISTRATION;

    public static final String FORGOT_PASSWORD_ENDPOINT = "/" + FORGOT_PASSWORD;

    protected static final String INDEX_PAGE = "/" + INDEX + ".*";

    public static final String LOGIN_ENDPOINT = "/" + LOGIN;

    protected static final String LOGIN_PAGE = LOGIN_ENDPOINT + ".*";

    public static final String LOGIN_FAILURE_ENDPOINT = "/" + LOGIN_FAILURE;

    public static final String LOGOUT_ENDPOINT = "/" + LOGOUT;

    public static final String LOGOUT_SUCCESS_ENDPOINT = "/" + LOGOUT_SUCCESS;

    public static final String REGISTER_ENDPOINT = "/" + REGISTER;

    public static final String RESET_PASSWORD_ENDPOINT = "/" + RESET_PASSWORD;

    public static final String CONFIRM_ERROR = "error/confirm";

    public static final String LOGIN_ERROR = "error/login";

    public static final String RESET_ERROR = "error/reset";

    protected static final String EMAIL_PARAM = "email";

    protected static final String MESSAGE_DELIMITER = "\n";

    protected static final String PARAM_PASSWORD = "password";

    protected static final String PARAM_TOKEN = ApiNames.TOKEN;

    protected static final String PARAM_USER = "user";

    protected static final String VIEW_MESSAGES = "messages";

    protected static final String VIEW_TOKEN = "token";

    protected static final String VIEW_USER = "user";

    @Autowired
    private final UserService userService;

    private final ResourceEndpoints resourceEndpoints;

    @GetMapping({ HOME, INDEX_PAGE })
    public ModelAndView home() {
        return modelAndView(resourceEndpoints.getHomePageRedirect());
    }

    @GetMapping({ ABOUT_ENDPOINT, ABOUT_PAGE })
    public ModelAndView about() {
        return modelAndView(ABOUT);
    }

    /**
     * <h3>Registration</h3>
     * <ul>
     * <li><strong>GET</strong> /register
     * <ul>
     * <li>templates/register.html</li>
     * </ul>
     * </li>
     * </ul>
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping(REGISTER_ENDPOINT)
    public ModelAndView register() {
        return modelAndView(REGISTER);
    }

    /**
     * <h3>Registration</h3>
     * <ul>
     * <li><strong>POST</strong> /register [usermodel] -&gt;
     * <ul>
     * <li><strong>Error:</strong> templates/register.html (message=validation fails, preload)</li>
     * <li><strong>OK:</strong> templates/confirmSent.html (message=confirm)</li>
     * </ul>
     * </li>
     * </ul>
     */
    @PreAuthorize("isAnonymous()")
    @PostMapping(REGISTER_ENDPOINT)
    public ModelAndView register(@ModelAttribute(PARAM_USER) UserRequest request, HttpSession session) {
        UserResponse response = userService.register(session, request);

        return modelAndView(response, CONFIRM_EMAIL_SENT, REGISTER);
    }

    /**
     * <h3>Registration</h3>
     * <ul>
     * <li><strong>GET</strong> /confirm/{token} -&gt;
     * <ul>
     * <li><strong>Error:</strong> templates/error/confirm.html (message=invalid/expired)</li>
     * <li><strong>OK:</strong> templates/login.html (message, preload)</li>
     * </ul>
     * </li>
     * </ul>
     * </ul>
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping(CONFIRM_REGISTRATION_ENDPOINT)
    public ModelAndView confirm(@RequestParam(PARAM_TOKEN) String token) {
        return modelAndView(userService.confirmRegistration(token), LOGIN, CONFIRM_ERROR);
    }

    /**
     * <h3>Account</h3>
     * <ul>
     * <li><strong>GET</strong> /account [authorization] -&gt;
     * <ul>
     * <li><strong>Error:</strong> templates/account.html (message=validation fails/invalid, preload)</li>
     * <li><strong>OK:</strong> templates/account.html (preload)</li>
     * </ul>
     * </li>
     * </ul>
     * </ul>
     */
    @GetMapping(ACCOUNT_ENDPOINT)
    public ModelAndView account(Authentication authentication, HttpSession session) {
        ModelAndView mav = modelAndView(ACCOUNT);
        addUser(mav, userService.get(authentication.getName(), authentication)
                                .map(u -> setLocale(u, session))
                                .orElse(null));
        return mav;
    }

    /**
     * <h3>Account</h3>
     * <ul>
     * <li><strong>POST</strong> /account [authorization] -&gt;
     * <ul>
     * <li><strong>Error:</strong> templates/account.html (message=validation fails, preload)</li>
     * <li><strong>OK:</strong> templates/account.html (message=saved, preload)</li>
     * </ul>
     * </li>
     * </ul>
     * </ul>
     */
    @PostMapping(ACCOUNT_ENDPOINT)
    public ModelAndView account(@ModelAttribute(PARAM_USER) UserRequest request, Authentication authentication, HttpSession session) {
        return modelAndView(userService.update(authentication.getName(), request, session, authentication), ACCOUNT, ACCOUNT);
    }

    /**
     * <h3>Account</h3>
     * <ul>
     * <li><strong>POST</strong> /changePassword [authorization] -&gt;
     * <ul>
     * <li><strong>Error:</strong> templates/account.html (message=validation fails, preload)</li>
     * <li><strong>OK:</strong> templates/login.html (message=changed, preload)</li>
     * </ul>
     * </li>
     * </ul>
     * </ul>
     */
    @PostMapping(CHANGE_PASSWORD_ENDPOINT)
    public ModelAndView changePassword(@RequestParam(PARAM_PASSWORD) String password, Authentication authentication, HttpSession session) {
        return modelAndView(userService.changePassword(authentication.getName(), password, authentication), LOGIN, ACCOUNT);
    }

    /**
     * <h3>Login</h3>
     * <ul>
     * <li><strong>GET</strong> /login -&gt;
     * <ul>
     * <li><strong>Any:</strong> templates/login.html</li>
     * </ul>
     * </li>
     * </ul>
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping({ LOGIN_ENDPOINT, LOGIN_PAGE })
    public ModelAndView login(Authentication authentication) {
        return modelAndView(LOGIN);
    }

    /**
     * <h3>Login</h3>
     * <ul>
     * <li><strong>GET</strong> /loginFailure -&gt;
     * <ul>
     * <li><strong>Any:</strong> templates/error/login.html</li>
     * </ul>
     * </li>
     * </ul>
     */
    @GetMapping(LOGIN_FAILURE_ENDPOINT)
    public ModelAndView loginFailure() {
        return modelAndView(LOGIN_ERROR);
    }

    /**
     * <h3>Logout</h3>
     * <ul>
     * <li><strong>POST</strong> /logout -&gt;
     * <ul>
     * <li><strong>Any:</strong> templates/logoutSuccess.html (message)</li>
     * </ul>
     * </li>
     * </ul>
     * @author JohnG
     */
    @GetMapping(LOGOUT_SUCCESS_ENDPOINT)
    public ModelAndView logoutSuccess() {
        return modelAndView(LOGOUT_SUCCESS);
    }

    /**
     * <h3>Forgot</h3>
     * <ul>
     * <li><strong>GET</strong> /forgot -&gt;
     * <ul>
     * <li><strong>Any:</strong> templates/forgot.html</li>
     * </ul>
     * </ul>
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping(FORGOT_PASSWORD_ENDPOINT)
    public ModelAndView forgot() {
        return modelAndView(FORGOT_PASSWORD);
    }

    /**
     * <h3>Forgot</h3>
     * <ul>
     * <li><strong>POST</strong> /forgot [email] -&gt;
     * <ul>
     * <li><strong>Any:</strong> templates/resetSent.html (message)</li>
     * </ul>
     * </li>
     * </ul>
     */
    @PreAuthorize("isAnonymous()")
    @PostMapping(FORGOT_PASSWORD_ENDPOINT)
    public ModelAndView forgot(@RequestParam(EMAIL_PARAM) String email) {
        return modelAndView(userService.forgotPassword(email), RESET_EMAIL_SENT, RESET_EMAIL_SENT);
    }

    /**
     * <h3>Forgot</h3>
     * <ul>
     * <li><strong>GET </strong>/reset/{token} -&gt;
     * <ul>
     * <li><strong>Any: </strong>templates/reset.html (token)</li>
     * </ul>
     * </li>
     * </ul>
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping(RESET_PASSWORD_ENDPOINT)
    public ModelAndView reset(@RequestParam(PARAM_TOKEN) String token) {
        ModelAndView mav = modelAndView(RESET_PASSWORD);
        mav.addObject(VIEW_TOKEN, token);
        return mav;
    }

    /**
     * <h3>Forgot</h3>
     * <ul>
     * <li><strong>POST</strong> /reset/{token} [password] -&gt;
     * <ul>
     * <li><strong>Error:</strong> templates/error/reset.html (message)</li>
     * <li><strong>OK:</strong> templates/login.html (message, preload)</li>
     * </ul>
     * </li>
     * </ul>
     */
    @PreAuthorize("isAnonymous()")
    @PostMapping(RESET_PASSWORD_ENDPOINT)
    public ModelAndView resetPassword(@RequestParam(PARAM_TOKEN) String token, @RequestParam(PARAM_PASSWORD) String password) {
        return modelAndView(userService.resetPassword(token, password), LOGIN, RESET_ERROR);
    }

    protected UserModel setLocale(UserModel user, HttpSession session) {
        session.setAttribute("locale", user.getLocale());
        return user;
    }

    protected boolean isAuthenticated(Authentication authentication) {
        return authentication != null && authentication.getAuthorities().contains(ADMIN) || authentication.getAuthorities().contains(USER);
    }

    protected ModelAndView modelAndView(String viewName) {
        return new ModelAndView(viewName);
    }

    protected ModelAndView modelAndView(UserResponse response, String accepted, String rejected) {
        ModelAndView mav = modelAndView(response.isAccepted() ? accepted : rejected);
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

    protected ModelAndView addErrors(ModelAndView mav, ConstraintViolationException ex) {
        return addMessage(mav, ex.getConstraintViolations()
                                 .stream()
                                 .map(v -> v.getMessage())
                                 .collect(Collectors.joining(MESSAGE_DELIMITER)));
    }

    protected ModelAndView addMessage(ModelAndView mav, String message) {
        if (StringUtils.hasText(message)) {
            mav.addObject(VIEW_MESSAGES, message);
        }

        return mav;
    }
}
