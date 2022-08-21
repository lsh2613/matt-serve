package mat.mat_t.web.repository;


import mat.mat_t.domain.class_.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClassRepository extends JpaRepository<Classes, Long> {

}
