package mat.mat_t.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class InstructorReviewForm {
    @NotNull
    private float score;

    @NotEmpty
    private String reviewContent;

    @NotEmpty
    private String reviewer;

    @NotEmpty
    private String reviewers;
}
