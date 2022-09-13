package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassStudents;
import mat.mat_t.domain.review.InstructorReview;
import mat.mat_t.web.repository.InstructorReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class InstructorReviewService {

    private final InstructorReviewRepository instructorReviewRepository;

    //저장
    public void saveReview(InstructorReview instructorReview) {
        instructorReviewRepository.save(instructorReview);
    }

    public InstructorReview updateInstructorReview(InstructorReview instructorReview, Long id) {
        InstructorReview review = instructorReviewRepository.findById(id).get();
        review.setReview(instructorReview.getReviewContent(), instructorReview.getScore());
        return instructorReviewRepository.save(review);
    }

    public List<InstructorReview> checkAll() {
        return instructorReviewRepository.findAll();
    }

    //삭제
    public void deleteReview(Long id) {
        instructorReviewRepository.deleteById(id);
    }

    //클래스 단건 조회
    public InstructorReview check(Long classId) {
        return instructorReviewRepository.findById(classId).orElse(null);
    }

    public InstructorReview updateClassStudents(ClassStudents classStudents, InstructorReview instructorReview) {
        instructorReview.setClassStudents(classStudents);
        return instructorReviewRepository.save(instructorReview);
    }

    public InstructorReview findByInsReviewId(Long id) {
        return instructorReviewRepository.findByInsReviewId(id);
    }

    public InstructorReview deleteClassStudents(ClassStudents classStudents, InstructorReview instructorReview) {
        classStudents = null;
        instructorReview.setClassStudents(classStudents);
        return instructorReviewRepository.save(instructorReview);
    }

    public List<InstructorReview> findReviewByClassId(Long id) {
        return instructorReviewRepository.findInstructorReviewsByClassStudents_ClassesCS_ClassId(id);
    }

    public List<InstructorReview> findReviewByScore(float score) {
        return instructorReviewRepository.findInstructorReviewsByScoreGreaterThan(score);
    }


    public int countInstructorReviews(Long classId, Long userId) {
        return instructorReviewRepository.countByClassStudents_ClassesCS_ClassIdAndClassStudents_UserCS_Id(classId, userId);
    }

    public int countClassId(Long classId){
        return instructorReviewRepository.countByClassStudents_ClassesCS_ClassId(classId);
    }

    public int countUserId(Long userId){
        return instructorReviewRepository.countByClassStudents_UserCS_Id(userId);
    }
}
