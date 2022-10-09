package mat.mat_t.web.repository;

import mat.mat_t.domain.user.Instructor;
import mat.mat_t.domain.user.User;
import mat.mat_t.form.InstructorForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    //전체 강사 정보 조회(강사테이블 + 유저 테이블 + 리뷰 score)
    @Query("select new mat.mat_t.form.InstructorForm(i.instructorId, i.major,i.introduction, u.name, u.nickname, u.birthDate,u.phoneNumber, u.email, u.gender, coalesce(avg(ir.score), -1))" +
            " from Instructor i join User u on i.instructorId= u.instructor.instructorId" +
            " left join Classes c on i.instructorId = c.instructorC.instructorId" +
            " left join ClassStudents cs on c.classId = cs.classesCS.classId" +
            " left join InstructorReview ir on cs.classStudentId = ir.classStudents.classStudentId" +
            " group by i.instructorId")
    List<InstructorForm> findAllByInstructorIdAndClassList();


    //선택 강사 정보 조회(강사아이디로 조회)->(강사테이블 + 유저 테이블 + 리뷰 score)
    @Query("select new mat.mat_t.form.InstructorForm(i.instructorId, i.major,i.introduction, u.name, u.nickname, u.birthDate,u.phoneNumber, u.email, u.gender, coalesce(avg(ir.score), -1))" +
            " from Instructor i join User u on i.instructorId= u.instructor.instructorId" +
            " left join Classes c on i.instructorId = c.instructorC.instructorId" +
            " left join ClassStudents cs on c.classId = cs.classesCS.classId" +
            " left join InstructorReview ir on cs.classStudentId = ir.classStudents.classStudentId" +
            " where i.instructorId =:instructorId")
    List<InstructorForm> findByInstructorIdAndAndClassList(@Param("instructorId") Long instructorId);

    @Query("select new mat.mat_t.domain.user.User(u.instructor.instructorId)" +
            " from User u join Instructor i on u.instructor.instructorId = i.instructorId" +
            " where u.id =:id")
    List<User> findByUserIn(@Param("id") Long id);
}
