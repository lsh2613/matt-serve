package mat.mat_t.form;

import lombok.Data;
import mat.mat_t.domain.user.Gender;
import mat.mat_t.domain.user.Instructor;

@Data
public class InstructorEditForm {

    private Long instructorId;
    private String major;
    private String introduction;

    public InstructorEditForm() {
    }

    public InstructorEditForm(Long instructorId, String major, String introduction) {
        this.instructorId = instructorId;
        this.major = major;
        this.introduction = introduction;
    }
}
