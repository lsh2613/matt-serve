package mat.mat_t.web.repository;

import mat.mat_t.domain.class_.Classes;
import mat.mat_t.form.ClassForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;


@Repository
public interface ClassRepository extends JpaRepository<Classes, Long> {

    //강사 아이디로 조회
    @Query(value = "select new mat.mat_t.form.ClassForm(c.classId, c.instructorC.instructorId, c.title, c.numberOfStudents, c.descriptions, c.place, c.startTime, c.endTime, c.category, c.startDate, c.endDate, count(distinct ws.userWS.id), i.userIn.name, i.major, coalesce(avg(ir.score), -1))" +
            " from Instructor i join User u on i.instructorId= u.instructor.instructorId" +
            " left join Classes c on i.instructorId = c.instructorC.instructorId" +
            " left join ClassStudents cs on c.classId = cs.classesCS.classId" +
            " left join InstructorReview ir on cs.classStudentId = ir.classStudents.classStudentId" +
            " join  Classes c2 on i.instructorId = c2.instructorC.instructorId" +
            " left join WaitingStudent ws on c.classId = ws.classesWS.classId"+
            " where c.instructorC.instructorId =:instructorId" +
            " group by c.classId, c.instructorC.instructorId, c.title, c.numberOfStudents, c.descriptions, c.place, c.startTime, c.endTime, c.category, c.startDate, c.endDate,  i.userIn.name, i.major")
     List<ClassForm> findAllByInstructorC_InstructorId(@Param(value = "instructorId") Long instructorId);

    //진행 전 강의 조회
    @Query(value = "select new mat.mat_t.form.ClassForm(c.classId, c.instructorC.instructorId, c.title, c.numberOfStudents, c.descriptions, c.place, c.startTime, c.endTime, c.category, c.startDate, c.endDate, count(distinct ws.userWS.id), i.userIn.name, i.major, coalesce(avg(ir.score), -1))" +
            " from Instructor i join User u on i.instructorId= u.instructor.instructorId" +
            " left join Classes c on i.instructorId = c.instructorC.instructorId" +
            " left join ClassStudents cs on c.classId = cs.classesCS.classId" +
            " left join InstructorReview ir on cs.classStudentId = ir.classStudents.classStudentId" +
            " join  Classes c2 on i.instructorId = c2.instructorC.instructorId" +
            " left join WaitingStudent ws on c.classId = ws.classesWS.classId"+
            " where c.startDate >:NOW" +
            " group by c.classId, c.instructorC.instructorId, c.title, c.numberOfStudents, c.descriptions, c.place, c.startTime, c.endTime, c.category, c.startDate, c.endDate,  i.userIn.name, i.major")
    List<ClassForm> findAllByStartDateBefore(@Param("NOW") Date NOW);

    //진행 중 강의 조회
    @Query(value ="select new mat.mat_t.form.ClassForm(c.classId, c.instructorC.instructorId, c.title, c.numberOfStudents, c.descriptions, c.place, c.startTime, c.endTime, c.category, c.startDate, c.endDate, count(distinct ws.userWS.id), i.userIn.name, i.major, coalesce(avg(ir.score), -1))" +
            " from Instructor i join User u on i.instructorId= u.instructor.instructorId" +
            " left join Classes c on i.instructorId = c.instructorC.instructorId" +
            " left join ClassStudents cs on c.classId = cs.classesCS.classId" +
            " left join InstructorReview ir on cs.classStudentId = ir.classStudents.classStudentId" +
            " join  Classes c2 on i.instructorId = c2.instructorC.instructorId" +
            " left join WaitingStudent ws on c.classId = ws.classesWS.classId"+
            " where c.startDate <= :NOW and c.endDate >= :NOW" +
            " group by c.classId, c.instructorC.instructorId, c.title, c.numberOfStudents, c.descriptions, c.place, c.startTime, c.endTime, c.category, c.startDate, c.endDate,  i.userIn.name, i.major")
    List<ClassForm> findAllByStartDateAfterAndEndDateBefore(@Param("NOW") Date NOW);

    //진행 완료 강의 조회
    @Query(value = "select new mat.mat_t.form.ClassForm(c.classId, c.instructorC.instructorId, c.title, c.numberOfStudents, c.descriptions, c.place, c.startTime, c.endTime, c.category, c.startDate, c.endDate, count(distinct ws.userWS.id), i.userIn.name, i.major, coalesce(avg(ir.score), -1))" +
            " from Instructor i join User u on i.instructorId= u.instructor.instructorId" +
            " left join Classes c on i.instructorId = c.instructorC.instructorId" +
            " left join ClassStudents cs on c.classId = cs.classesCS.classId" +
            " left join InstructorReview ir on cs.classStudentId = ir.classStudents.classStudentId" +
            " join  Classes c2 on i.instructorId = c2.instructorC.instructorId" +
            " left join WaitingStudent ws on c.classId = ws.classesWS.classId"+
            " where c.endDate <:NOW" +
            " group by c.classId, c.instructorC.instructorId, c.title, c.numberOfStudents, c.descriptions, c.place, c.startTime, c.endTime, c.category, c.startDate, c.endDate,  i.userIn.name, i.major")
     List<ClassForm> findAllByEndDateAfter(@Param("NOW") Date NOW);

