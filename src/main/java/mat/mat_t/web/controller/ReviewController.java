package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.review.InstructorReview;
import mat.mat_t.domain.review.StudentReview;
import mat.mat_t.form.InstructorReviewForm;
import mat.mat_t.form.StudentReviewForm;
import mat.mat_t.web.service.ClassStudentsService;
import mat.mat_t.web.service.InstructorReviewService;
import mat.mat_t.web.service.StudentReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final StudentReviewService studentReviewService;
    private final InstructorReviewService instructorReviewService;
    private final ClassStudentsService classStudentsService;

    @ApiOperation(value = "수업 리뷰저장")
    @PostMapping("instructorReview/save")
    public ResponseEntity<InstructorReview> createInstructorReview(@Valid @RequestBody InstructorReviewForm form) {
        InstructorReview instructorReview = new InstructorReview(form.getReviewContent(), form.getScore());
        instructorReviewService.saveReview(instructorReview);
        long insId = instructorReview.getInsReviewId();
        classStudentsService.matchReview(form.getLoginId(), form.getClassId(), form.getClassStudentsId(), insId);

        return ResponseEntity.ok().body(instructorReview);
    }

    @ApiOperation(value = "수업 리뷰수정")
    @PatchMapping("instructorReview/update")
    public ResponseEntity<InstructorReview> updateInstructorReview(@Valid @RequestBody InstructorReviewForm form, Long id) {
        InstructorReview instructorReview = new InstructorReview(form.getReviewContent(), form.getScore());
        instructorReviewService.updateReview(instructorReview, id);
        return ResponseEntity.ok().body(instructorReview);
    }

    @ApiOperation(value = "수업 리뷰삭제")
    @DeleteMapping("instructorReview/delete")
    public ResponseEntity<InstructorReview> deleteInstructorReview(@Valid @RequestBody InstructorReviewForm form, Long id) {
        InstructorReview instructorReview = new InstructorReview(form.getReviewContent(), form.getScore());
        instructorReviewService.deleteReview(id);
        return ResponseEntity.ok().body(instructorReview);
    }

    @ApiOperation(value = "수업리뷰 전체 조회")
    @GetMapping("instructorReview/reviewLists")
    public ResponseEntity<List<InstructorReviewForm>> checkAllInstructorReviews() {
        List<InstructorReview> instructorReviews= instructorReviewService.checkAll();;
        List<InstructorReviewForm> list = new ArrayList<>();
        instructorReviews.forEach(el -> {
            InstructorReviewForm instructorReviewForm = new InstructorReviewForm(el);
            list.add(instructorReviewForm);
        });
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = "수업리뷰 하나만 조회")
    @GetMapping("instructorReview/{classId}")
    public ResponseEntity<List<InstructorReviewForm>> checkInstructorReview(@PathVariable Long classId) {
        InstructorReview instructorReview = instructorReviewService.check(classId);
        List<InstructorReviewForm> list = new ArrayList<>();

        InstructorReviewForm data = new InstructorReviewForm(instructorReview);
        list.add(data);
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = "학생 리뷰저장")
    @PostMapping("studentReview/save")
    public ResponseEntity<StudentReview> createStudentReview(@Valid @RequestBody StudentReviewForm form) {
        StudentReview studentReview = new StudentReview(form.getMannerTemperature());
        studentReviewService.saveReview(studentReview);
        return ResponseEntity.ok().body(studentReview);
    }

    @ApiOperation(value = "학생 리뷰수정")
    @PatchMapping("studentReview/update")
    public ResponseEntity<StudentReview> updateStudentReview(@Valid @RequestBody StudentReviewForm form, Long id) {
        StudentReview studentReview = new StudentReview(form.getMannerTemperature());
        studentReviewService.updateReview(studentReview, id);
        return ResponseEntity.ok().body(studentReview);
    }

    @ApiOperation(value = "학생 리뷰삭제")
    @DeleteMapping("studentReview/delete")
    public ResponseEntity<StudentReview> deleteStudentReview(@Valid @RequestBody StudentReviewForm form, Long id) {
        StudentReview studentReview = new StudentReview(form.getMannerTemperature());
        studentReviewService.deleteReview(id);
        return ResponseEntity.ok().body(studentReview);
    }

    @ApiOperation(value = "학생 리뷰전체 조회")
    @GetMapping("studentReview/reviewLists")
    public ResponseEntity<List<StudentReviewForm>> checkAllStudentReviews() {
        List<StudentReview> studentReviews=studentReviewService.checkAll();
        List<StudentReviewForm> list = new ArrayList<>();
        studentReviews.forEach(el -> {
            StudentReviewForm studentReviewForm = new StudentReviewForm(el);
            list.add(studentReviewForm);
        });
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = "학생리뷰 하나만 조회")
    @GetMapping("studentReview/{classId}")
    public ResponseEntity<List<StudentReviewForm>> checkStudentReview(@PathVariable Long classId) {
        StudentReview studentReview=studentReviewService.check(classId);
        List<StudentReviewForm> list = new ArrayList<>();
        StudentReviewForm data = new StudentReviewForm(studentReview);
        list.add(data);
        return ResponseEntity.ok().body(list);
    }
}