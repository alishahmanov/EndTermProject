package controllers;

import controllers.interfaces.ILoginController;
import services.interfaces.ILoginService;

public class LoginController implements ILoginController {
    private final ILoginService loginService;

    public LoginController(ILoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public String login(String email, String password) {
        try {
            return loginService.login(email, password);
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
