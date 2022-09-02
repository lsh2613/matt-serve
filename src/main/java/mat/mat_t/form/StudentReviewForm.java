package mat.mat_t.form;

import lombok.Data;
import mat.mat_t.domain.review.StudentReview;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class StudentReviewForm {

    @NotNull
    private Long mannerTemperature;

    @NotNull
    private Long loginId;

    @NotNull
    private Long classId;

    @NotEmpty
    private String reviewContent;

    public StudentReviewForm(){}

    public StudentReviewForm(StudentReview studentReview) {
        this.mannerTemperature=studentReview.getMannerTemperature();
    }
}
