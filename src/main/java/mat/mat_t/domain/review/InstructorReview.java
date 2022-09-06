package mat.mat_t.domain.review;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import mat.mat_t.domain.class_.ClassStudents;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class InstructorReview {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "insRe_id")
    private Long insReviewId;

    private float score;

    private String reviewContent;

    // 클래스 스튜던트 매핑

    @OneToOne(fetch = FetchType.LAZY)
    private ClassStudents classStudents;

    public void setReview(String reviewContent, float score) {
        this.reviewContent = reviewContent;
        this.score = score;
    }

    public InstructorReview() {
    }

    public InstructorReview(String reviewContent, float score) {
        this.score = score;
        this.reviewContent = reviewContent;
    }

    public void update(InstructorReview newInstructorReview) {
        this.score = newInstructorReview.getScore();
        this.reviewContent = newInstructorReview.getReviewContent();
    }

    public InstructorReview getId(Long id) {
        this.insReviewId = id;
        return null;
    }
}
