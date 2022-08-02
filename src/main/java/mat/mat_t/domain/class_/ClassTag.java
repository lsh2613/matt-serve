package mat.mat_t.domain.class_;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ClassTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tagCl_id")
    private Long tagClId;

    //클래스 정보 매핑
    @OneToOne
    @JoinColumn(name = "tagInfo_id")
    private TagInfo tagInfo;

    //클래스 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Classes classesCT;

}
