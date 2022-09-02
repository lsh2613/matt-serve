package mat.mat_t.form;

import lombok.Data;
import mat.mat_t.domain.user.Gender;
import mat.mat_t.domain.user.Instructor;

@Data
public class InstructorForm {

    private Long instructorId;
    private String major;

    private String name;
    private String nickname;
    private String birthDate;
    private String phoneNumber;
    private String email;
    private Gender gender;

    public InstructorForm() {
    }

    public InstructorForm(Instructor instructor){
        this.instructorId = instructor.getInstructorId();
        this.major = instructor.getMajor();
        this.name= instructor.getUserIn().getName();
        this.nickname = instructor.getUserIn().getNickname();
        this.birthDate = instructor.getUserIn().getBirthDate();
        this.phoneNumber = instructor.getUserIn().getPhoneNumber();
        this.email = instructor.getUserIn().getEmail();
        this.gender = instructor.getUserIn().getGender();
    }
}
