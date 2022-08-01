package mat.mat_t.domain.class_;

import lombok.Getter;
import mat.mat_t.domain.user.Instructor;
import mat.mat_t.domain.user.User;

import javax.persistence.*;

@Entity
@Getter
public class WaitingStudent {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "waiting_id")
    private Long waitingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "login_id")
    private User userWS;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Classes classesWS;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructorWS;

    private String content;

}
