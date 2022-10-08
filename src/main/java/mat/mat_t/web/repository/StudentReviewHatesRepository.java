package mat.mat_t.web.repository;

import mat.mat_t.domain.review.StudentReviewHates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentReviewHatesRepository extends JpaRepository<StudentReviewHates,Long> {

    @Transactional
    void deleteStudentReviewHatesByStudentReview_StReIdAndUserCS_Id(Long stReId,Long userId);
    boolean existsStudentReviewHatesByUserCS_IdAndStudentReview_StReId(Long userId,Long stReId);
    List<StudentReviewHates> findStudentReviewHatesByStudentReview_StReId(Long stReId);
}
