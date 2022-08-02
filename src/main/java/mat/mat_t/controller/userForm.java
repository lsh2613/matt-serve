package mat.mat_t.controller;


import lombok.Getter;
import lombok.Setter;
import mat.mat_t.domain.user.Gender;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
public class userForm {

    @NotEmpty(message = "필수 입력란입니다.")
    private String name;

    @NotEmpty(message = "필수 입력란입니다.")
    private String password;

    @NotEmpty(message = "필수 입력란입니다.")
    private String nickname;

    @NotEmpty(message = "필수 입력란입니다.")
    private int age;

    @NotEmpty(message = "필수 입력란입니다.")
    private String phoneNumber;

    @NotEmpty(message = "필수 입력란입니다.")
    private String email;

    @NotEmpty(message = "필수 입력란입니다.")
    private Gender gender;

}
