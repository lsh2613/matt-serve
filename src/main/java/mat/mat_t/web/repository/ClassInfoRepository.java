package mat.mat_t.web.repository;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassInformation;
import mat.mat_t.domain.class_.Classes;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClassInfoRepository {

    private final EntityManager em;

    public void save(ClassInformation classInformation) {
        em.persist(classInformation);
    }

    public List<ClassInformation> findAll() {
        return em.createQuery("select c from ClassInformation c", ClassInformation.class)
                .getResultList();
    }

    public ClassInformation findOne(Long id) {
        return em.find(ClassInformation.class, id);
    }
}
