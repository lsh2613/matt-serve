package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassStudents;
import mat.mat_t.domain.review.InstructorReview;
import mat.mat_t.domain.review.StudentReview;
import mat.mat_t.form.InstructorReviewForm;
import mat.mat_t.form.StudentReviewForm;
import mat.mat_t.web.service.ClassService;
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
    @PostMapping("instructor/review")
    public ResponseEntity<InstructorReview> createInstructorReview(@Valid @RequestBody InstructorReviewForm form) {

        InstructorReview instructorReview = new InstructorReview(form.getReviewContent(), form.getScore());
        if(instructorReviewService.countInstructorReviews(form.getClassId(),form.getStudentId())==1){
            throw new IllegalStateException("이미 등록되어 있습니다.");
        }

        instructorReviewService.saveReview(instructorReview);

        ClassStudents student = classStudentsService.findByUserIdAndClassId(form.getStudentId(), form.getClassId());
        instructorReview = instructorReviewService.updateClassStudents(student, instructorReview);
        return ResponseEntity.ok().body(instructorReview);
    }

    @ApiOperation(value = "수업 리뷰수정")
    @PatchMapping("instructor/review")
    public ResponseEntity<InstructorReview> updateInstructorReview(@Valid @RequestBody InstructorReviewForm form, Long id) {
        InstructorReview instructorReview = new InstructorReview(form.getReviewContent(), form.getScore());
        instructorReviewService.updateInstructorReview(instructorReview, id);
        return ResponseEntity.ok().body(instructorReview);
    }

    @ApiOperation(value = "수업 리뷰삭제")
    @DeleteMapping("instructor/review")
    public ResponseEntity<InstructorReview> deleteInstructorReview(@Valid @RequestBody InstructorReviewForm form,
                                                                   Long id) {
        InstructorReview instructorReview = new InstructorReview(form.getReviewContent(), form.getScore());
        instructorReviewService.deleteReview(id);
        return ResponseEntity.ok().body(instructorReview);
    }

    @ApiOperation(value = "수업리뷰 전체 조회")
    @GetMapping("instructor/review/all")
    public ResponseEntity<List<InstructorReviewForm>> checkAllInstructorReviews() {
        List<InstructorReview> instructorReviews = instructorReviewService.checkAll();
        ;
        List<InstructorReviewForm> list = new ArrayList<>();
        instructorReviews.forEach(el -> {
            InstructorReviewForm instructorReviewForm = new InstructorReviewForm(el);
            list.add(instructorReviewForm);
        });
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = "수업리뷰 하나만 조회")
    @GetMapping("instructor_review/{reviewId}")
    public ResponseEntity<List<InstructorReviewForm>> checkInstructorReview(@PathVariable Long reviewId) {
        InstructorReview instructorReview = instructorReviewService.check(reviewId);
        List<InstructorReviewForm> list = new ArrayList<>();

        InstructorReviewForm data = new InstructorReviewForm(instructorReview);
        list.add(data);
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = "학생 리뷰저장")
    @PostMapping("student/review")
    public ResponseEntity<StudentReview> createStudentReview(@Valid @RequestBody StudentReviewForm form) {
        StudentReview studentReview = new StudentReview(form.getMannerTemperature());
        if(studentReviewService.countStudentReviews(form.getClassId(),form.getStudentId())==1){
            throw new IllegalStateException("이미 등록되어 있습니다.");
        }
        studentReviewService.saveReview(studentReview);

        ClassStudents student = classStudentsService.findByUserIdAndClassId(form.getStudentId(), form.getClassId());
        studentReview = studentReviewService.updateClassStudents(student, studentReview);
        return ResponseEntity.ok().body(studentReview);
    }

    /**
     * 온도 평균
     */
    @ApiOperation(value = "몰라")
    @GetMapping("studentTemperatureReview/{id}")
    public ResponseEntity<Float> temperatureReview(@PathVariable Long id) {
        float averageTemperature=studentReviewService.averageTemperature(id);
        return ResponseEntity.ok().body(averageTemperature);
    }

    @ApiOperation(value = "학생 리뷰수정")
    @PatchMapping("student/review")
    public ResponseEntity<StudentReview> updateStudentReview(@Valid @RequestBody StudentReviewForm form, Long id) {
        StudentReview studentReview = new StudentReview(form.getMannerTemperature());
        studentReviewService.updateStudentReview(studentReview, id);
        return ResponseEntity.ok().body(studentReview);
    }

    @ApiOperation(value = "학생 리뷰삭제")
    @DeleteMapping("student/review")
    public ResponseEntity<StudentReview> deleteStudentReview(@Valid @RequestBody StudentReviewForm form, Long id) {
        StudentReview studentReview = new StudentReview(form.getMannerTemperature());
        studentReviewService.deleteReview(id);
        return ResponseEntity.ok().body(studentReview);
    }

    @ApiOperation(value = "학생 리뷰전체 조회")
    @GetMapping("student/reviews/all")
    public ResponseEntity<List<StudentReviewForm>> checkAllStudentReviews() {
        List<StudentReview> studentReviews = studentReviewService.checkAll();
        List<StudentReviewForm> list = new ArrayList<>();
        studentReviews.forEach(el -> {
            StudentReviewForm studentReviewForm = new StudentReviewForm(el);
            list.add(studentReviewForm);
        });
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = "학생리뷰 하나만 조회")
    @GetMapping("student/review/{reviewId}")
    public ResponseEntity<List<StudentReviewForm>> checkStudentReview(@PathVariable Long reviewId) {
        StudentReview studentReview = studentReviewService.check(reviewId);
        List<StudentReviewForm> list = new ArrayList<>();
        StudentReviewForm data = new StudentReviewForm(studentReview);
        list.add(data);
        return ResponseEntity.ok().body(list);
    }

    /**
     * 클래스 이름으로 리뷰 조회하는거
     */

    @ApiOperation(value="classId로 리뷰 조회")
    @GetMapping("/instructor/review/classId/{classId}}")
    public ResponseEntity<List<InstructorReviewForm>> findInstructorReviewByClassId(@PathVariable Long classId) {
        List<InstructorReview> instructorReviews = new ArrayList<>();
        List<InstructorReviewForm> list = new ArrayList<>();

        instructorReviews = instructorReviewService.findReviewByClassId(classId);
        instructorReviews.forEach(el -> {
            InstructorReviewForm data = new InstructorReviewForm(el);
            list.add(data);
        });


        return ResponseEntity.ok().body(list);
    }

    /**
     * 입력점수 이상의 리뷰들이 나오게함
     * 만약 50을 입력하면 50점이상들이 나오게함
     */

    @ApiOperation(value="점수로 리뷰 조회")
    @GetMapping("/instructor/review/score/{score}}")
    public ResponseEntity<List<InstructorReviewForm>> findInstructorReviewByScore(@PathVariable float score) {
        List<InstructorReview> instructorReviews = new ArrayList<>();
        List<InstructorReviewForm> list = new ArrayList<>();

        instructorReviews = instructorReviewService.findReviewByScore(score);
        instructorReviews.forEach(el -> {
            InstructorReviewForm data = new InstructorReviewForm(el);
            list.add(data);
        });

        return ResponseEntity.ok().body(list);
    }

    /**
     *  닉네임으로 학생 리뷰 조회하는거
     */

    @ApiOperation(value="UserId 로 리뷰 조회")
    @GetMapping("/student/review/{userId}}")
    public ResponseEntity<List<StudentReviewForm>> findStudentReviewByUserId(@PathVariable Long userId) {
        List<StudentReview> studentReviews = new ArrayList<>();
        List<StudentReviewForm> list = new ArrayList<>();

        studentReviews = studentReviewService.findReviewByUserId(userId);
        studentReviews.forEach(el -> {
            StudentReviewForm data = new StudentReviewForm(el);
            list.add(data);
        });

        return ResponseEntity.ok().body(list);
    }
}