package mat.mat_t.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ClassStudentsForm {

    @NotNull
    private Long classId;

    @NotNull
    private Long loginId;

}
