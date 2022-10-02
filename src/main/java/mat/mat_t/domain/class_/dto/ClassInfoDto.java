package mat.mat_t.domain.class_.dto;

import lombok.Data;
import lombok.Getter;
import mat.mat_t.domain.class_.ClassTag;

import java.util.Date;

@Getter
public class ClassInfoDto {
    protected Long classId;
    protected String title;
    protected Long numberOfStudents;
    protected String descriptions;
    protected String place;
    protected String startTime; // 시작시간
    protected String endTime; // 종료시간
    protected Date startDate; // 시작날짜
    protected Date endDate;   //종료날짜

    public ClassInfoDto(ClassTag classTag) {
        {
            this.classId = classTag.getClassesCT().getClassId();
            this.title = classTag.getClassesCT().getTitle();
            this.numberOfStudents = classTag.getClassesCT().getNumberOfStudents();
            this.descriptions = classTag.getClassesCT().getDescriptions();
            this.place = classTag.getClassesCT().getPlace();
            this.startTime = classTag.getClassesCT().getStartTime();
            this.endTime = classTag.getClassesCT().getEndTime();
            this.startDate = classTag.getClassesCT().getStartDate();
            this.endDate = classTag.getClassesCT().getEndDate();
        }
    }


}
