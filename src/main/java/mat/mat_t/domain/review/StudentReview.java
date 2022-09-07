package mat.mat_t.domain.review;

import lombok.Getter;
import lombok.Setter;
import mat.mat_t.domain.class_.ClassStudents;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class StudentReview {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "stRe_id")
    private Long stReId;

    private float mannerTemperature;  // 평가내용
<<<<<<< Updated upstream
=======

    private String reviewContent;
>>>>>>> Stashed changes

    @OneToOne(fetch = FetchType.LAZY)
    private ClassStudents classStudents;

    public StudentReview(){}

<<<<<<< Updated upstream
    public StudentReview(float mannerTemperature){
=======
    public StudentReview(float mannerTemperature,String reviewContent){
        this.mannerTemperature=mannerTemperature;
        this.reviewContent=reviewContent;
    }

    public void setReview(float mannerTemperature,String reviewContent){
>>>>>>> Stashed changes
        this.mannerTemperature=mannerTemperature;
        this.reviewContent=reviewContent;
    }
<<<<<<< Updated upstream

    public void setReview(float mannerTemperature){
        this.mannerTemperature=mannerTemperature;
    }
=======
>>>>>>> Stashed changes
}