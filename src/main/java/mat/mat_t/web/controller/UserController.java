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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "신규 회원가입")
    @GetMapping("/user/new")
    public String createForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "user/createForm";
    }

    @ApiOperation(value = "신규 회원가입")
    @PostMapping("user/new")
    public ResponseEntity<User> create(@Valid @RequestBody UserForm form, BindingResult bindingResult) {

        User user = new User(form.getLoginId(), form.getPassword(), form.getName(), form.getNickname(),
                form.getBirthDate(), form.getPhoneNumber(), form.getEmail(), form.getGender());

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(user);
        }
        userService.join(user);

        return ResponseEntity.ok().body(user);
    }

    @ApiOperation(value = "비밀번호 수정")
    @PatchMapping("user/editPwd")
    public ResponseEntity editPwd(HttpServletRequest request, @RequestParam("pwd") String pwd) {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        User updatedUser = userService.updatePwd(loginUser.getId(), pwd);
        return ResponseEntity.ok(updatedUser);
    }

    @ApiOperation(value = "닉네임 수정")
    @PatchMapping("user/editNickname")
    public ResponseEntity editNickname(HttpServletRequest request, @RequestParam("nickname") String nickname) {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        User updatedUser = userService.updateNickname(loginUser.getId(), nickname);
        return ResponseEntity.ok(updatedUser);
    }

    @ApiOperation(value = "회원 탈퇴")
    @DeleteMapping("/user/delete")
    public ResponseEntity deleteUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        userService.deleteUser(loginUser.getId());
        return ResponseEntity.ok(null);
    }

    @ApiOperation(value = "비밀번호 찾기")
    @PostMapping("/user/findPwd")
    public ResponseEntity findPasswordById(@RequestParam("loginId") String loginId, @RequestParam("email") String email) {
        User findUser = userService.findUserByLoginId(loginId);
        boolean getEmail = userService.userEmailCheck(findUser.getLoginId(), email);

        if (getEmail) {

        } else {

        }
        return ResponseEntity.ok().body(null);
    }

    @ApiOperation(value = "인증번호 확인후 비밀번호 수정")
    @PatchMapping("user/findPwd/editPwd")
    public ResponseEntity editPwdById(@RequestParam("id") Long id, @RequestParam("pwd") String pwd) {
        User findUser = userService.findById(id);

        User updatedUser = userService.updatePwd(findUser.getId(), pwd);
        return ResponseEntity.ok(updatedUser);
    }
}
