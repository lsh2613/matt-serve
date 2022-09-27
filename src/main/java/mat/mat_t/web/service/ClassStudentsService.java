package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassStatus;
import mat.mat_t.domain.class_.ClassStudents;
import mat.mat_t.domain.class_.dto.ClassDto;
import mat.mat_t.domain.review.InstructorReview;
import mat.mat_t.web.repository.ClassStudentsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassStudentsService {

    private final ClassStudentsRepository classStudentsRepository;

    public void saveClassStudents(ClassStudents classStudents) {
        classStudentsRepository.save(classStudents);
    }

    // 수정
    public ClassStudents finishClass(Long id) {
        ClassStudents students = classStudentsRepository.findById(id).get();
        students.setStatus(ClassStatus.FINISHED);
        return classStudentsRepository.save(students);
    }

    public void deleteClassStudents(Long id) {
        classStudentsRepository.deleteById(id);
    }

    public List<ClassStudents> checkAll() {
        return classStudentsRepository.findAll();
    }

    // 클래스 단건 조회
    public ClassStudents check(Long classId) {
        return classStudentsRepository.findById(classId).orElse(null);
    }

    // 유저 id 와 클래스 id 를 이용한 class-student 조회
    public ClassStudents findByUserIdAndClassId(Long userId, Long classId) {
        ClassStudents students = classStudentsRepository.findByUserCS_IdAndClassesCS_ClassId(userId, classId);
        return students;
    }

    public List<ClassStudents> findByUserCS_IdAndStatusIs(Long userId, ClassStatus classStatus) {
        return classStudentsRepository.findClassDtoByUserCS_IdAndStatusIsOrderByClassStudentIdDesc(userId, classStatus);
    }

    public int countClassStudents(Long classId, Long userId) {
        return classStudentsRepository.countByClassesCS_ClassIdAndUserCS_Id(classId, userId);
    }

    public List<ClassStudents> findByUserCS(Long userId) {
        return classStudentsRepository.findByUserCS_IdOrderByClassStudentIdDesc(userId);
    }

    public List<ClassStudents> findByClassIdAndStatus(Long classId){
        return classStudentsRepository.findByClassesCS_ClassIdAndStatusIsLikeOrderByClassStudentIdDesc(classId,ClassStatus.DOING);
    }

    public Boolean checkNotReview(ClassStudents classStudent,
                      InstructorReviewService instructorReviewService, List<InstructorReview> instructorReviews) {
            return instructorReviewService.hasReview(instructorReviews, classStudent.getClassesCS().getClassId());
    }

    public Boolean checkReviews(ClassStudents classStudent, InstructorReviewService instructorReviewService,Long classId) {
        List<InstructorReview> instructorReviews;
        instructorReviews=instructorReviewService.findReviewByClassId(classId);
        return instructorReviewService.hasReview(instructorReviews, classStudent.getClassesCS().getClassId());
    }
}