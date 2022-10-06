package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.review.StudentReview;
import mat.mat_t.domain.review.StudentReviewLikes;
import mat.mat_t.domain.user.User;
import mat.mat_t.web.service.StudentReviewLikesService;
import mat.mat_t.web.service.StudentReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Controller
@RequiredArgsConstructor
public class StudentReviewLikesController {

    private final StudentReviewLikesService studentReviewLikesService;
    private final StudentReviewService studentReviewService;

    @ApiOperation(value = "좋아요 누르기")
    @PostMapping("student/review/Like/{reviewId}")
    public ResponseEntity pressLikeReview(@PathVariable Long reviewId
            , HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        User loginUser = (User) session.getAttribute("loginUser");

        StudentReview studentReview = studentReviewService.findByStReId(reviewId);
        StudentReviewLikes studentReviewLikes = new StudentReviewLikes(studentReview, loginUser);

        if(studentReviewLikesService.existsByUserIdAndInstructorReviewId(loginUser.getId(), reviewId)){
            throw new IllegalStateException("이미 좋아요를 누르셨습니다.");
        }

        studentReviewLikesService.saveLike(studentReviewLikes);
        studentReviewService.pressLikes(studentReview);

        return null;
    }

    @ApiOperation(value="좋아요 취소하기")
    @DeleteMapping("student/review/Like/{reviewId}")
    public ResponseEntity cancelLikeReview(@PathVariable Long reviewId, HttpServletRequest request){

        HttpSession session = request.getSession(false);
        User loginUser = (User) session.getAttribute("loginUser");

        StudentReview studentReview = studentReviewService.findByStReId(reviewId);

        studentReviewService.cancelLikes(studentReview);
        studentReviewLikesService.deleteLike(reviewId, loginUser.getId());
        return null;
    }
}
