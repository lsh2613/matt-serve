package mat.mat_t.domain.class_;

import lombok.Builder;
import lombok.Data;
import mat.mat_t.domain.user.Instructor;
import mat.mat_t.form.ClassForm;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Classes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "class_id")
    private Long classId;

    private String title;
    private Long numberOfStudents;
    private String descriptions;
    private String place;
    private String startTime;   //시작시간
    private String endTime; //종료시간\
    private String category;

    @Temporal(TemporalType.DATE)    // 값 입력할 때 '2022-01-01' 이런식으로 입력하면 됨
    private Date startDate; // 시작날짜
    @Temporal(TemporalType.DATE)
    private Date endDate;   //종료날짜

    //클래스 수강생 매핑
    @OneToMany(mappedBy = "classesCS")
    private List<ClassStudents> classStudents = new ArrayList<>();

    //클래스 수강생 매핑
    @OneToMany(mappedBy = "classesWS")
    private List<WaitingStudent> waitingStudents = new ArrayList<>();

    //해당 클래스의 태그 매핑
    @OneToMany(mappedBy = "classesCT")
    private List<ClassTag> classTags = new ArrayList<>();

    //강사 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructorC;

    //요일 매핑
    @OneToMany(mappedBy = "classesDay")
    private List<ClassDay> days = new ArrayList<>();

    @Builder
    public Classes(Long classId, String title, Long numberOfStudents, String descriptions, String place, String startTime, String endTime,  String category, Date startDate, Date endDate) {
        this.classId = classId;
        this.title = title;
        this.numberOfStudents = numberOfStudents;
        this.descriptions = descriptions;
        this.place = place;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //ClassForm을 이용한 생성자 생성
    public Classes(ClassForm form) {
        this.classId = form.getClassId();
        this.instructorC = new Instructor(form.getInstructorId());
        this.title = form.getTitle();
        this.numberOfStudents = form.getNumberOfStudents();
        this.descriptions = form.getDescriptions();
        this.place = form.getPlace();
        this.startTime = form.getStartTime();
        this.endTime = form.getEndTime();
        this.category = form.getCategory();
        this.startDate = form.getStartDate();
        this.endDate = form.getEndDate();
    }

    public Classes(Long id){
        this.classId=id;
    }

    public Classes() {
    }

    public void update(Classes upClasses) {
        this.title = upClasses.getTitle();
        this.numberOfStudents = upClasses.getNumberOfStudents();
        this.descriptions = upClasses.getDescriptions();
        this.place = upClasses.getPlace();
        this.startTime = upClasses.getStartTime();
        this.endTime = upClasses.getEndTime();
        this.category = upClasses.getCategory();
        this.startDate = upClasses.getStartDate();
        this.endDate = upClasses.getEndDate();
    }

    @Override
    public String toString() {
        return "Classes{" +
                "classId=" + classId +
                '}';
    }
}
