package mat.mat_t.form;

import lombok.Data;
import mat.mat_t.domain.review.InstructorReview;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class InstructorReviewForm {
    @NotNull
    private Long score;

    @NotEmpty
    private String reviewContent;

    @NotNull
    private Long loginId;

    @NotNull
    private Long classId;

    public InstructorReviewForm(){}

    public InstructorReviewForm(InstructorReview instructorReview) {
        this.score = instructorReview.getScore();
        this.reviewContent = instructorReview.getReviewContent();
    }
}
