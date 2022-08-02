package mat.mat_t.controller;


import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.user.User;
import mat.mat_t.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @GetMapping("/user/new")
    public String createForm(Model model) {

        model.addAttribute("userForm", new userForm());
        return "user/createForm";
    }

    @ApiOperation(value="신규 회원가입")
    @PostMapping("user/new")
    public String create(@Valid userForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "user/createForm";
        }

        User user = new User(form.getName(), form.getPassword(), form.getNickname(),
                form.getAge(), form.getPhoneNumber(), form.getEmail(), form.getGender());

        userService.join(user);

        return "redirect:/";
    }
}
