package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassStatus;
import mat.mat_t.domain.class_.ClassStudents;
import mat.mat_t.domain.review.InstructorReview;
import mat.mat_t.domain.review.StudentReview;
import mat.mat_t.form.ClassStudentsForm;
import mat.mat_t.form.StudentReviewForm;
import mat.mat_t.web.service.ClassStudentsService;
import mat.mat_t.web.service.InstructorReviewService;
import mat.mat_t.web.service.StudentReviewService;
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
    private final InstructorReviewService instructorReviewService;
    private final StudentReviewService studentReviewService;

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
    public ResponseEntity<ClassStudents> updateClassStudents(@Valid @RequestBody ClassStudentsForm form, Long cs_Id) {
        ClassStudents classStudents=new ClassStudents(form.getStatus());
        classStudentsService.updateClassStudents(classStudents, cs_Id);
        return ResponseEntity.ok().body(classStudents);
    }

    @ApiOperation(value="클래스스튜던트 삭제")
    @DeleteMapping("classStudents/delete")
    public ResponseEntity<ClassStudents> deleteClassStudents(@Valid @RequestBody ClassStudentsForm form, Long cs_Id,Long insRe_id,Long stRe_id) {
        ClassStudents classStudents=new ClassStudents();

        //review 값들 매핑정보 null 값으로 만드는거
        if(insRe_id!=null) {
            InstructorReview instructorReview=instructorReviewService.findByInsReviewId(insRe_id);
            instructorReview=instructorReviewService.deleteClassStudents(new ClassStudents(),instructorReview);
            instructorReviewService.deleteReview(insRe_id);
        }

        if(stRe_id!=null) {
            StudentReview studentReview = studentReviewService.findByStReId(stRe_id);
            studentReview=studentReviewService.deleteClassStudents(new ClassStudents(),studentReview);
            studentReviewService.deleteReview(stRe_id);
        }

        classStudentsService.deleteClassStudents(cs_Id);
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


    /**
     *  DOING,FINISHED 로 학생구분하는거
     */

    @ApiOperation(value="상태로 classStudents 조회")
    @GetMapping("/classStudents/{status}}")
    public ResponseEntity<List<ClassStudentsForm>> findClassStudentsBystatus(@PathVariable ClassStatus status) {
        List<ClassStudents> classStudents = new ArrayList<>();
        List<ClassStudentsForm> list = new ArrayList<>();

        classStudents = classStudentsService.findClassStudentsByStatus(status);
        classStudents.forEach(el -> {
            ClassStudentsForm data = new ClassStudentsForm(el);
            list.add(data);
        });

        return ResponseEntity.ok().body(list);
    }

}