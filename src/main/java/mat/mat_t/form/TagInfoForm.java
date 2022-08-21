package mat.mat_t.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class TagInfoForm {

    @NotNull
    private Long tagInfoId;

    @NotEmpty
    private String tagName;


}
