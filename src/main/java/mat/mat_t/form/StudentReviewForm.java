package mat.mat_t.form;

import lombok.Data;
import mat.mat_t.domain.review.StudentReview;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class StudentReviewForm {

    @NotNull
    private float mannerTemperature;

    @NotNull
    private Long studentId;

    @NotNull
    private Long classId;

}