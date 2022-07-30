package mat.mat_t.domain.review;

import javax.persistence.Entity;

@Entity
public class InstructorReview {

    private int classId;
    private String studentId;
    private int score;
    private String content;  // 평가내용
}
