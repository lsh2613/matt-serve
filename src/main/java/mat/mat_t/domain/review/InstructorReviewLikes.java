package mat.mat_t.domain.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import mat.mat_t.domain.user.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class InstructorReviewLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "like_id")
    private Long likeId;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "student_id")
    private User userCS;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "review_id")
    private InstructorReview instructorReview;

    public InstructorReviewLikes() {
    }

    public InstructorReviewLikes(InstructorReview instructorReview, User user) {
        this.setInstructorReview(instructorReview);
        this.setUserCS(user);
    }
}