    //해당 요일 클래스 조회
    @Query(value = "select new mat.mat_t.form.ClassForm(c.classId, c.instructorC.instructorId, c.title, c.numberOfStudents, c.descriptions, c.place, c.startTime, c.endTime, c.category, c.startDate, c.endDate, count(distinct ws.userWS.id), i.userIn.name, i.major, coalesce(avg(ir.score), -1))" +
            " from Instructor i join User u on i.instructorId= u.instructor.instructorId" +
            " left join Classes c on i.instructorId = c.instructorC.instructorId" +
            " left join ClassStudents cs on c.classId = cs.classesCS.classId" +
            " left join InstructorReview ir on cs.classStudentId = ir.classStudents.classStudentId" +
            " join  Classes c2 on i.instructorId = c2.instructorC.instructorId" +
            " left join WaitingStudent ws on c.classId = ws.classesWS.classId"+
            " join c.classDays d" +
            " where d.days.dayName like :dayName" +
            " group by c.classId, c.instructorC.instructorId, c.title, c.numberOfStudents, c.descriptions, c.place, c.startTime, c.endTime, c.category, c.startDate, c.endDate,  i.userIn.name, i.major")
    List<ClassForm> findAllByClassDays(@Param("dayName")String dayName);

    //키워드로 클래스 조회(title, category, description, place 에서 검색)
    @Query(value = "select new mat.mat_t.form.ClassForm(c.classId, c.instructorC.instructorId, c.title, c.numberOfStudents, c.descriptions, c.place, c.startTime, c.endTime, c.category, c.startDate, c.endDate, count(distinct ws.userWS.id), i.userIn.name, i.major, coalesce(avg(ir.score), -1))" +
            " from Instructor i join User u on i.instructorId= u.instructor.instructorId" +
            " left join Classes c on i.instructorId = c.instructorC.instructorId" +
            " left join ClassStudents cs on c.classId = cs.classesCS.classId" +
            " left join InstructorReview ir on cs.classStudentId = ir.classStudents.classStudentId" +
            " join  Classes c2 on i.instructorId = c2.instructorC.instructorId" +
            " left join WaitingStudent ws on c.classId = ws.classesWS.classId"+
            " where c.title like %:keyword% or c.category like %:keyword% or c.descriptions like %:keyword% or c.place like %:keyword%" +
            " group by c.classId, c.instructorC.instructorId, c.title, c.numberOfStudents, c.descriptions, c.place, c.startTime, c.endTime, c.category, c.startDate, c.endDate,  i.userIn.name, i.major")
    List<ClassForm> findAllByTitleOrCategoryOrDescriptionsOrPlace(@Param("keyword")String keyword);

    //전체 클래스 조회
    @Query(value = "select new mat.mat_t.form.ClassForm(c.classId, c.instructorC.instructorId, c.title, c.numberOfStudents, c.descriptions, c.place, c.startTime, c.endTime, c.category, c.startDate, c.endDate, count(distinct ws.userWS.id), i.userIn.name, i.major, coalesce(avg(ir.score), -1))" +
            " from Instructor i join User u on i.instructorId= u.instructor.instructorId" +
            " left join Classes c on i.instructorId = c.instructorC.instructorId" +
            " left join ClassStudents cs on c.classId = cs.classesCS.classId" +
            " left join InstructorReview ir on cs.classStudentId = ir.classStudents.classStudentId" +
            " join  Classes c2 on i.instructorId = c2.instructorC.instructorId" +
            " left join WaitingStudent ws on c.classId = ws.classesWS.classId"+
            " group by c.classId, c.instructorC.instructorId, c.title, c.numberOfStudents, c.descriptions, c.place, c.startTime, c.endTime, c.category, c.startDate, c.endDate,  i.userIn.name, i.major")
    List<ClassForm> findAllByWaitingStudents();

    //클래스 아이디로 조회 waitingStudent 포함
    @Query(value =
            "select new mat.mat_t.form.ClassForm(c.classId, c.instructorC.instructorId, c.title, c.numberOfStudents, c.descriptions, c.place, c.startTime, c.endTime, c.category, c.startDate, c.endDate, count(distinct ws.userWS.id), i.userIn.name, i.major, coalesce(avg(ir.score), -1))" +
                    " from Instructor i join User u on i.instructorId= u.instructor.instructorId" +
                    " left join Classes c on i.instructorId = c.instructorC.instructorId" +
                    " left join ClassStudents cs on c.classId = cs.classesCS.classId" +
                    " left join InstructorReview ir on cs.classStudentId = ir.classStudents.classStudentId" +
                    " join  Classes c2 on i.instructorId = c2.instructorC.instructorId" +
                    " left join WaitingStudent ws on c.classId = ws.classesWS.classId"+
                    " where c.classId =:classId" +
                    " group by c.classId, c.instructorC.instructorId, c.title, c.numberOfStudents, c.descriptions, c.place, c.startTime, c.endTime, c.category, c.startDate, c.endDate,  i.userIn.name, i.major")
     ClassForm findAllByClassId(@Param("classId") Long classId);

    Classes findByClassId(Long classId);
}
