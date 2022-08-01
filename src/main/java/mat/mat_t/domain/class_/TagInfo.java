package mat.mat_t.domain.class_;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class TagInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tagInfo_id")
    private Long tagInfoId;

    @Column(name = "tag_name", unique = true)
    private String tagName;

}
