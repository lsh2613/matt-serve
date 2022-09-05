package mat.mat_t.domain.class_;

import lombok.Getter;
import lombok.Setter;
import mat.mat_t.domain.review.StudentReview;
import mat.mat_t.domain.user.User;
import mat.mat_t.form.ClassStudentsForm;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ClassStudents {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cs_id")
    private Long classStudentId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User userCS;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Classes classesCS;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClassStatus status; // 수강상태 [DOING, FINISHED]

    public ClassStudents() {
    }

    public ClassStudents(ClassStudentsForm form) {
        this.status = form.getStatus();
        this.classesCS = new Classes(form.getClassId());
        this.userCS = new User(form.getStudentId());
    }

    public ClassStudents(ClassStatus classStatus) {
        this.status = classStatus;
    }

    public ClassStudents(WaitingStudent findWs) {
        this.status = ClassStatus.DOING;
        this.setClassesCS(findWs.getClassesWS());
        this.setUserCS(findWs.getUserWS());
    }

    public ClassStudents(Long id) {
        this.classStudentId = id;
    }
}