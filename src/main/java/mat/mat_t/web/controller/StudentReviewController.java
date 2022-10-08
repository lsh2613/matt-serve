package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassStudents;
import mat.mat_t.domain.class_.dto.StudentReviewDto;
import mat.mat_t.domain.review.StudentReview;
import mat.mat_t.domain.user.User;
import mat.mat_t.form.StudentReviewForm;
import mat.mat_t.web.service.ClassStudentsService;
import mat.mat_t.web.service.StudentReviewHatesService;
import mat.mat_t.web.service.StudentReviewLikesService;
import mat.mat_t.web.service.StudentReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class StudentReviewController {

    private final StudentReviewService studentReviewService;
    private final ClassStudentsService classStudentsService;
    private final StudentReviewLikesService studentReviewLikesService;
    private final StudentReviewHatesService studentReviewHatesService;

    @ApiOperation(value = "학생 리뷰저장")
    @PostMapping("student/review")
    public ResponseEntity<StudentReview> createStudentReview(@Valid @RequestBody StudentReviewForm form,
                                                             HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        User loginUser = (User) session.getAttribute("loginUser");

        ClassStudents classStudents=classStudentsService.findByUserIdAndClassId(form.getStudentId(), form.getClassId());

        StudentReview studentReview = new StudentReview(form.getMannerTemperature(),classStudents);

        if(loginUser.getInstructor()==null){
            throw new IllegalStateException("Instructor로 등록되어 있지 않습니다.");
        }

        if(!studentReview.getClassStudents().getClassesCS().getInstructorC().
                getInstructorId().equals(loginUser.getInstructor().getInstructorId())){
            throw new IllegalStateException("Instructor가 아니여서 댓글을 달수 없습니다.");
        }

        if (studentReviewService.existsByClassStudentsId(studentReview.getClassStudents().getClassStudentId())) {
            throw new IllegalStateException("이미 등록되어 있습니다.");
        }

        studentReviewService.createDate(studentReview);
        studentReviewService.saveReview(studentReview);

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
        StudentReview studentReview = studentReviewService.findByStReId(id);

        if(studentReview.getLikes()>0){
            studentReviewLikesService.deleteLikesByStReId(id);
        }

        if(studentReview.getHates()>0){
            studentReviewHatesService.deleteHatesByStReId(id);
        }

        studentReviewService.deleteReview(id);
        return null;
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
    @GetMapping("/student/reviews/{userId}")
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