package mat.mat_t.domain.review;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class StudentReview {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "stRe_id")
    private Long stReId;

    private Long mannerTemperature;  // 평가내용

    public StudentReview(){}


    public StudentReview( Long mannerTemperature) {
        this.mannerTemperature = mannerTemperature;
    }

    public void update(StudentReview newStudentReview){
        this.mannerTemperature=newStudentReview.getMannerTemperature();
    }
}
