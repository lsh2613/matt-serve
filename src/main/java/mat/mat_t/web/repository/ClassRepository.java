package mat.mat_t.web.repository;


import mat.mat_t.domain.class_.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;


@Repository
public interface ClassRepository extends JpaRepository<Classes, Long> {

    List findByInstructorC_InstructorId(@Param(value = "instructor_id") Long instructorId);

}
