package mat.mat_t.web.repository;


import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.TagInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TagInfoRepository {

    private final EntityManager em;

    public void save(TagInfo tagInfo) {
        em.persist(tagInfo);
    }

    public List<TagInfo> findAll() {
        return em.createQuery("select t from TagInfo t", TagInfo.class)
                .getResultList();
    }

    public TagInfo findById(Long tagInfoId) {
        return em.find(TagInfo.class, tagInfoId);
    }

}

