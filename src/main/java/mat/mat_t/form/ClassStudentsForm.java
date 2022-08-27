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


//    private Long insReviewId;
//    private Long stReviewId;

    @NotNull
    private Long loginId;

    public ClassStudentsForm(){}

    public ClassStudentsForm(ClassStudents classStudents){
        this.status=classStudents.getStatus();
        this.classId=classStudents.getClassesCS().getClassId();
        this.loginId=classStudents.getUserCS().getId();
    }

}
