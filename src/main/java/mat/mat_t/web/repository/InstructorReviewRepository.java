package mat.mat_t.web.repository;

import mat.mat_t.domain.review.InstructorReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorReviewRepository extends JpaRepository<InstructorReview, Long> {
    InstructorReview findByInsReviewId(Long Id);
    List <InstructorReview> findInstructorReviewsByClassStudents_ClassesCS_ClassIdOrderByInsReviewIdDesc(Long id);
    List <InstructorReview> findInstructorReviewsByScoreGreaterThanOrderByInsReviewIdDesc(float score);
    List <InstructorReview> findInstructorReviewsByClassStudents_ClassesCS_InstructorC_InstructorIdOrderByInsReviewIdDesc(Long id);
    List <InstructorReview> findInstructorReviewsByClassStudents_UserCS_Id(Long id);
    List <InstructorReview> findInstructorReviewsByClassStudents_ClassStudentId(Long id);
    int countByClassStudents_ClassesCS_ClassIdAndClassStudents_UserCS_Id(Long classId,Long userId);
}