package mat.mat_t.form;

import lombok.Data;
import mat.mat_t.domain.class_.ClassDay;

@Data
public class ClassDayForm {

    private Long classDayId;
    private Long classesID;
    private Long dayId;

    public ClassDayForm() {
    }

    public ClassDayForm(ClassDay classDay){
        this.classDayId = classDay.getClassDayId();
        this.classesID = classDay.getClassesD().getClassId();
        this.dayId = classDay.getDays().getDayId();
    }
}
