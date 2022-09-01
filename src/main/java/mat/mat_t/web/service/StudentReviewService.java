package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.review.InstructorReview;
import mat.mat_t.domain.review.StudentReview;
import mat.mat_t.web.repository.StudentReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentReviewService {

    private final StudentReviewRepository studentReviewRepository;

    //저장
    public void saveReview(StudentReview studentReview) {
        studentReviewRepository.save(studentReview);
    }

    public StudentReview updateStudentReview(StudentReview studentReview, Long id) {
        StudentReview review = studentReviewRepository.findById(id).get();
        review.setReview(studentReview.getMannerTemperature());
        review.setReviewContent(studentReview.getReviewContent());
        return studentReviewRepository.save(review);
    }

    //삭제
    public void deleteReview(Long id) {
        studentReviewRepository.deleteById(id);
    }

    public List<StudentReview> checkAll() {
        return studentReviewRepository.findAll();
    }

    //클래스 단건 조회
    public StudentReview check(Long classId) {
        return studentReviewRepository.findById(classId).orElse(null);
    }

}
