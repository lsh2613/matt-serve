package mat.mat_t.form;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClassForm {

    private Long classId;
    private String title;
    private Long numberOfStudents;
    private String descriptions;
    private String place;
    private String startTime;   //시작일
    private String endTime; //종료일
    private String days;    //요일
    private String category;
    private Long date;    //  기간



}
