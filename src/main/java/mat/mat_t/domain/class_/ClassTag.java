package mat.mat_t.domain.class_;


import lombok.Data;
import mat.mat_t.form.ClassTagForm;

import javax.persistence.*;

@Entity
@Data
public class ClassTag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "tagCl_id")
    private Long tagClId;

    //클래스 태그 정보 매핑
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tagInfo_id")
    private TagInfo tagInfo;

    //클래스 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Classes classesCT;

    public ClassTag() {
    }

    public ClassTag(ClassTagForm form) {
        this.tagClId = form.getTagClId();
        this.tagInfo = new TagInfo(form.getTagInfo());
        this.classesCT = new Classes(form.getClassesCT());
    }
}
