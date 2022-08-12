package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.user.User;
import mat.mat_t.form.UserForm;
import mat.mat_t.web.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value="신규 회원가입")
    @GetMapping("/user/new")
    public String createForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "user/createForm";
    }

    @ApiOperation(value = "신규 회원가입")
    @PostMapping("user/new")
    public ResponseEntity<User> create(@Valid @RequestBody UserForm form, BindingResult bindingResult) {

        User user = new User(form.getLoginId(), form.getPassword(),form.getName(), form.getNickname(),
                form.getBirthDate(), form.getPhoneNumber(), form.getEmail(), form.getGender());

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(user);
        }
        userService.join(user);

        return ResponseEntity.ok().body(user);
    }
}
