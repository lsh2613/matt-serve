package mat.mat_t.domain.class_.dto;

import lombok.Getter;
import mat.mat_t.domain.class_.ClassStatus;
import mat.mat_t.domain.class_.ClassStudents;
import mat.mat_t.domain.review.StudentReviewStatus;
import mat.mat_t.web.service.ClassStudentsService;
import mat.mat_t.web.service.StudentReviewService;

@Getter
public class ClassStudentsDto {
    protected Long userId;
    protected String name;
    protected StudentReviewStatus studentReviewStatus;

    public ClassStudentsDto(){}

    public ClassStudentsDto(ClassStudents classStudents, ClassStudentsService classStudentsService, StudentReviewService studentReviewService) {
        this.userId=classStudents.getUserCS().getId();
        this.name=classStudents.getUserCS().getName();

        if(classStudentsService.checkStudentReviews(classStudents,studentReviewService,classStudents.getUserCS().getId())){
            this.studentReviewStatus=StudentReviewStatus.NOT;
        }else{
            this.studentReviewStatus=StudentReviewStatus.HAS;
        }
    }
}
