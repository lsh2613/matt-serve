package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.review.InstructorReviewHates;
import mat.mat_t.web.repository.InstructorReviewHatesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorReviewHatesService {

    private final InstructorReviewHatesRepository instructorReviewHatesRepository;

    public void saveHate(InstructorReviewHates instructorReviewHates){
        instructorReviewHatesRepository.save(instructorReviewHates);
    }

    public void deleteHate(Long insId,Long userId){
        instructorReviewHatesRepository.deleteReviewLikeByInstructorReview_InsReviewIdAndUserCS_Id(insId,userId);
    }

    public boolean existsByUserIdAndInstructorReviewId(Long userId,Long insId){
        return instructorReviewHatesRepository.existsReviewLikeByUserCS_IdAndInstructorReview_InsReviewId(userId,insId);
    }

    public void deleteHatesByInsId(Long insId){
        List<InstructorReviewHates> instructorReviewHates=instructorReviewHatesRepository.findReviewHatesByInstructorReview_InsReviewId(insId);
        instructorReviewHatesRepository.deleteAll(instructorReviewHates);
    }
}
