package mat.mat_t.domain.review;

import lombok.Getter;
import lombok.Setter;
import mat.mat_t.domain.class_.ClassStatus;
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

    private long score;
    private String reviewContent;

    public InstructorReview(String reviewContent, long score) {
        this.score = score;
        this.reviewContent = reviewContent;
    }

    public void setReview(long score,String reviewContent){
        this.score=score;
        this.reviewContent=reviewContent;
    }

    public InstructorReview() {

    }

    public InstructorReview(Long id) {
        this.insReviewId=id;
    }
}