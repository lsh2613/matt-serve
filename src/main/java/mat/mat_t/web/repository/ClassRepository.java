package mat.mat_t.web.repository;


import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.Classes;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.Iterator;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClassRepository{

    private EntityManager em;

    public void save (Classes classes){
        if (classes.getClassId() == null) {
            em.persist(classes);
        } else {
            em.merge(classes);
        }
    }

    public Classes findOne(Long classId){
        return em.find(Classes.class, classId);
    }


    public List<Classes> findAll() {
        return em.createQuery("select c from Classes c", Classes.class)
                .getResultList();
    }

    //같은 클래스 존재하는지 확인(codeId, instructorId 둘다 같으면 같은 클래스)
    public List<Classes> findSameClass(Long codeId, Long instructorId) {
        return em.createQuery("select c from Classes c where c.classInformation = :codeId and c.instructorC = : instructorId", Classes.class)
                .setParameter("codeId", codeId)
                .setParameter("instructorId", instructorId)
                .getResultList();
    }

    //해당 강사 클래스들 조회
    public List<Classes> findByInstructor(Long instructorId) {
        return em.createQuery("select c from Classes c where c.instructorC = :instructorId", Classes.class)
                .setParameter("instructorId", instructorId)
                .getResultList();
    }

    //클래스 삭제 만들어야함

}
