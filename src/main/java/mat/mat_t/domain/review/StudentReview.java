package mat.mat_t.domain.review;

import javax.persistence.Entity;

@Entity
public class StudentReview {

    private int classId;
    private String studentId;
    private float mannerTemperature;  // 평가내용
}
