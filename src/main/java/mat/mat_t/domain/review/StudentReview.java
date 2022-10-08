package mat.mat_t.domain.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String date;
    private int likes;
    private int hates;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private ClassStudents classStudents;

    public StudentReview(){}

    public StudentReview(float mannerTemperature){
        this.mannerTemperature=mannerTemperature;
    }

    public StudentReview(float mannerTemperature,ClassStudents classStudents){
        this.mannerTemperature=mannerTemperature;
        this.setClassStudents(classStudents);
    }

    public void setReview(float mannerTemperature){
        this.mannerTemperature=mannerTemperature;
    }
}