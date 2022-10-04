package mat.mat_t.domain.class_.dto;

import lombok.Builder;
import lombok.Getter;
import mat.mat_t.domain.review.StudentReview;

import java.util.List;

@Getter
public class StudentReviewDto {

    protected float mannerTemperature;
    protected String nickname;
    protected String date;

    public  StudentReviewDto(){}

    @Builder
    public StudentReviewDto(StudentReview studentReview){
            this.mannerTemperature=studentReview.getMannerTemperature();
            this.nickname=studentReview.getClassStudents().getUserCS().getNickname();
            this.date=studentReview.getDate();
    }
}
