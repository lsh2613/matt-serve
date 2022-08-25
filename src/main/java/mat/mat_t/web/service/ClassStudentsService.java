package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassStudents;
import mat.mat_t.domain.review.InstructorReview;
import mat.mat_t.web.repository.ClassStudentsRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassStudentsService {

    private final ClassStudentsRepository classStudentsRepository;

    // 수강신청
    public void classApplication(ClassStudents classStudents){classStudentsRepository.save(classStudents);}

//    //수정
//    public void changeClass(ClassStudents classStudents, Long id) {
//        ClassStudents students = classStudentsRepository.findById(id).get();
//        students.
//    }

    //취소
    public void cancelClass(Long id) {
        classStudentsRepository.deleteById(id);
    }

    public void matchReview(ClassStudents classStudents, String userId, String classId, InstructorReview id){
        if(classStudents.getUserCS().getId().equals(userId)&&classStudents.getClassesCS().getClassId().equals(classId))
            classStudents.setInstructorReview(id);
    }
}