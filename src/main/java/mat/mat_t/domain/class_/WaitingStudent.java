package mat.mat_t.domain.class_;

import mat.mat_t.domain.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
public class WaitingStudent {


    @Id
    @GeneratedValue
    @Column(name = "waiting_student_id")
    private Long id;

    @OneToMany
    @JoinColumn(name="login_id")
    private List<User> user;

    private String content;
}
