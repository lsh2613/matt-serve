package mat.mat_t.domain.class_;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TagInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tagInfo_id")
    private Long tagInfoId;

    @Column(name = "tag_name", unique = true)
    private String tagName;

    public  TagInfo () {

    }
    @Builder
    public TagInfo(Long tagInfoId, String tagName) {
        this.tagInfoId = tagInfoId;
        this.tagName = tagName;
    }

    public static TagInfo createTagInfo(Long tagInfoId, String tagName) {

        TagInfo tagInfo = new TagInfo();
        tagInfo.setTagInfoId(tagInfoId);
        tagInfo.setTagName(tagName);

        return tagInfo;
    }
}
