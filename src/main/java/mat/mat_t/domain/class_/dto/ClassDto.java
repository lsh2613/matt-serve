package mat.mat_t.domain.class_.dto;

import lombok.Getter;
import lombok.Setter;
import mat.mat_t.domain.class_.ClassReviewStatus;
import mat.mat_t.domain.class_.ClassStudents;
import mat.mat_t.web.service.ClassStudentsService;
import mat.mat_t.web.service.InstructorReviewService;

import java.util.Date;

@Getter
@Setter
public class ClassDto {

    protected Long classId;

    protected String title;
    protected Long numberOfStudents;
    protected String descriptions;
    protected String place;
    protected String startTime; // 시작시간
    protected String endTime; // 종료시간\
    protected String category;

    protected Date startDate; // 시작날짜

    protected Date endDate;   //종료날짜
    protected ClassReviewStatus review;

    public ClassDto() {
    }

    public ClassDto(ClassStudents classStudents) {
        this.classId = classStudents.getClassesCS().getClassId();
        this.title = classStudents.getClassesCS().getTitle();
        this.numberOfStudents = classStudents.getClassesCS().getNumberOfStudents();
        this.descriptions = classStudents.getClassesCS().getDescriptions();
        this.place = classStudents.getClassesCS().getPlace();
        this.startTime = classStudents.getClassesCS().getStartTime();
        this.endTime = classStudents.getClassesCS().getEndTime();
        this.category = classStudents.getClassesCS().getCategory();
        this.startDate = classStudents.getClassesCS().getStartDate();
        this.endDate = classStudents.getClassesCS().getEndDate();
        this.review= ClassReviewStatus.NOT;
    }

    public ClassDto(ClassStudents classStudents, ClassStudentsService classStudentsService, InstructorReviewService instructorReviewService) {
        this.classId = classStudents.getClassesCS().getClassId();
        this.title = classStudents.getClassesCS().getTitle();
        this.numberOfStudents = classStudents.getClassesCS().getNumberOfStudents();
        this.descriptions = classStudents.getClassesCS().getDescriptions();
        this.place = classStudents.getClassesCS().getPlace();
        this.startTime = classStudents.getClassesCS().getStartTime();
        this.endTime = classStudents.getClassesCS().getEndTime();
        this.category = classStudents.getClassesCS().getCategory();
        this.startDate = classStudents.getClassesCS().getStartDate();
        this.endDate = classStudents.getClassesCS().getEndDate();

        if(classStudentsService.checkReviews(classStudents,instructorReviewService,classStudents.getClassesCS().getClassId())){
            this.review=ClassReviewStatus.NOT;
        } else{
            this.review=ClassReviewStatus.HAS;
        }

    }
}