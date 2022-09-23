package mat.mat_t.domain.class_;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


public enum ClassReviewStatus {
    @Enumerated(EnumType.STRING)
    HAS,NOT
}
