package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassStudents;
import mat.mat_t.form.ClassStudentsForm;
import mat.mat_t.web.service.ClassStudentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClassStudentsController {
    private final ClassStudentsService classStudentsService;

    @ApiOperation(value = "클래스스튜던트 저장")
    @PostMapping(value = "classStudents/save")
    public ResponseEntity<ClassStudents> createClassStudents(@Valid @RequestBody ClassStudentsForm form, BindingResult bindingResult) {
        ClassStudents classStudents = new ClassStudents(form);

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(classStudents);
        }
        classStudentsService.saveClassStudents(classStudents);
        return ResponseEntity.ok().body(classStudents);
    }

    @ApiOperation(value="클래스스튜던트 수정")
    @PatchMapping("classStudents/update")
    public ResponseEntity<ClassStudents> updateClassStudents(@Valid @RequestBody ClassStudentsForm form, Long ClassId) {
        ClassStudents classStudents=new ClassStudents(form.getStatus());
        classStudentsService.updateClassStudents(classStudents, ClassId);
        return ResponseEntity.ok().body(classStudents);
    }

    @ApiOperation(value="클래스스튜던트 삭제")
    @DeleteMapping("classStudents/delete")
    public ResponseEntity<ClassStudents> deleteClassStudents(@Valid @RequestBody ClassStudentsForm form, Long ClassId) {
        ClassStudents classStudents=new ClassStudents();
        classStudentsService.deleteClassStudents(ClassId);
        return ResponseEntity.ok().body(classStudents);
    }

    @ApiOperation(value = "클래스스튜던트 전체 조회")
    @GetMapping("classStudents/Lists")
    public ResponseEntity<List<ClassStudentsForm>> checkAllClassStudents() {
        List<ClassStudents> classStudents= classStudentsService.checkAll();;
        List<ClassStudentsForm> list = new ArrayList<>();
        classStudents.forEach(el -> {
            ClassStudentsForm classStudentsForm = new ClassStudentsForm(el);
            list.add(classStudentsForm);
        });
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = "수업리뷰 하나만 조회")
    @GetMapping("classStudents/{classId}")
    public ResponseEntity<List<ClassStudentsForm>> checkClassStudents(@PathVariable Long classId) {
        ClassStudents classStudents = classStudentsService.check(classId);
        List<ClassStudentsForm> list = new ArrayList<>();

        ClassStudentsForm data = new ClassStudentsForm(classStudents);
        list.add(data);
        return ResponseEntity.ok().body(list);
    }
}