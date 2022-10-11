package mat.mat_t.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class WaitingStudentForm {

    @NotNull
    private Long wsId;

    @NotEmpty
    private String content;
}
