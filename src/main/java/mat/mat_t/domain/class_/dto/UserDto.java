package mat.mat_t.domain.class_.dto;

import lombok.Getter;
import lombok.Setter;
import mat.mat_t.domain.class_.ClassStudents;

@Getter
@Setter
public class UserDto {

    protected String Name;
    protected String contents;
    protected String date;

    public UserDto(ClassStudents classStudents){
        Name=classStudents.getUserCS().getName();
        contents=classStudents.getContents();
        date=classStudents.getDate();
    }
}
