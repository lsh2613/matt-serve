package mat.mat_t.domain.class_;

import lombok.Builder;
import lombok.Data;
import mat.mat_t.domain.user.Instructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long classId;

    private String title;
    private Long numberOfStudents;
    private String descriptions;
    private String place;
    private String startTime;   //시작시간
    private String endTime; //종료시간
    private String days;    //요일
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

    @Builder
    public Classes(Long classId, String title, Long numberOfStudents, String descriptions, String place, String startTime, String endTime, String days, String category, Date startDate, Date endDate) {
        this.classId = classId;
        this.title = title;
        this.numberOfStudents = numberOfStudents;
        this.descriptions = descriptions;
        this.place = place;
        this.startTime = startTime;
        this.endTime = endTime;
        this.days = days;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Classes(Long classId, Instructor instructId, String title, Long numberOfStudents, String descriptions, String place, String startTime, String endTime, String days, String category, Date startDate, Date endDate) {
        this.classId = classId;
        this.instructorC = instructId;
        this.title = title;
        this.numberOfStudents = numberOfStudents;
        this.descriptions = descriptions;
        this.place = place;
        this.startTime = startTime;
        this.endTime = endTime;
        this.days = days;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
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
        this.days = upClasses.getDays();
        this.category = upClasses.getCategory();
        this.startDate = upClasses.getStartDate();
        this.endDate = upClasses.getEndDate();
    }



}
