package mat.mat_t.domain.class_;

import lombok.Getter;
import lombok.Setter;
import mat.mat_t.domain.user.Instructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Classes {

    @Id
    @GeneratedValue
    @Column(name = "class_id")
    private Long classId;

    private String title;
    private int numberOfStudents;
    private String descriptions;
    private String place;
//    private ?? startTime;
//    private ?? endTime;
//    private ?? days;
//    private ?? category;
//    private ?? date;

    //클래스 수강생 매핑
    @OneToMany(mappedBy = "classesCS")
    private List<ClassStudent> classStudents = new ArrayList<>();

    //클래스 수강생 매핑
    @OneToMany(mappedBy = "classesWS")
    private List<WaitingStudent> waitingStudents = new ArrayList<>();

    //클래스 정보 매핑
    @OneToOne
    @JoinColumn(name = "code_id")
    private ClassInformation classInformation;

    //해당 클래스의 태그 매핑
    @OneToMany(mappedBy = "classesCT")
    private List<ClassTag> classTags = new ArrayList<>();

    //강사 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructorC;
}
