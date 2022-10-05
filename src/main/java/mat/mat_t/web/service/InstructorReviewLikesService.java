package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.review.InstructorReviewLikes;
import mat.mat_t.web.repository.InstructorReviewLikesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorReviewLikesService {

    private final InstructorReviewLikesRepository instructorReviewLikesRepository;

    public void saveLike(InstructorReviewLikes instructorReviewLikes){
        instructorReviewLikesRepository.save(instructorReviewLikes);
    }

    public void deleteLike(Long insId,Long userId){
        instructorReviewLikesRepository.deleteReviewLikeByInstructorReview_InsReviewIdAndUserCS_Id(insId,userId);
    }

    public boolean existsByUserIdAndInstructorReviewId(Long userId,Long insId){
        return instructorReviewLikesRepository.existsReviewLikeByUserCS_IdAndInstructorReview_InsReviewId(userId,insId);
    }

    public void deleteLikesByInsId(Long insId){
        List<InstructorReviewLikes> reviewLikes=instructorReviewLikesRepository.findInstructorReviewLikesByInstructorReview_InsReviewId(insId);
        instructorReviewLikesRepository.deleteAll(reviewLikes);
    }
}
