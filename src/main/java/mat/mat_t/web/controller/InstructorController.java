package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.user.Instructor;
import mat.mat_t.domain.user.User;
import mat.mat_t.form.InstructorForm;
import mat.mat_t.web.service.InstructorService;
import mat.mat_t.web.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;
    private final UserService userService;
    /**
     * 강사 테이블 생성 후 유저 테이블의 강사 번호를 update 해줘야함
     * 강사와 유저 데이터가 매핑 된 후에 조회 가능함
     * 유저에 강사번호가 남아있을시 강사 테이블 삭제 안됨
     * **/


    /**강사 생성**/
    @ApiOperation(value="강사 생성")
    @PostMapping(value = "/instructor/new")
    public ResponseEntity<Instructor> createInstructor(@Valid @RequestBody InstructorForm form, BindingResult bindingResult, HttpServletRequest request) {

        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        Instructor instructor = new Instructor(form);
        User findUser = userService.findById(loginUser.getId());
        findUser.setInstructor(instructor);

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(instructor);
        }

        instructorService.saveInstructor(instructor);
        return ResponseEntity.ok().body(instructor);
    }

    /**강사 수정**/
    @ApiOperation(value="강사 수정")
    @PatchMapping("/instructor/{instructorId}/edit")
    public ResponseEntity<Instructor> updateInstructor(@Valid @RequestBody InstructorForm form, Long instructorId) {
        Instructor instructor = new Instructor(form.getInstructorId(), form.getMajor());
        instructorService.updateInstructor(instructor, instructorId);
        return ResponseEntity.ok().body(instructor);
    }

    /**강사 조회**/
    @ApiOperation(value="강사 조회")
    @GetMapping(value = "/instructor")
    public ResponseEntity<List<InstructorForm>> findAll() {
        List<InstructorForm> list = new ArrayList<>();
        List<Instructor> instructors = new ArrayList<>();

        instructors = instructorService.findAllInstructor();
        instructors.forEach(el -> {
            InstructorForm data = new InstructorForm(el);
            list.add(data);
        });
        return ResponseEntity.ok().body(list);
    }

    /** 강사 아이디로 조회**/
    @ApiOperation(value="강사 아이디로 조회")
    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<InstructorForm>> findInstructorById( @PathVariable Long instructorId) {
        Instructor instructor = new Instructor();
        List<InstructorForm> list = new ArrayList<>();

        instructor = instructorService.findById(instructorId);
        InstructorForm data = new InstructorForm(instructor);
        list.add(data);
        return ResponseEntity.ok().body(list);
    }

    /**강사 삭제**/
    @ApiOperation(value = "강사 삭제")
    @DeleteMapping("/instructor/delete")
    public ResponseEntity<Instructor> deleteClassInfo(@Valid @RequestBody InstructorForm form, Long instructorId) {
        Instructor instructor = new Instructor(form);
        instructorService.deleteInstructor(instructorId);
        return ResponseEntity.ok().body(instructor);
    }
}
