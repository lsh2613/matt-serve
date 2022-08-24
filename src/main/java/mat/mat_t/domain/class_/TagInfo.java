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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tagInfo_id")
    private Long tagInfoId;

    @Column(name = "tag_name", unique = true)
    private String tagName;

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

}
