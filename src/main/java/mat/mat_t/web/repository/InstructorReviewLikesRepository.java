package mat.mat_t.web.repository;

import mat.mat_t.domain.review.InstructorReviewLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InstructorReviewLikesRepository extends JpaRepository<InstructorReviewLikes,Long> {

    @Transactional
    void deleteReviewLikeByInstructorReview_InsReviewIdAndUserCS_Id(Long insId,Long userId);
    boolean existsReviewLikeByUserCS_IdAndInstructorReview_InsReviewId(Long userId,Long insId);
    List<InstructorReviewLikes> findInstructorReviewLikesByInstructorReview_InsReviewId(Long insId);
}
