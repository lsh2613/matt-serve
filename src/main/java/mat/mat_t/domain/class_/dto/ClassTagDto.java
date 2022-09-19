package mat.mat_t.domain.class_.dto;

import lombok.Data;
import mat.mat_t.domain.class_.ClassTag;

import java.util.Date;

@Data
public class ClassTagDto {
    private Long classId;
    private String title;
    private Long numberOfStudents;
    private String descriptions;
    private String place;
    private String startTime; // 시작시간
    private String endTime; // 종료시간
    private String category;
    private Date startDate; // 시작날짜
    private Date endDate;   //종료날짜
    private Long tagInfoId;
    private String tagName;

    public ClassTagDto(ClassTag classTag) {
        {
            this.classId = classTag.getClassesCT().getClassId();
            this.title = classTag.getClassesCT().getTitle();
            this.numberOfStudents = classTag.getClassesCT().getNumberOfStudents();
            this.descriptions = classTag.getClassesCT().getDescriptions();
            this.place = classTag.getClassesCT().getPlace();
            this.startTime = classTag.getClassesCT().getStartTime();
            this.endTime = classTag.getClassesCT().getEndTime();
            this.category = classTag.getClassesCT().getCategory();
            this.startDate = classTag.getClassesCT().getStartDate();
            this.endDate = classTag.getClassesCT().getEndDate();
            this.tagInfoId = classTag.getTagInfo().getTagInfoId();
            this.tagName = classTag.getTagInfo().getTagName();
        }
    }

}
