package mat.mat_t.form;

import lombok.Getter;
import lombok.Setter;
import mat.mat_t.domain.user.Category;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CommunityForm {

    @NotBlank(message = "제목을 입력해주세요.")
    @Size(max = 20, message = "제목은 20자 이하로 입력해주세요.")
    String title;

    @NotBlank(message = "내용을 입력해주세요.")
    String content;

    @NotNull(message = "필수 입력란입니다.")
    Category category;
}
