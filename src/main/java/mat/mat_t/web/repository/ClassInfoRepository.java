package mat.mat_t.web.repository;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassInformation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ClassInfoRepository {

    private final EntityManager em;

    public void save(ClassInformation classInformation) {
        em.persist(classInformation);
    }
    public ClassInformation findOne(Long id) {
        return em.find(ClassInformation.class, id);
    }
}
