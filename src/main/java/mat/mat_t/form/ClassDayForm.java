package mat.mat_t.form;

import lombok.Data;
import mat.mat_t.domain.class_.ClassDay;

import java.util.Date;

@Data
public class ClassDayForm {

    private Long classDayId;
    private Long classesID;
    private Long dayId;
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

    private String dayName;

    public ClassDayForm() {
    }

    public ClassDayForm(ClassDay classDay){
        this.classDayId = classDay.getClassDayId();
        this.classesID = classDay.getClassesD().getClassId();
        this.dayId = classDay.getDays().getDayId();
        this.instructorId = classDay.getClassesD().getInstructorC().getInstructorId();
        this.title = classDay.getClassesD().getTitle();
        this.numberOfStudents = classDay.getClassesD().getNumberOfStudents();
        this.descriptions = classDay.getClassesD().getDescriptions();
        this.place = classDay.getClassesD().getPlace();
        this.startTime = classDay.getClassesD().getStartTime();
        this.endTime = classDay.getClassesD().getEndTime();
        this.category = classDay.getClassesD().getCategory();
        this.startDate = classDay.getClassesD().getStartDate();
        this.endDate = classDay.getClassesD().getEndDate();
        this.dayName = classDay.getDays().getDayName();
    }

}
