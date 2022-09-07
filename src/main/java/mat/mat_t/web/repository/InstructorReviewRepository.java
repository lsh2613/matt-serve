package mat.mat_t.web.repository;

import mat.mat_t.domain.review.InstructorReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorReviewRepository extends JpaRepository<InstructorReview, Long> {
    InstructorReview findByInsReviewId(Long Id);
    List <InstructorReview> findInstructorReviewsByClassStudents_ClassesCS_ClassId(Long id);
    List <InstructorReview> findInstructorReviewsByScoreGreaterThan(float score);
    int countByClassStudents_ClassesCS_ClassIdAndClassStudents_UserCS_Id(Long classId,Long userId);
}