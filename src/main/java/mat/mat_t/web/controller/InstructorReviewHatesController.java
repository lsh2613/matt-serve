package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.review.InstructorReview;
import mat.mat_t.domain.review.InstructorReviewHates;
import mat.mat_t.domain.user.User;
import mat.mat_t.web.service.InstructorReviewHatesService;
import mat.mat_t.web.service.InstructorReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class InstructorReviewHatesController {

    private final InstructorReviewHatesService instructorReviewHatesService;
    private final InstructorReviewService instructorReviewService;

    @ApiOperation(value = "싫어요 누르기")
    @PostMapping("review/Hate/{reviewId}")
    public ResponseEntity pressHateReview(@PathVariable Long reviewId
            , HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        User loginUser = (User) session.getAttribute("loginUser");

        InstructorReview instructorReview=instructorReviewService.findByInsReviewId(reviewId);
        InstructorReviewHates reviewHates = new InstructorReviewHates(instructorReview, loginUser);

        if(instructorReviewHatesService.existsByUserIdAndInstructorReviewId(loginUser.getId(), reviewId)){
            throw new IllegalStateException("이미 싫어요를 누르셨습니다.");
        }

        instructorReviewHatesService.saveHate(reviewHates);
        instructorReviewService.pressHates(instructorReview);

        return null;
    }

    @ApiOperation(value="싫어요 취소하기")
    @DeleteMapping("review/Hate/{reviewId}")
    public ResponseEntity cancelLikeReview(@PathVariable Long reviewId, HttpServletRequest request){

        InstructorReview instructorReview=instructorReviewService.findByInsReviewId(reviewId);
        HttpSession session = request.getSession(false);
        User loginUser = (User) session.getAttribute("loginUser");

        instructorReviewService.cancelHates(instructorReview);
        instructorReviewHatesService.deleteHate(reviewId, loginUser.getId());
        return null;
    }
}
