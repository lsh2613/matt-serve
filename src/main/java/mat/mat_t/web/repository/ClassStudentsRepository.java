package mat.mat_t.web.repository;

import mat.mat_t.domain.class_.ClassStudents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassStudentsRepository  extends JpaRepository<ClassStudents,Long> {
}