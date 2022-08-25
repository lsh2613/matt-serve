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
    private Long daysId;    //요일
    private String category;
    private Date startDate; // 시작날짜
    private Date endDate;   //종료날짜

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


}
