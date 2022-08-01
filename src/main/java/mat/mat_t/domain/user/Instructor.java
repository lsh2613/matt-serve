package mat.mat_t.domain.user;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Instructor extends User{

    @Id
    @GeneratedValue
    @Column(name = "instructor_id")
    private Long id;

    private String major;
}
