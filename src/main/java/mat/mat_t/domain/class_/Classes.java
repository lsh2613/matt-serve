package mat.mat_t.domain.class_;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mat.mat_t.domain.user.Instructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Data
@Getter
@Setter
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long classId;

    private String title;
    private Long numberOfStudents;
    private String descriptions;
    private String place;

    //우선은 문자형으로..
    private String startTime;   //시작일
    private String endTime; //종료일
    private String days;    //요일
    private String category;
    private Long date;    //  기간

    //클래스 수강생 매핑
    @OneToMany(mappedBy = "classesCS")
    private List<ClassStudents> classStudents = new ArrayList<>();

    //클래스 수강생 매핑
    @OneToMany(mappedBy = "classesWS")
    private List<WaitingStudent> waitingStudents = new ArrayList<>();

    //클래스 정보 매핑
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "code_id")
    private ClassInformation classInformation;

    //해당 클래스의 태그 매핑
    @OneToMany(mappedBy = "classesCT")
    private List<ClassTag> classTags = new ArrayList<>();

    //강사 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructorC;

    @Builder
    public Classes(Long classId, String title, Long numberOfStudents, String descriptions, String place, String startTime, String endTime, String days, String category, Long date) {
        this.classId = classId;
        this.title = title;
        this.numberOfStudents = numberOfStudents;
        this.descriptions = descriptions;
        this.place = place;
        this.startTime = startTime;
        this.endTime = endTime;
        this.days = days;
        this.category = category;
        this.date = date;
    }

    public Classes(Long classId, ClassInformation codeId, Instructor instructId, String title, Long numberOfStudents, String descriptions, String place, String startTime, String endTime, String days, String category, Long date) {
        this.classId = classId;
        this.classInformation = codeId;
        this.instructorC = instructId;
        this.title = title;
        this.numberOfStudents = numberOfStudents;
        this.descriptions = descriptions;
        this.place = place;
        this.startTime = startTime;
        this.endTime = endTime;
        this.days = days;
        this.category = category;
        this.date = date;
    }


    public Classes() {
    }

  /*  //==연관관계 메서드==//
    public void setClassInformation(ClassInformation classInformation) {
        this.classInformation = classInformation;
        classInformation.setClasses(this);
    }

    public void setInstructorC(Instructor instructorC) {
        this.instructorC = instructorC;
        instructorC.getClassList().add(this);
    }

    //==생성 메서드==//
    public static Classes createClass( ClassInformation classInformation, Instructor instructor, String title, Long numberOfStudents, String descriptions, String place, String startTime, String endTime, String days, String category, Long date) {
        Classes classes = new Classes();
        classes.setClassInformation(classInformation);
        classes.setInstructorC(instructor);
        classes.setTitle(title);
        classes.setNumberOfStudents(numberOfStudents);
        classes.setDescriptions(descriptions);
        classes.setPlace(place);
        classes.setStartTime(startTime);
        classes.setEndTime(endTime);
        classes.setDays(days);
        classes.setCategory(category);
        classes.setDate(date);

        return classes;
    }*/

    public void update(Classes upClasses) {
        this.title = upClasses.getTitle();
        this.numberOfStudents = upClasses.getNumberOfStudents();
        this.descriptions = upClasses.getDescriptions();
        this.place = upClasses.getPlace();
        this.startTime = upClasses.getStartTime();
        this.endTime = upClasses.getEndTime();
        this.days = upClasses.getDays();
        this.category = upClasses.getCategory();
        this.date = upClasses.getDate();
    }

}
