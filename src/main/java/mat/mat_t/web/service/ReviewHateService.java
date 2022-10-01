package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.review.ReviewHate;
import mat.mat_t.web.repository.ReviewHateRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewHateService {

    private final ReviewHateRepository reviewHateRepository;

    public void saveHate(ReviewHate reviewHate){
        reviewHateRepository.save(reviewHate);
    }

    public void deleteHate(Long insId,Long userId){
        reviewHateRepository.deleteReviewLikeByInstructorReview_InsReviewIdAndUserCS_Id(insId,userId);
    }

    public boolean existsByUserIdAndInstructorReviewId(Long userId,Long insId){
        return reviewHateRepository.existsReviewLikeByUserCS_IdAndInstructorReview_InsReviewId(userId,insId);
    }

}
