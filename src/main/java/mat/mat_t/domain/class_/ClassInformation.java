package mat.mat_t.domain.class_;


import lombok.Data;
import lombok.Getter;

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


    @Column(name = "code_name", unique = true)
    private String codeName;

    public ClassInformation(Long codeId, String codeName) {
        this.codeId = codeId;
        this.codeName = codeName;
    }

    public ClassInformation() {

    }
}
