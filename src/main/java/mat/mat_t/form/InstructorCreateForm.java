package mat.mat_t.form;

import lombok.Data;
import mat.mat_t.domain.user.Instructor;

@Data
public class InstructorCreateForm {

    private String major;
    private String introduction;

    public InstructorCreateForm() {
    }

    public InstructorCreateForm(Instructor instructor) {
        this.major = major;
        this.introduction = introduction;
    }
}
