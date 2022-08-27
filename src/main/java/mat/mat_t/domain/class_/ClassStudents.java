package mat.mat_t.domain.class_;

import lombok.Getter;
import lombok.Setter;
import mat.mat_t.domain.review.InstructorReview;
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
    @Column(name = "classLi_id")
    private Long classListId;

    @ManyToOne
    @JoinColumn(name = "login_id")
    private User userCS;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Classes classesCS;

    @Enumerated(EnumType.STRING)

    @Column(nullable = false)
    private ClassStatus status; //수강상태 [DOING, FINISHED]

    //수강생 리뷰 매핑
    @OneToOne
    @JoinColumn(name = "stRe_id")
    private StudentReview studentReview;

    //강사 리뷰 매핑
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "insRe_id")
    private InstructorReview instructorReview;

    public ClassStudents() {
    }

    public ClassStudents(ClassStudentsForm form) {
        this.status = form.getStatus();
        this.classesCS = new Classes(form.getClassId());
        this.userCS = new User(form.getLoginId());
    }

    public void ss(Long id) {
        this.instructorReview = new InstructorReview(id);
    }

    public ClassStudents(ClassStatus classStatus) {
        this.status = classStatus;
    }

    public void update(ClassStudents classStudents) {
        this.status = classStudents.getStatus();
    }

    @Override
    public String toString() {
        return "ClassStudents{" +
                "classListId=" + classListId +
                ", userCS=" + userCS +
                ", classesCS=" + classesCS +
                ", status=" + status +
                ", studentReview=" + studentReview +
                ", instructorReview=" + instructorReview +
                '}';
    }
}
