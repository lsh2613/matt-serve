package mat.mat_t.form;

import lombok.Data;
import mat.mat_t.domain.class_.Classes;
import java.util.Date;

@Data
public class ClassForm {

    private Long classId;
    private Long instructorId;
    private String title;
    private Long numberOfStudents;
    private String descriptions;
    private String place;
    private String startTime;   //시작일
    private String endTime; //종료일
    private String category;
    private Date startDate; // 시작날짜
    private Date endDate;   //종료날짜
    private Long countWS; //수강신청한 학생 수
    private Long countCS; //수강신청 후 수락된 학생 수
    private Long totalCount; //총 지원자 수

    private String instructorName;  //강사 이름
    private String instructorMajor;     //강사 전공
    private double instructorScore; //강사 평점

    public ClassForm(){}

    public ClassForm(Classes classes){
        this.classId = classes.getClassId();
        this.instructorId = classes.getInstructorC().getInstructorId();
        this.title = classes.getTitle();
        this.numberOfStudents = classes.getNumberOfStudents();
        this.descriptions = classes.getDescriptions();
        this.place = classes.getPlace();
        this.startTime = classes.getStartTime();
        this.endTime = classes.getEndTime();
        this.category = classes.getCategory();
        this.startDate = classes.getStartDate();
        this.endDate = classes.getEndDate();
    }

    public ClassForm(Long classId, Long instructorId, String title, Long numberOfStudents, String descriptions, String place, String startTime, String endTime, String category, Date startDate, Date endDate, Long countWS, Long countCS, String instructorName, String instructorMajor, double instructorScore) {
        this.classId = classId;
        this.instructorId = instructorId;
        this.title = title;
        this.numberOfStudents = numberOfStudents;
        this.descriptions = descriptions;
        this.place = place;
        this.startTime = startTime;
        this.endTime = endTime;
        this.category = category;
        this.startDate = startDate;
        this.endDate = endDate;
        this.countWS = countWS;
        this.countCS = countCS;
        this.instructorName = instructorName;
        this.instructorMajor = instructorMajor;
        this.instructorScore = instructorScore;
        this.totalCount = countWS+countCS;
    }
}
