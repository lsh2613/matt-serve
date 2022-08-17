package mat.mat_t.web.repository;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassInformation;
import mat.mat_t.domain.user.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

}
