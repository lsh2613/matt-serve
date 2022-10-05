package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassStudents;
import mat.mat_t.domain.class_.dto.StudentReviewDto;
import mat.mat_t.domain.review.StudentReview;
import mat.mat_t.form.StudentReviewForm;
import mat.mat_t.web.service.ClassStudentsService;
import mat.mat_t.web.service.StudentReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class StudentReviewController {

    private final StudentReviewService studentReviewService;
    private final ClassStudentsService classStudentsService;

    @ApiOperation(value = "학생 리뷰저장")
    @PostMapping("student/review")
    public ResponseEntity<StudentReview> createStudentReview(@Valid @RequestBody StudentReviewForm form) {

        StudentReview studentReview = new StudentReview(form.getMannerTemperature());
        studentReviewService.createDate(studentReview);

        if (studentReviewService.countStudentReviews(form.getClassId(), form.getStudentId()) == 1) {
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
    @ApiOperation(value = "온도평균임")
    @GetMapping("studentTemperatureReview/{id}")
    public ResponseEntity<Float> temperatureReview(@PathVariable Long id) {
        float averageTemperature = studentReviewService.averageTemperature(id);
        return ResponseEntity.ok().body(averageTemperature);
    }

    @ApiOperation(value = "학생 리뷰수정")
    @PatchMapping("student/review/{id}")
    public ResponseEntity<StudentReview> updateStudentReview(@PathVariable Long id,float mannerTemperature) {
        StudentReview studentReview = new StudentReview(mannerTemperature);
        studentReviewService.updateStudentReview(studentReview, id);
        return ResponseEntity.ok().body(studentReview);
    }

    @ApiOperation(value = "학생 리뷰삭제")
    @DeleteMapping("student/review/{id}")
    public ResponseEntity<StudentReview> deleteStudentReview(@PathVariable Long id) {
        StudentReview studentReview = new StudentReview();
        studentReviewService.deleteReview(id);
        return ResponseEntity.ok().body(studentReview);
    }

    @ApiOperation(value = "학생 리뷰전체 조회")
    @GetMapping("student/reviews/all")
    public ResponseEntity<List<StudentReviewDto>> checkAllStudentReviews() {
        List<StudentReview> studentReviews = studentReviewService.checkAll();
        List<StudentReviewDto> studentReviewDtoList = new ArrayList<>();

        for (int i = 0; i < studentReviews.size(); i++) {
            studentReviewDtoList.add(new StudentReviewDto(studentReviews.get(i)));
        }

        Collections.reverse(studentReviewDtoList);

        return ResponseEntity.ok().body(studentReviewDtoList);
    }

    @ApiOperation(value = "학생리뷰 하나만 조회")
    @GetMapping("student/review/{reviewId}")
    public ResponseEntity<StudentReviewDto> checkStudentReview(@PathVariable Long reviewId) {
        StudentReview studentReview = studentReviewService.check(reviewId);
        StudentReviewDto studentReviewDto = new StudentReviewDto(studentReview);

        return ResponseEntity.ok().body(studentReviewDto);
    }

    /**
     * UserId로 리뷰 조회
     */

    @ApiOperation(value = "UserId 로 리뷰 조회")
    @GetMapping("/student/review/{userId}")
    public ResponseEntity<List<StudentReviewDto>> findStudentReviewByUserId(@PathVariable Long userId) {
        List<StudentReview> studentReviews = new ArrayList<>();
        List<StudentReviewDto> studentReviewDtoList = new ArrayList<>();

        studentReviews = studentReviewService.findReviewByUserId(userId);

        for (int i = 0; i < studentReviews.size(); i++) {
            studentReviewDtoList.add(new StudentReviewDto(studentReviews.get(i)));
        }

        return ResponseEntity.ok().body(studentReviewDtoList);
    }
}