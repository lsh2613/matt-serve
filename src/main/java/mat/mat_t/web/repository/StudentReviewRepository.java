package mat.mat_t.web.repository;

import mat.mat_t.domain.review.InstructorReview;
import mat.mat_t.domain.review.StudentReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentReviewRepository extends JpaRepository<StudentReview,Long>{
    StudentReview findByStReId(Long Id);

    List<StudentReview> findStudentReviewsByClassStudents_UserCS_Id(Long id);
//    List<StudentReview> findStudentReviewsByMannerTemperatureGreaterThan(float temperature);

}
