package mat.mat_t.form;

import lombok.Data;
import mat.mat_t.domain.user.Instructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ClassForm {

    private Long classId;
    private Instructor instructorId;
    private String title;
    private Long numberOfStudents;
    private String descriptions;
    private String place;
    private String startTime;   //시작일
    private String endTime; //종료일
    private String days;    //요일
    private String category;
    private Date startDate; // 시작날짜
    private Date endDate;   //종료날짜



}
