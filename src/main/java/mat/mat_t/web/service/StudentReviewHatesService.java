package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.review.StudentReviewHates;
import mat.mat_t.web.repository.StudentReviewHatesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentReviewHatesService {

    private final StudentReviewHatesRepository studentReviewHatesRepository;

    public void saveHates(StudentReviewHates studentReviewHates){
        studentReviewHatesRepository.save(studentReviewHates);
    }

    public void deleteHates(Long stReId,Long userId){
        studentReviewHatesRepository.deleteStudentReviewHatesByStudentReview_StReIdAndUserCS_Id(stReId,userId);
    }

    public boolean existsByUserIdAndInstructorReviewId(Long userId,Long stReId){
        return studentReviewHatesRepository.existsStudentReviewHatesByUserCS_IdAndStudentReview_StReId(userId,stReId);
    }

    public void deleteHatesByStReId(Long stReId){
        List<StudentReviewHates> studentReviewHates=studentReviewHatesRepository.findStudentReviewHatesByStudentReview_StReId(stReId);
        studentReviewHatesRepository.deleteAll(studentReviewHates);
    }
}
