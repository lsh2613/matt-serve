package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.user.Instructor;
import mat.mat_t.form.InstructorForm;
import mat.mat_t.web.service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    /**강사 생성**/
    @ApiOperation(value="강사 생성")
    @PostMapping(value = "/instructor/new")
    public ResponseEntity<Instructor> createInstructor(@Valid @RequestBody Instructor form, BindingResult bindingResult) {

        Instructor instructor = new Instructor(form.getInstructorId(), form.getMajor());

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
    public ResponseEntity<List<Instructor>> findAll() {
        return ResponseEntity.ok(instructorService.findAllInstructor());
    }

    /**강사 삭제**/
    @ApiOperation(value = "강사 삭제")
    @DeleteMapping("/instructor/delete")
    public ResponseEntity<Instructor> deleteClassInfo(@Valid @RequestBody InstructorForm form, Long instructorId) {
        Instructor instructor = new Instructor(form.getInstructorId(), form.getMajor());
        instructorService.deleteInstructor(instructorId);
        return ResponseEntity.ok().body(instructor);
    }
}
