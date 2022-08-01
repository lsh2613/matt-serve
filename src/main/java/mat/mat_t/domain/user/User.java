package mat.mat_t.domain.user;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="instructor_id")
    private Instructor instructor;

    private String password;
    private String name;  //이름
    private String nickname; //닉네임
    private int phoneNumber;
    private String email;
    private int gender;
    private int age;
    private int auth;
}
