package mat.mat_t.web.repository;

import lombok.RequiredArgsConstructor;
import mat.mat_t.DTO.ClassDTO;
import mat.mat_t.DTO.InstructorDTO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClassDtoRepository {

    private final EntityManager em;

    /**
     * instructor 먼저 조회 후 헤당 instructorId로 Classes 테이블 조회
     */
    public List<InstructorDTO> findInstructorDTOs() { //루트 조회(toOne 코드를 모두 한번에 조회)
        List<InstructorDTO> result = findInstructors(); //루프를 돌면서 컬렉션 추가(추가 쿼리 실행)
        result.forEach(i -> {
            List<ClassDTO> classes =
                    findClasses(i.getInstructorId());
            i.setClasses(classes);
        });
        return result;
    }

    /**
     * N인 instructor 조회
     */
    private List<InstructorDTO> findInstructors() {
        return em.createQuery(
                        "select new mat.mat_t.DTO.InstructorDTO(i.instructorId, i.major)" +
                        " from Instructor i", InstructorDTO.class)
                .getResultList();
    }

    /**
     * 1인 Class 조회
     */
    private List<ClassDTO> findClasses(Long instructorId) {
        return em.createQuery(
                        "select new mat.mat_t.DTO.ClassDTO(c.classId, c.title, c.numberOfStudents, c.descriptions, c.place, c.startTime, c.endTime, c.days, c.category, c.startDate, c.endDate, c.instructorC.instructorId)" +
                        " from Classes c" +
                                " where c.instructorC.instructorId = : instructorId",
                        ClassDTO.class)
                .setParameter("instructorId", instructorId)
                .getResultList();
    }

    /**강사 아이디로 class 조회**/
    public List<ClassDTO> findByInstructorId(Long instructorId) {
        return em.createQuery(
                        "select new mat.mat_t.DTO.ClassDTO(c.classId, c.title, c.numberOfStudents, c.descriptions, c.place, c.startTime, c.endTime, c.days, c.category, c.startDate, c.endDate, c.instructorC.instructorId)" +
                                " from Classes c" +
                                " where c.instructorC.instructorId = : instructorId",
                        ClassDTO.class)
                .setParameter("instructorId", instructorId)
                .getResultList();
    }
}
