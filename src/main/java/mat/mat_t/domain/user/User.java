package mat.mat_t.domain.user;

import lombok.Getter;
import mat.mat_t.domain.class_.ClassStudent;
import mat.mat_t.domain.class_.WaitingStudent;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User {

    @Id @GeneratedValue
    @Column(name = "login_id")
    private String studentId;

    private String password;
    private String name;
    private String nickname;
    private int phoneNumber;
    private String email;
    private int gender;
    private int age;
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
}
