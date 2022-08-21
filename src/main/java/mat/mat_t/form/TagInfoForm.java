package mat.mat_t.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TagInfoForm {

    @NotEmpty
    private Long tagInfoId;

    @NotEmpty
    private String tagName;


}
