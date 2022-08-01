package mat.mat_t.domain.class_;

import mat.mat_t.domain.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="class_students")
public class ClassStudent {


    @Id
    @GeneratedValue
    @Column(name = "class_student_id")
    private Long id;

    @OneToMany
    @JoinColumn(name="login_id")
    private List<User> user;

    private String content; // 수강 신청 시 메시지 전달 목적
}
