package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassStudents;
import mat.mat_t.domain.review.InstructorReview;
import mat.mat_t.domain.review.StudentReview;
import mat.mat_t.form.InstructorReviewForm;
import mat.mat_t.form.StudentReviewForm;
import mat.mat_t.web.service.ClassStudentsService;
import mat.mat_t.web.service.InstructorReviewService;
import mat.mat_t.web.service.StudentReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final StudentReviewService studentReviewService;
    private final InstructorReviewService instructorReviewService;
    private final ClassStudentsService classStudentsService;

    @ApiOperation(value = "선생 리뷰화면")
//    @GetMapping("/instructorReview")
    public String instructorReviewForm(Model model) {
        model.addAttribute("instructorReviewForm", new InstructorReviewForm());
        return "instructorReview/instructorReviewForm";
    }

    @ApiOperation(value = "선생 리뷰저장")
    @PostMapping("instructorReview/save")
    public ResponseEntity<InstructorReview> instructorReviewCreate(@Valid @RequestBody InstructorReviewForm form) {
        InstructorReview instructorReview = new InstructorReview(form.getReviewContent(), form.getScore());
        ClassStudents classStudents=new ClassStudents();
        instructorReviewService.saveReview(instructorReview);
        long insId=instructorReview.getInsReviewId();
        classStudentsService.matchReview(classStudents,form.getLoginId(),form.getClassId(),instructorReview.getId(insId));
        return ResponseEntity.ok().body(instructorReview);
    }

    @ApiOperation(value = "선생 리뷰조회")
    @GetMapping("instructorReview")
    public String instructorReviewCheck(Model model) {
        List reviews = instructorReviewService.checkReview();
        model.addAttribute("reviews", reviews);
        return "/reviewList";
    }

    @ApiOperation(value = "선생 리뷰수정")
    @PatchMapping("instructorReview/update")
    public ResponseEntity<InstructorReview> instructorReviewUpdate(@Valid @RequestBody InstructorReviewForm form, Long id) {
        InstructorReview instructorReview = new InstructorReview(form.getReviewContent(), form.getScore());
        instructorReviewService.updateReview(instructorReview, id);
        return ResponseEntity.ok().body(instructorReview);
    }

    @ApiOperation(value = "선생 리뷰삭제")
    @DeleteMapping("instructorReview/delete")
    public ResponseEntity<InstructorReview> instructorReviewDelete(@Valid @RequestBody InstructorReviewForm form, Long id) {
        InstructorReview instructorReview = new InstructorReview(form.getReviewContent(), form.getScore());
        instructorReviewService.deleteReview(id);
        return ResponseEntity.ok().body(instructorReview);
    }

    @ApiOperation(value = "학생리뷰화면")
//    @GetMapping("/studentReview")
    public String studentReviewForm(Model model) {
        model.addAttribute("studentReviewForm", new StudentReviewForm());
        return "studentReview/studentReviewForm";
    }

    @ApiOperation(value = "학생 리뷰저장")
    @PostMapping("studentReview/save")
    public ResponseEntity<StudentReview> studentReviewCreate(@Valid @RequestBody StudentReviewForm form) {
        StudentReview studentReview = new StudentReview(form.getMannerTemperature());
        studentReviewService.saveReview(studentReview);
        return ResponseEntity.ok().body(studentReview);
    }

    @ApiOperation(value = "학생 리뷰수정")
    @PatchMapping("studentReview/update")
    public ResponseEntity<StudentReview> studentReviewUpdate(@Valid @RequestBody StudentReviewForm form, Long id) {
        StudentReview studentReview = new StudentReview(form.getMannerTemperature());
        studentReviewService.updateReview(studentReview, id);
        return ResponseEntity.ok().body(studentReview);
    }

    @ApiOperation(value = "학생 리뷰삭제")
    @DeleteMapping("studentReview/delete")
    public ResponseEntity<StudentReview> studentReviewDelete(@Valid @RequestBody StudentReviewForm form, Long id) {
        StudentReview studentReview = new StudentReview(form.getMannerTemperature());
        studentReviewService.deleteReview(id);
        return ResponseEntity.ok().body(studentReview);
    }
}