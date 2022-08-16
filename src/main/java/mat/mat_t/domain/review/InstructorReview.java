    package mat.mat_t.domain.review;

    import lombok.Getter;
    import lombok.Setter;
    import mat.mat_t.domain.class_.ClassStudents;
    import javax.persistence.*;

    @Entity
    @Getter
    @Setter
    public class InstructorReview {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "insRe_id")
        private Long insReviewId;

        private float score;
        private String reviewContent;

        @OneToOne(mappedBy = "instructorReview")
        private ClassStudents classStudents;

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
