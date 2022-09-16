package mat.mat_t.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CommunityForm {

    @NotBlank(message = "제목을 입력해주세요.")
    @Size(max = 20, message = "제목은 20자 이하로 입력해주세요.")
    String title;

    @NotBlank
    String content;
}
