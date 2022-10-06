package mat.mat_t.web.repository;

import mat.mat_t.domain.review.StudentReviewLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentReviewLikesRepository extends JpaRepository<StudentReviewLikes,Long> {

    @Transactional
    void deleteStudentReviewLikesByStudentReview_StReIdAndUserCS_Id(Long stReId,Long userId);
    boolean existsStudentReviewLikesByUserCS_IdAndStudentReview_StReId(Long userId,Long stReId);
    List<StudentReviewLikes> findStudentReviewLikesByStudentReview_StReId(Long stReId);
}
