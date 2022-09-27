package mat.mat_t.domain.class_.dto;

import lombok.Builder;
import lombok.Getter;
import mat.mat_t.domain.review.InstructorReview;

import java.util.List;

@Getter
public class InstructorReviewDto {

    protected float score;
    protected String instructorName;
    protected String reviewContent;
    protected String nickname;
    protected String title;
    protected String date;

    public InstructorReviewDto() {
    }

    @Builder
    public InstructorReviewDto(InstructorReview instructorReview) {

        this.title = instructorReview.getClassStudents().getClassesCS().getTitle();
        this.instructorName = instructorReview.getClassStudents().getClassesCS().getInstructorC().getUserIn().getName();
        this.nickname = instructorReview.getClassStudents().getUserCS().getNickname();
        this.score = instructorReview.getScore();
        this.reviewContent = instructorReview.getReviewContent();
        this.date=instructorReview.getDate();
    }
}
