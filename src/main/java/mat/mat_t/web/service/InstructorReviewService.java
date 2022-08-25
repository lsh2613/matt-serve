package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
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

//    public boolean validateReview(InstructorReview instructorReview,String name1,String name2){
//        if(name1.equals(instructorReview.getClassStudents().getUserCS().getName())&&name2.equals(instructorReview.getClassStudents().getClassesCS().getTitle())) {
//            return true;
//        }
//        return false;
//    }

    //저장
    public void saveReview(InstructorReview instructorReview) {
        instructorReviewRepository.save(instructorReview);
    }

    //수정
    public void updateReview(InstructorReview newInstructorReview, Long id) {
        InstructorReview instructorReview = instructorReviewRepository.findById(id).get();
        instructorReview.update(newInstructorReview);
    }

    //삭제
    public void deleteReview(Long id) {
        instructorReviewRepository.deleteById(id);
    }

    public List checkReview(){
        List<InstructorReview> instructorReviews = instructorReviewRepository.findAll();
        return instructorReviews;
    }
}