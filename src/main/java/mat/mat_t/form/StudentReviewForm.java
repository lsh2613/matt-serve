package mat.mat_t.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StudentReviewForm {

    @NotNull
    private float mannerTemperature;
}
