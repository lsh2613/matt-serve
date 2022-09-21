package mat.mat_t.web.repository;

import mat.mat_t.domain.class_.ClassStatus;
import mat.mat_t.domain.class_.ClassStudents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassStudentsRepository extends JpaRepository<ClassStudents, Long> {
    ClassStudents findByUserCS_IdAndClassesCS_ClassId(Long userId, Long classId);
    List<ClassStudents> findClassDtoByUserCS_IdAndStatusIsOrderByClassStudentIdDesc(Long userId, ClassStatus status);
    int countByClassesCS_ClassIdAndUserCS_Id(Long classId,Long userId);
}