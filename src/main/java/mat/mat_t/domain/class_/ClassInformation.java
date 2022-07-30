package mat.mat_t.domain.class_;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class ClassInformation {

    private Long classId;

    private int codeId;
}
