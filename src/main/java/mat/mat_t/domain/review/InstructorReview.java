package mat.mat_t.domain.review;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class InstructorReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insRe_id")
    private Long insReviewId;

    private float score;

    private String reviewContent;
}
