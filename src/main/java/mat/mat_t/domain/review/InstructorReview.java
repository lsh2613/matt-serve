    package mat.mat_t.domain.review;

    import lombok.Getter;
    import lombok.Setter;
    import javax.persistence.*;

    @Entity
    @Getter
    @Setter
    public class InstructorReview {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "insRe_id")
    private Long insReviewId;

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
    }
