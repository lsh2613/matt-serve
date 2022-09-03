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
    private float score;
    
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

        public InstructorReview getId(Long id){
            this.insReviewId=id;
            return null;
        }
}