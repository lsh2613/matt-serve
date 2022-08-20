package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.Classes;
import mat.mat_t.form.ClassForm;
import mat.mat_t.web.service.ClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;

    @ApiOperation(value="신규 클래스 생성")
    @PostMapping(value = "/class/new")
    public ResponseEntity<Classes> createClass(@Valid @RequestBody ClassForm form1, BindingResult bindingResult) {

        Classes classes = new Classes(form1.getClassId(), form1.getInstructorId(), form1.getTitle(), form1.getNumberOfStudents(),form1.getDescriptions(), form1.getPlace(),
                form1.getStartTime(), form1.getEndTime(), form1.getDays(), form1.getCategory(), form1.getDate());

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(classes);
        }

        //classService.validateDuplicateClass(form1.getInstructorId()); // classinformation 지우면서 중복 검사를 할 필요가 없을것 같음.
        classService.saveClass(classes);
        return ResponseEntity.ok().body(classes);
    }



    /**클래스 수정**/
    //클래스 아이디, 강사아이디, 코드아이디는 변경안되게 함
    @ApiOperation(value="클래스 수정")
    @PatchMapping("/class/{classId}/edit")
    public ResponseEntity<Classes> updateClass(@Valid @RequestBody ClassForm form, Long ClassId) {
        Classes classes = new Classes(form.getClassId(), form.getTitle(), form.getNumberOfStudents(),form.getDescriptions(), form.getPlace(),
                form.getStartTime(), form.getEndTime(), form.getDays(), form.getCategory(), form.getDate());
        classService.updateClass(classes, ClassId);
        return ResponseEntity.ok().body(classes);
    }

    /** 전체 클래스 조회**/
    @ApiOperation(value="전체 클래스 조회")
    @GetMapping(value = "/class")
    public ResponseEntity<List<Classes>> findAll() {
        return ResponseEntity.ok(classService.findAllClass());
    }

    /** 클래스 아이디 조회**/
    @ApiOperation(value="클래스 아이디로 조회")
    @GetMapping("/class/{classId}")
    public ResponseEntity<Classes> findClassById( @PathVariable Long classId) {
        return ResponseEntity.ok().body(classService.findById(classId));
    }


    /**클래스 삭제**/
    @ApiOperation(value = "클래스 삭제")
    @DeleteMapping("class/delete")
    public ResponseEntity<Classes> DeleteClass(@Valid @RequestBody ClassForm form, Long classId) {
        Classes classes = new Classes(form.getClassId(), form.getTitle(), form.getNumberOfStudents(),form.getDescriptions(), form.getPlace(),
                form.getStartTime(), form.getEndTime(), form.getDays(), form.getCategory(), form.getDate());
        classService.deleteClass(classId);
        return ResponseEntity.ok().body(classes);
    }

}
