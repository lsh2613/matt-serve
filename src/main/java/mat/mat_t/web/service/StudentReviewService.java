package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassStudents;

import mat.mat_t.domain.review.StudentReview;
import mat.mat_t.web.repository.StudentReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentReviewService {

    private final StudentReviewRepository studentReviewRepository;

    //저장
    public void saveReview(StudentReview studentReview) {
        studentReview.setLikes(0);
        studentReview.setHates(0);
        studentReviewRepository.save(studentReview);
    }

    public void createDate(StudentReview studentReview){
        studentReview.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
    }

    public StudentReview updateStudentReview(StudentReview studentReview, Long id) {
        StudentReview review = studentReviewRepository.findById(id).get();
        review.setReview(studentReview.getMannerTemperature());
        return studentReviewRepository.save(review);
    }

    //삭제
    public void deleteReview(Long id) {
        studentReviewRepository.deleteById(id);
    }

    public void pressLikes(StudentReview studentReview){
        int likes=studentReview.getLikes();
        likes+=1;
        studentReview.setLikes(likes);
    }

    public void cancelLikes(StudentReview studentReview){
        int likes=studentReview.getLikes();
        likes-=1;
        studentReview.setLikes(likes);
    }

    public void pressHates(StudentReview studentReview){
        int hates=studentReview.getHates();
        hates+=1;
        studentReview.setHates(hates);
    }

    public void cancelHates(StudentReview studentReview){
        int hates=studentReview.getHates();
        hates-=1;
        studentReview.setHates(hates);
    }

    public float averageTemperature(Long id){
        List<StudentReview> studentReviews=studentReviewRepository.findStudentReviewsByClassStudents_UserCS_Id(id);

        float sumOfTemperature=0;

        for (StudentReview studentReview : studentReviews) {
            sumOfTemperature += studentReview.getMannerTemperature();
        }

        return sumOfTemperature/studentReviews.size();
    }

    public List<StudentReview> checkAll() {
        return studentReviewRepository.findAll();
    }

    //클래스 단건 조회
    public StudentReview check(Long reviewId) {
        return studentReviewRepository.findByStReId(reviewId);
    }

    public StudentReview findByStReId(Long id) {
        return studentReviewRepository.findByStReId(id);
    }

    public StudentReview deleteClassStudents(ClassStudents classStudents,StudentReview studentReview) {
        classStudents=null;
        studentReview.setClassStudents(classStudents);
        return studentReviewRepository.save(studentReview);
    }

    public List<StudentReview> findReviewByUserId(Long id) {
        return studentReviewRepository.findStudentReviewsByClassStudents_UserCS_IdOrderByStReIdDesc(id);
    }

    public boolean existsByClassStudentsId(Long csId){
        return studentReviewRepository.existsByClassStudents_ClassStudentId(csId);
    }
}

