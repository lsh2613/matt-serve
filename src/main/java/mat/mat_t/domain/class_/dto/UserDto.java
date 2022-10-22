package mat.mat_t.domain.class_.dto;

import lombok.Getter;
import lombok.Setter;
import mat.mat_t.domain.class_.ClassStudents;

@Getter
@Setter
public class UserDto {

    protected String Name;
    protected Long classStudentId;
    protected String contents;
    protected String date;

    public UserDto(ClassStudents classStudents) {
        classStudentId = classStudents.getClassStudentId();
        Name = classStudents.getUserCS().getName();
        contents = classStudents.getContents();
        date = classStudents.getDate();
    }
}
