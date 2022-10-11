package mat.mat_t.form;

import lombok.Data;
import mat.mat_t.domain.class_.Classes;
import java.util.Date;

@Data
public class ClassCreateForm {

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


    public ClassCreateForm(){}

    public ClassCreateForm(Classes classes){
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

    public ClassCreateForm(Long instructorId, String title, Long numberOfStudents, String descriptions, String place, String startTime, String endTime, String category, Date startDate, Date endDate) {
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
    }
}
