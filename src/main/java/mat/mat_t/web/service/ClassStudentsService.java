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

    //수정 
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

    //클래스 단건 조회
    public ClassStudents check(Long classId) {
        return classStudentsRepository.findById(classId).orElse(null);
    }



    //유저 id 와 클래스 id 를 이용한 class-student 조회 
    public ClassStudents findByUserIdAndClassId(Long userId, Long classId) {
        ClassStudents students = classStudentsRepository.findById(id1).get();
        if (userId.equals(students.getUserCS().getId()) && classId.equals(students.getClassesCS().getClassId())) {
            System.out.println("students.getInstructorReview() = " + students.getInstructorReview());
            System.out.println("students.getUserCS() = " + students.getUserCS());
            System.out.println("students.getClassesCS() = " + students.getClassesCS());
            System.out.println("students.getStudentReview() = " + students.getStudentReview());
            System.out.println("students.getStatus() = " + students.getStatus());
            System.out.println("students.getClassListId() = " + students.getClassListId());
        }
    }
}