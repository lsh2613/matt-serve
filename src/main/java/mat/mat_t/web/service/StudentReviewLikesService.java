package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.review.StudentReviewLikes;
import mat.mat_t.web.repository.StudentReviewLikesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentReviewLikesService {

    private final StudentReviewLikesRepository studentReviewLikesRepository;

    public void saveLike(StudentReviewLikes studentReviewLikes){
        studentReviewLikesRepository.save(studentReviewLikes);
    }

    public void deleteLike(Long stReId,Long userId){
        studentReviewLikesRepository.deleteStudentReviewLikesByStudentReview_StReIdAndUserCS_Id(stReId,userId);
    }

    public boolean existsByUserIdAndInstructorReviewId(Long userId,Long stReId){
        return studentReviewLikesRepository.existsStudentReviewLikesByUserCS_IdAndStudentReview_StReId(userId,stReId);
    }

    public void deleteLikesByStReId(Long stReId){
        List<StudentReviewLikes> studentReviewLikes=studentReviewLikesRepository.findStudentReviewLikesByStudentReview_StReId(stReId);
        studentReviewLikesRepository.deleteAll(studentReviewLikes);
    }
}
