package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.review.ReviewLike;
import mat.mat_t.web.repository.ReviewLikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewLikeService {

    private final ReviewLikeRepository reviewLikeRepository;

    public void saveLike(ReviewLike reviewLike){
        reviewLikeRepository.save(reviewLike);
    }

    public void deleteLike(Long insId,Long userId){
        reviewLikeRepository.deleteReviewLikeByInstructorReview_InsReviewIdAndUserCS_Id(insId,userId);
    }

    public boolean existsByUserIdAndInstructorReviewId(Long userId,Long insId){
        return reviewLikeRepository.existsReviewLikeByUserCS_IdAndInstructorReview_InsReviewId(userId,insId);
    }

    public void deleteLikesByInsId(Long insId){
        List<ReviewLike> reviewLikes=reviewLikeRepository.findReviewLikesByInstructorReview_InsReviewId(insId);
        reviewLikeRepository.deleteAll(reviewLikes);
    }
}
