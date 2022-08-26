package mat.mat_t.domain.class_;

import lombok.Data;
import mat.mat_t.form.ClassDayForm;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class ClassDay implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "classDay_id")
    private Long classDayId;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="class_id", nullable = false)
    private Classes classesD;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="day_id", nullable = false)
    private Days days;

    //ClassDayForm 을 이용한 생성자 생성
    public ClassDay(ClassDayForm form) {
        this.classDayId = form.getClassDayId();
        this.classesD = new Classes(form.getClassesID());
        this.days = new Days(form.getDayId());
    }

    public ClassDay() {

    }

}
