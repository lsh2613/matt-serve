package mat.mat_t.form;

import lombok.Data;
import mat.mat_t.domain.class_.ClassTag;

@Data
public class ClassTagForm {

    private Long tagClId;

    private Long tagInfo; // 태그 정보 매핑

    private Long classesCT; // 클래스 매핑

    public ClassTagForm() {
    }

    public ClassTagForm(ClassTag classTag) {
        this.tagClId = classTag.getTagClId();
        this.tagInfo = classTag.getTagInfo().getTagInfoId();
        this.classesCT = classTag.getClassesCT().getClassId();
    }
}
