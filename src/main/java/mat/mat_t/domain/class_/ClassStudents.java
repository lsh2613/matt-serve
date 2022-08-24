package mat.mat_t.domain.class_;

import lombok.Getter;
import mat.mat_t.domain.review.InstructorReview;
import mat.mat_t.domain.review.StudentReview;
import mat.mat_t.domain.user.User;

import javax.persistence.*;

@Entity
@Getter
public class ClassStudents {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
    @OneToOne
    @JoinColumn(name = "insRe_id")
    private InstructorReview instructorReview;



}
