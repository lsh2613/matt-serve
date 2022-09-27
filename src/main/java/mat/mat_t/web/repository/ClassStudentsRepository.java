package mat.mat_t.web.repository;

import mat.mat_t.domain.class_.ClassStatus;
import mat.mat_t.domain.class_.ClassStudents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassStudentsRepository extends JpaRepository<ClassStudents, Long> {
    ClassStudents findByUserCS_IdAndClassesCS_ClassId(Long userId, Long classId);
    List<ClassStudents> findClassDtoByUserCS_IdAndStatusIsOrderByClassStudentIdDesc(Long userId, ClassStatus status);
    List<ClassStudents> findByUserCS_IdOrderByClassStudentIdDesc(Long userId);
    int countByClassesCS_ClassIdAndUserCS_Id(Long classId,Long userId);
    List<ClassStudents> findByClassesCS_ClassIdAndStatusIsLikeOrderByClassStudentIdDesc(Long classId,ClassStatus status);
//    @Query(value = "select DISTINCT c from ClassStudents c " +
//            "left outer join InstructorReview ir on c.classStudentId=ir.classStudents.classStudentId where c.classStudentId=:classStudentId"
//    )
//    Boolean findClassStudents(@Param("classStudentId") Long classStudentId);
}