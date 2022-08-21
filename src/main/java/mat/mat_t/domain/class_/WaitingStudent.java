package mat.mat_t.domain.class_;

import lombok.Getter;
import lombok.Setter;
import mat.mat_t.domain.user.Instructor;
import mat.mat_t.domain.user.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class WaitingStudent {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "waiting_id")
    private Long waitingId;

    //pull 했을때 밑에 부분 주석 처리되어 있었는데 일단 다른거 만들려면 있어야 해서 난 주석 풀고 돌림,,
//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private User userWS;
//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Classes classesWS;
//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructorWS;

    private String content;

}
