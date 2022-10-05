package mat.mat_t.web.repository;

import mat.mat_t.domain.review.ReviewLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReviewLikeRepository extends JpaRepository<ReviewLike,Long> {

    @Transactional
    void deleteReviewLikeByInstructorReview_InsReviewIdAndUserCS_Id(Long insId,Long userId);
    boolean existsReviewLikeByUserCS_IdAndInstructorReview_InsReviewId(Long userId,Long insId);
    List<ReviewLike> findReviewLikesByInstructorReview_InsReviewId(Long insId);
}
