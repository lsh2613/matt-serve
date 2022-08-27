package mat.mat_t.form;

import lombok.Data;
import mat.mat_t.domain.review.StudentReview;

import javax.validation.constraints.NotNull;

@Data
public class StudentReviewForm {

    @NotNull
    private Long mannerTemperature;

    public StudentReviewForm(){}

    public StudentReviewForm(StudentReview studentReview) {
        this.mannerTemperature=studentReview.getMannerTemperature();
    }
}
