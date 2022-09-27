package mat.mat_t.domain.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
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
    private String date;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private ClassStudents classStudents;

    public InstructorReview(String reviewContent, float score) {

        this.reviewContent = reviewContent;
        this.score = score;
    }

    public void setReview(String reviewContent, float score) {
        this.reviewContent = reviewContent;
        this.score = score;
    }

    public InstructorReview() {
    }
}