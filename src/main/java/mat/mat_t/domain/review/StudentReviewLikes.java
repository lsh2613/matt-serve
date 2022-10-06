package mat.mat_t.domain.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import mat.mat_t.domain.user.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class StudentReviewLikes {
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
    private StudentReview studentReview;

    public StudentReviewLikes() {
    }

    public StudentReviewLikes(StudentReview studentReview, User user) {
        this.setStudentReview(studentReview);
        this.setUserCS(user);
    }
}
