package mat.mat_t.domain.class_;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Days {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "day_id")
    private Long dayId;

    @Enumerated(EnumType.STRING)
    private DayEnum dayEnum;

    @OneToMany(mappedBy = "days")
    private List<ClassDay> classDayList = new ArrayList<>();

    public Days() {
    }

}
