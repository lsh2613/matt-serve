package mat.mat_t.domain.class_;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ClassInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_id")
    private Long codeId;

    @OneToOne(mappedBy = "classInformation", fetch = FetchType.LAZY)
    private Classes classes;


    @Column(name = "code_name")
    private String codeName;

    public ClassInformation(Long codeId, String codeName) {
        this.codeId = codeId;
        this.codeName = codeName;
    }

    public ClassInformation(Long codeId) {
        this.codeId = codeId;
    }

    public ClassInformation() {

    }


    public void update(ClassInformation upClassInfo) {
        this.codeName = upClassInfo.getCodeName();
    }
}
