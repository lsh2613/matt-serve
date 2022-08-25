package mat.mat_t.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ClassStudentsForm {

    @NotEmpty
    private String classId;

    @NotEmpty
    private String loginId;

}
