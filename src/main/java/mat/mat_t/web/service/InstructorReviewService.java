package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassStudents;
import mat.mat_t.domain.review.InstructorReview;
import mat.mat_t.web.repository.InstructorReviewRepository;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public void createDate(InstructorReview instructorReview){
        instructorReview.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
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
        return instructorReviewRepository.findInstructorReviewsByClassStudents_ClassesCS_ClassIdOrderByInsReviewIdDesc(id);
    }

    public List<InstructorReview> findReviewByScore(float score) {
        return instructorReviewRepository.findInstructorReviewsByScoreGreaterThanOrderByInsReviewIdDesc(score);
    }

    public List<InstructorReview> findReviewByInstructorId(Long id) {
        return instructorReviewRepository.findInstructorReviewsByClassStudents_ClassesCS_InstructorC_InstructorIdOrderByInsReviewIdDesc(id);
    }

    public int countInstructorReviews(Long classId, Long userId) {
        return instructorReviewRepository.countByClassStudents_ClassesCS_ClassIdAndClassStudents_UserCS_Id(classId, userId);
    }

    public List<InstructorReview> findReviewByUserCS_id(Long id) {
        return instructorReviewRepository.findInstructorReviewsByClassStudents_UserCS_Id(id);
    }

    public List<InstructorReview> findReviewByClassStudents_id(Long id){
        return instructorReviewRepository.findInstructorReviewsByClassStudents_ClassStudentId(id);
    }

    public boolean hasReview(List<InstructorReview> instructorReviews, Long classId) {
        for (InstructorReview instructorReview : instructorReviews) {
            if (instructorReview.getClassStudents().getClassesCS().getClassId().equals(classId)) {
                return false;
            }
        }
        return true;
    }

}
