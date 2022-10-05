package mat.mat_t.domain.class_.dto;

import lombok.Data;
import lombok.Getter;
import mat.mat_t.domain.class_.ClassTag;

import java.util.Date;

@Getter
public class ClassTagDto {
    protected Long tagId;
    protected String tagName;

    public ClassTagDto(ClassTag classTag) {
        {
            this.tagId = classTag.getTagInfo().getTagInfoId();
            this.tagName = classTag.getTagInfo().getTagName();
        }
    }

}
