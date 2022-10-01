package mat.mat_t.web.repository;

import mat.mat_t.domain.review.ReviewHate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReviewHateRepository extends JpaRepository<ReviewHate,Long> {

    @Transactional
    void deleteReviewLikeByInstructorReview_InsReviewIdAndUserCS_Id(Long insId,Long userId);
    boolean existsReviewLikeByUserCS_IdAndInstructorReview_InsReviewId(Long userId,Long insId);
}
