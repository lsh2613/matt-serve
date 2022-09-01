package mat.mat_t.web.repository;

import mat.mat_t.domain.class_.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface ClassRepository extends JpaRepository<Classes, Long> {

    //강사 아이디로 조회
    List findByInstructorC_InstructorId(@Param(value = "instructor_id") Long instructorId);

    //진행 전 강의 조회
    @Query("select new mat.mat_t.domain.class_.Classes(c.classId, c.instructorC.instructorId, c.title, c.numberOfStudents, c.descriptions, c.place, c.startTime, c.endTime, c.category, c.startDate, c.endDate) from Classes c where c.startDate > :NOW")
    List<Classes> findAllByStartDateBefore(@Param("NOW") Date NOW);

    //진행 중 강의 조회
    @Query("select new mat.mat_t.domain.class_.Classes(c.classId, c.instructorC.instructorId, c.title, c.numberOfStudents, c.descriptions, c.place, c.startTime, c.endTime, c.category, c.startDate, c.endDate) from Classes c where c.startDate <= :NOW and c.endDate >= :NOW")
    List<Classes> findAllByStartDateAfterAndEndDateBefore(@Param("NOW") Date NOW);

    //진행 완료 강의 조회
    @Query("select new mat.mat_t.domain.class_.Classes(c.classId, c.instructorC.instructorId, c.title, c.numberOfStudents, c.descriptions, c.place, c.startTime, c.endTime, c.category, c.startDate, c.endDate) from Classes c where c.endDate < :NOW")
    List<Classes> findAllByEndDateAfter(@Param("NOW") Date NOW);

    //해당 요일 클래스 조회
    @Query("select new mat.mat_t.domain.class_.Classes(c.classId, c.instructorC.instructorId, c.title, c.numberOfStudents, c.descriptions, c.place, c.startTime, c.endTime, c.category, c.startDate, c.endDate)"+
            " from Classes c join c.classDays d" +
            " where d.days.dayName like :dayName")
    List<Classes> findAllByClassDays(@Param("dayName")String dayName);

    //키워드로 클래스 조회(title, category, description, place 에서 검색)
    @Query("select new mat.mat_t.domain.class_.Classes(c.classId, c.instructorC.instructorId, c.title, c.numberOfStudents, c.descriptions, c.place, c.startTime, c.endTime, c.category, c.startDate, c.endDate)"+
            " from Classes c where c.title like %:keyword% or c.category like %:keyword% or c.descriptions like %:keyword% or c.place like %:keyword%")
    List<Classes> findAllByTitleOrCategoryOrDescriptionsOrPlace(@Param("keyword")String keyword);

    @Query("select new mat.mat_t.domain.class_.Classes(c.classId, c.instructorC.instructorId, c.title, c.numberOfStudents, c.descriptions, c.place, c.startTime, c.endTime, c.category, c.startDate, c.endDate)" +
            " from Classes c" +
            " join c.classTags ct" +
            " join ct.tagInfo ti" +
            " where c.classId = ct.classesCT.classId and ct.tagInfo.tagName like :tagName")
    List<Classes> findByClassTags(@Param("tagName") String tagName);

}
