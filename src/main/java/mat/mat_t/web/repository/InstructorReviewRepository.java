package mat.mat_t.web.repository;

import mat.mat_t.domain.review.InstructorReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorReviewRepository extends JpaRepository<InstructorReview,Long>{
}
