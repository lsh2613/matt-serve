package mat.mat_t.form;

import lombok.Data;
import mat.mat_t.domain.class_.Classes;
import mat.mat_t.domain.user.Instructor;

@Data
public class InstructorForm {

    private Long instructorId;
    private String major;
    private Long userIn; //회원 매핑

    public InstructorForm() {
    }

    public InstructorForm(Instructor instructor){
        this.instructorId = instructor.getInstructorId();
        this.major = instructor.getMajor();
        this.userIn = instructor.getUserIn().getId();
    }


}
