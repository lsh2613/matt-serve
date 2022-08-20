package mat.mat_t.web.repository;


import mat.mat_t.domain.class_.Classes;
import mat.mat_t.domain.user.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Classes, Long> {


    /* 필요없어짐
    //같은 클래스 존재하는지 확인(codeId, instructorId 둘다 같으면 같은 클래스)
    @Query("select c from Classes c where c.instructorC = :instructorId")
    public List<Classes> findByClassInformationAndInstructorC(Instructor instructorId);
    */

    //강사 아이디로 조회
    @Query("select c from Classes c where c.instructorC = :instructorId")
    public List<Classes> findByInstructorC(Long instructorId);


}
