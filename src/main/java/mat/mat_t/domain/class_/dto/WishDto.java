package mat.mat_t.domain.class_.dto;

import lombok.Getter;
import lombok.Setter;
import mat.mat_t.domain.class_.Wish;

@Getter
@Setter
public class WishDto {

    protected Long classId;
    protected String title;
    protected Long numberOfStudents;
    protected String descriptions;
    protected String category;

    public WishDto(Wish wish){
        this.classId= wish.getClassCs().getClassId();
        this.title=wish.getClassCs().getTitle();
        this.numberOfStudents=wish.getClassCs().getNumberOfStudents();
        this.descriptions=wish.getClassCs().getDescriptions();
        this.category=wish.getClassCs().getCategory();
    }
}
