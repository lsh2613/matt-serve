package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassStatus;
import mat.mat_t.domain.class_.ClassStudents;
import mat.mat_t.domain.class_.Classes;
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

<<<<<<< HEAD

    public List<ClassStudents> findClassStudentsByStatus(ClassStatus classStatus){
        return classStudentsRepository.findClassStudentsByStatusIs(classStatus);
=======
    public List<ClassStudents> findClassStudentsByUserIdAndStatus(Long userId,ClassStatus classStatus){
        return classStudentsRepository.findClassStudentsByUserCS_IdAndStatusIs(userId,classStatus);
    }

    public List<Classes> finddd(Long userId,ClassStatus classStatus){
        List<ClassStudents> classStudents = classStudentsRepository.findClassStudentsByUserCS_IdAndStatusIs(userId, classStatus);

    }

    public int countClassStudents(Long classId,Long userId){
        return classStudentsRepository.countByClassesCS_ClassIdAndUserCS_Id(classId,userId);
>>>>>>> 2ede074a8aff9506dc9daa03fb65bec51b12935f
    }

}