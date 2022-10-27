package mat.mat_t.domain.review;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum StudentReviewStatus {
    @Enumerated(EnumType.STRING)
    HAS,NOT
}
