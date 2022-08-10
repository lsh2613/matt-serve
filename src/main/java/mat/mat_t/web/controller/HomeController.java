package mat.mat_t.web.controller;

import mat.mat_t.domain.user.User;
import mat.mat_t.form.UserForm;
import mat.mat_t.web.argumentresolver.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    //    @GetMapping("/")  //로그인 세션 관리 미완성이라 주석처리 해둠
    public String home(@Login User loginUser) {
        if (loginUser == null) {
            return "home";
        }
        return "loginHome";
    }

}