package mat.mat_t.web.repository;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.WaitingStudent;
import org.hibernate.sql.Select;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class WaitingStudentsRepository {

    private final EntityManager em;

    public void save(WaitingStudent wsUser) {
        em.persist(wsUser);
    }

    public void remove(WaitingStudent wsUser) {
        em.remove(wsUser.getWaitingId());
    }

    public WaitingStudent findOne(Long id) {
        return em.find(WaitingStudent.class, id);
    }

    public List<WaitingStudent> findListByClassId(Long classId) {
        return em.createQuery("select w from WaitingStudent w where w.classesWS.classId =:classId", WaitingStudent.class)
                .setParameter("classId", classId)
                .getResultList();
    }

}
