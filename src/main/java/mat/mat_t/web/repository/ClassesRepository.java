package mat.mat_t.web.repository;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.Classes;
import mat.mat_t.domain.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ClassesRepository {
    private final EntityManager em;

    public void save(Classes classes) {
        em.persist(classes);
    }




}
