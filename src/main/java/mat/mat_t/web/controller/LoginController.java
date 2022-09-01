package mat.mat_t.web.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.user.Gender;
import mat.mat_t.domain.user.Instructor;
import mat.mat_t.domain.user.User;
import mat.mat_t.form.LoginForm;
import mat.mat_t.web.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @ApiOperation(value = "로그인화면")
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login/loginForm";
    }

    @ApiOperation(value = "로그인 시 세션에 loginUser객체 저장")
    @PostMapping("/login")

    public ResponseEntity<?> login(@Valid @RequestBody LoginForm form, BindingResult bindingResult,

            HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }

        User loginUser = loginService.login(form.getLoginId(), form.getPassword());

        if (loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return ResponseEntity.badRequest().body(null);
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginUser", loginUser);

        LoginUserDto loginUserDto = new LoginUserDto(loginUser);

        return ResponseEntity.ok().body(loginUserDto);


    }

    @ApiOperation(value = "로그아웃 시 세션에서 loginUser객체 제거")
    @GetMapping("/logout")
    public ResponseEntity logoutV3(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return ResponseEntity.badRequest().body(null);
    }

    @Getter
    static class LoginUserDto {
        private Long studentId;
        private String loginId;
        private String password;
        private String name;
        private String nickname;
        private String birthDate;
        private String phoneNumber;
        private String email;
        private Gender gender;
        private Long instructorId;


        public LoginUserDto(User loginUser) {
            studentId = loginUser.getId();
            loginId = loginUser.getLoginId();
            password = loginUser.getPassword();
            name = loginUser.getName();
            nickname = loginUser.getNickname();
            birthDate = loginUser.getBirthDate();
            phoneNumber = loginUser.getPhoneNumber();
            email = loginUser.getEmail();
            gender = loginUser.getGender();

            if (loginUser.getInstructor()==null){
                instructorId = null;
            }else {
                instructorId = loginUser.getInstructor().getInstructorId();
            }


        }
    }
}
