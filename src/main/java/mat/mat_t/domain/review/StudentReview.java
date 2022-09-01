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

    private long mannerTemperature;  // 평가내용

    private String reviewContent;

    @OneToOne(fetch = FetchType.LAZY)
    private ClassStudents classStudentsStId;

    public StudentReview(){}

    public StudentReview(long mannerTemperature,String reviewContent){
        this.mannerTemperature=mannerTemperature;
        this.reviewContent=reviewContent;
    }

    public StudentReview(Long id){this.stReId=id;}

    public void setReview(long mannerTemperature){
        this.mannerTemperature=mannerTemperature;
    }
}
