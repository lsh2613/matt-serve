package mat.mat_t.domain.user;

import lombok.Getter;
import lombok.Setter;
import mat.mat_t.domain.class_.ClassStudent;
import mat.mat_t.domain.class_.WaitingStudent;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name="user_")
public class User {

    @Id @GeneratedValue
    @Column(name = "student_id")
    private Long id;
    private String name;  //로그인 아이디
    private String password;
    private String nickname;
    private String age;
    private String phoneNumber;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int auth;

    //강사 매핑
    @OneToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    //클래스 수강생 매핑
    @OneToMany(mappedBy = "userCS")
    private List<ClassStudent> classStudents = new ArrayList<>();

    //클래스 수강희망생 매핑
    @OneToMany(mappedBy = "userWS")
    private List<WaitingStudent> waitingStudents= new ArrayList<>();

    public User() {
    }

    public User(String name, String password, String nickname, String age, String phoneNumber, String email, Gender gender) {
        this.name = name;
        this.password = password;
        this.nickname = nickname;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
    }
}
