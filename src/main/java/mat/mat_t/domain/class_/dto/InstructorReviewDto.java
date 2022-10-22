package mat.mat_t.domain.class_.dto;

import lombok.Builder;
import lombok.Getter;
import mat.mat_t.domain.review.InstructorReview;

import java.util.List;

@Getter
public class InstructorReviewDto {

    protected Long instructorReviewId;
    protected float score;
    protected String instructorName;
    protected Long userId;
    protected String reviewContent;
    protected String nickname;
    protected String title;
    protected String date;
    protected int likes;
    protected int hates;

    public InstructorReviewDto() {
    }

    @Builder
    public InstructorReviewDto(InstructorReview instructorReview) {

        this.instructorReviewId = instructorReview.getInsReviewId();
        this.title = instructorReview.getClassStudents().getClassesCS().getTitle();
        this.instructorName = instructorReview.getClassStudents().getClassesCS().getInstructorC().getUserIn().getName();
        this.userId = instructorReview.getClassStudents().getUserCS().getId();
        this.nickname = instructorReview.getClassStudents().getUserCS().getNickname();
        this.score = instructorReview.getScore();
        this.reviewContent = instructorReview.getReviewContent();
        this.date = instructorReview.getDate();
        this.likes = instructorReview.getLikes();
        this.hates = instructorReview.getHates();
    }
}
