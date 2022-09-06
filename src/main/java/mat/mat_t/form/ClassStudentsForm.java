package mat.mat_t.form;

import lombok.Data;
import mat.mat_t.domain.class_.ClassStatus;
import mat.mat_t.domain.class_.ClassStudents;

import javax.validation.constraints.NotNull;

@Data
public class ClassStudentsForm {

    @NotNull
    private ClassStatus status;

    @NotNull
    private Long classId;

    @NotNull
    private Long studentId;

    public ClassStudentsForm() {
    }

    public ClassStudentsForm(ClassStudents classStudents) {
        this.status = classStudents.getStatus();
        this.classId = classStudents.getClassesCS().getClassId();
        this.studentId = classStudents.getUserCS().getId();
    }

}
