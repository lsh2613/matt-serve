package mat.mat_t.domain.class_;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mat.mat_t.form.TagInfoForm;

import javax.persistence.*;


@Entity
@Data
@Getter
@Setter
public class TagInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "tagInfo_id")
    private Long tagInfoId;

    @Column(name = "tag_name", unique = true)
    private String tagName;

    // 해당 태그 정보 매핑
    @OneToOne(mappedBy = "tagInfo")
    private ClassTag classTag;


    @Builder
    public TagInfo(Long tagInfoId, String tagName) {
        this.tagInfoId = tagInfoId;
        this.tagName = tagName;
    }

    public TagInfo() {
    }

    public TagInfo(TagInfoForm form){
        this.tagInfoId = form.getTagInfoId();
        this.tagName = form.getTagName();
    }

    // 생성 메소드
    public static TagInfo createTagInfo(Long tagInfoId, String tagName) {

        TagInfo tagInfo = new TagInfo();
        tagInfo.setTagInfoId(tagInfoId);
        tagInfo.setTagName(tagName);

        return tagInfo;
    }

}
