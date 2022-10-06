package mat.mat_t.domain.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import mat.mat_t.domain.user.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class StudentReviewHates {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "hate_id")
    private Long hateId;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "student_id")
    private User userCS;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "review_id")
    private StudentReview studentReview;

    public StudentReviewHates() {
    }

    public StudentReviewHates(StudentReview studentReview, User user) {
        this.setStudentReview(studentReview);
        this.setUserCS(user);
    }
}
