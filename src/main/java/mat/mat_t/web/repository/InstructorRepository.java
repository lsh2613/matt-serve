package mat.mat_t.web.repository;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassInformation;
import mat.mat_t.domain.user.Instructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class InstructorRepository {

    private final EntityManager em;

    public void save(Instructor instructor) {
        em.persist(instructor);
    }
    public Instructor findOne(Long id) {
        return em.find(Instructor.class, id);
    }

    public List<Instructor> findAll() {
        return em.createQuery("select i from Instructor i", Instructor.class)
                .getResultList();
    }
}
