package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassStudents;
import mat.mat_t.domain.review.InstructorReview;
import mat.mat_t.domain.review.StudentReview;
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
    public ClassStudents updateClassStudents(ClassStudents classStudents, Long id) {
        ClassStudents students = classStudentsRepository.findById(id).get();
        students.setStatus(classStudents.getStatus());
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

    // class student 에서 insReId 수정 
    public ClassStudents updateClassStudentsInsRevId(ClassStudents student, Long ins_re_id) {

//        student.setInstructorReview(new InstructorReview(ins_re_id));
        return classStudentsRepository.save(student);
    }

    public ClassStudents updateClassStudentsStRevId(ClassStudents student, Long st_re_id) {

        student.setStudentReview(new StudentReview(st_re_id));
        return classStudentsRepository.save(student);
    }
}