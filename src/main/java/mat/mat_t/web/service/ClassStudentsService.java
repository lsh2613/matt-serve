package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassStudents;
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

    public void updateClassStudents(ClassStudents classStudents, Long id) {
        ClassStudents students = classStudentsRepository.findById(id).get();
        classStudents.update(students);
    }

    public void deleteClassStudents(Long id) {
        classStudentsRepository.deleteById(id);
    }

    public List<ClassStudents> checkAll() {
        return classStudentsRepository.findAll();
    }

    //클래스 단건 조회
    public ClassStudents check(Long classId) {
        return classStudentsRepository.findById(classId).orElse(null);
    }


    public void matchReview(Long userId, Long classId, Long id1, Long id2) {
        ClassStudents students = classStudentsRepository.findById(id1).get();
        if (userId.equals(students.getUserCS().getId()) && classId.equals(students.getClassesCS().getClassId())) {
            students.ss(id2);
            System.out.println("students.getInstructorReview() = " + students.getInstructorReview());
            System.out.println("students.getUserCS() = " + students.getUserCS());
            System.out.println("students.getClassesCS() = " + students.getClassesCS());
            System.out.println("students.getStudentReview() = " + students.getStudentReview());
            System.out.println("students.getStatus() = " + students.getStatus());
            System.out.println("students.getClassListId() = " + students.getClassListId());
        }
    }
}