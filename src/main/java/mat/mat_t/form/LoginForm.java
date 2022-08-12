package mat.mat_t.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {

    @NotEmpty
    private String loginName;

    @NotEmpty
    private String password;
}
