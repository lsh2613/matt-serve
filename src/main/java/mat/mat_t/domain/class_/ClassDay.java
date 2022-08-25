package mat.mat_t.domain.class_;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@IdClass(ClassDayId.class)
public class ClassDay {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="class_id")
    private Classes classesDay;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="day_id")
    private Days days;

}
