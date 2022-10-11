package mat.mat_t.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import mat.mat_t.domain.class_.Classes;
import mat.mat_t.form.InstructorCreateForm;
import mat.mat_t.form.InstructorForm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "instructor_id")
    private Long instructorId;

    //회원매핑
    @OneToOne(mappedBy = "instructor")
    @JsonIgnore
    private User userIn;

    @Column(nullable = false)
    private String major;

    private String introduction;    //자기소개

    //클래스 매핑
    @OneToMany(mappedBy = "instructorC")
    private List<Classes> classList = new ArrayList<>();

    public Instructor(Long instructorId) {
        this.instructorId = instructorId;
    }

    public Instructor() {

    }

    public Instructor(Long instructorId, String major, String introduction) {
        this.instructorId = instructorId;
        this.major = major;
        this.introduction= introduction;
    }


    public void update(Instructor upInstructor) {
        this.major = upInstructor.getMajor();
        this.introduction= upInstructor.getIntroduction();
    }

    //InstructorForm 이용한 생성자 생성
    public Instructor(InstructorForm form) {
        this.instructorId = form.getInstructorId();
        this.major = form.getMajor();
        this.introduction = form.getIntroduction();
    }

    //InstructorForm 이용한 생성자 생성
    public Instructor(InstructorCreateForm form) {
        this.major = form.getMajor();
        this.introduction = form.getIntroduction();
    }
}
