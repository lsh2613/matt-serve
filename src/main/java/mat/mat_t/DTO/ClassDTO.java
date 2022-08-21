package mat.mat_t.DTO;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class ClassDTO {

    private Long classId;
    private String title;
    private Long numberOfStudents;
    private String descriptions;
    private String place;
    private String startTime;   //시작일
    private String endTime; //종료일
    private String days;    //요일
    private String category;

    @Temporal(TemporalType.DATE)
    private Date startDate; // 시작날짜
    @Temporal(TemporalType.DATE)
    private Date endDate;   //종료날짜

    private Long instructorId;

    public ClassDTO(Long classId, String title, Long numberOfStudents, String descriptions, String place, String startTime, String endTime, String days, String category, Date startDate, Date endDate, Long instructorId) {
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
        this.instructorId = instructorId;
    }
}
