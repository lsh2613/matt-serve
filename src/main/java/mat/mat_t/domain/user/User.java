package mat.mat_t.domain.user;

import lombok.Getter;
import lombok.Setter;
import mat.mat_t.domain.class_.ClassStudents;
import mat.mat_t.domain.class_.WaitingStudent;
import mat.mat_t.form.UserForm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name="user_")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "student_id")
    private Long id;

    private String loginId;
    private String password;
    private String name;
    private String nickname;
    private String birthDate;
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
    private List<ClassStudents> classStudents = new ArrayList<>();

    //클래스 수강희망생 매핑
    @OneToMany(mappedBy = "userWS")
    private List<WaitingStudent> waitingStudents= new ArrayList<>();

    public User() {
    }

    public User(String loginId, String password, String name, String nickname, String birthDate, String phoneNumber, String email, Gender gender) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
    }

    public User(UserForm form){
        this.id=form.getId();
    }

    public User(Long id){
        this.id=id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                '}';
    }
}
