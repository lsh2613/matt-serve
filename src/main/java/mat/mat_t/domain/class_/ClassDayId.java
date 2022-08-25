package mat.mat_t.domain.class_;

import java.io.Serializable;

public class ClassDayId implements Serializable {

    private Long classesDay;
    private Long days;

    public ClassDayId() {
    }

    public ClassDayId(Long classesDay, Long days) {
        this.classesDay = classesDay;
        this.days = days;
    }
}
