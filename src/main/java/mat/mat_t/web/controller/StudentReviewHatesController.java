package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.review.StudentReview;
import mat.mat_t.domain.review.StudentReviewHates;
import mat.mat_t.domain.user.User;
import mat.mat_t.web.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class StudentReviewHatesController {

    private final StudentReviewHatesService studentReviewHatesService;
    private final StudentReviewService studentReviewService;

    @ApiOperation(value = "싫어요 누르기")
    @PostMapping("student/review/Hate/{reviewId}")
    public ResponseEntity pressHateReview(@PathVariable Long reviewId
            , HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        User loginUser = (User) session.getAttribute("loginUser");

        StudentReview studentReview = studentReviewService.findByStReId(reviewId);
        StudentReviewHates studentReviewHates = new StudentReviewHates(studentReview, loginUser);

        if(studentReviewHatesService.existsByUserIdAndInstructorReviewId(loginUser.getId(), reviewId)){
            throw new IllegalStateException("이미 싫어요를 누르셨습니다.");
        }

        studentReviewHatesService.saveHates(studentReviewHates);
        studentReviewService.pressHates(studentReview);

        return null;
    }

    @ApiOperation(value="싫어요 취소하기")
    @DeleteMapping("student/review/Hate/{reviewId}")
    public ResponseEntity cancelHateReview(@PathVariable Long reviewId, HttpServletRequest request){

        HttpSession session = request.getSession(false);
        User loginUser = (User) session.getAttribute("loginUser");
        StudentReview studentReview = studentReviewService.findByStReId(reviewId);

        studentReviewService.cancelHates(studentReview);
        studentReviewHatesService.deleteHates(reviewId, loginUser.getId());
        return null;
    }
}
