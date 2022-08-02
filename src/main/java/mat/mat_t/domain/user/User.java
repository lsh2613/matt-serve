package mat.mat_t.domain.user;

import lombok.Getter;
import mat.mat_t.domain.class_.ClassStudent;
import mat.mat_t.domain.class_.WaitingStudent;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name="user_")
public class User {

    @Id @GeneratedValue
    @Column(name = "student_id")
    private String studentId;
    private String name;  //로그인 아이디
    private String password;
    private String nickname;
    private int age;
    private String phoneNumber;
    private String email;
    private int gender;
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

    protected User() {
    }

    public User(String name, String password, String nickname, int age, String phoneNumber, String email, int gender) {
        this.name = name;
        this.password = password;
        this.nickname = nickname;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
    }
}
