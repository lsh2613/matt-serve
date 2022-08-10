package mat.mat_t.domain.review;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class StudentReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stRe_id")
    private Long stReId;

    private float mannerTemperature;  // 평가내용

}
