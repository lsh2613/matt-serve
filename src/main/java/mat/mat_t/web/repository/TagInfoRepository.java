package mat.mat_t.web.repository;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.Classes;
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
    public void
    public List<TagInfo> findAll() {
        return em.createQuery("select t from TagInfo t", TagInfo.class)
                .getResultList();
    }

    public TagInfo findById(Long tagInfoId) {
        return em.find(TagInfo.class, tagInfoId);
    }

    //같은 태그 정보가 존재하는지 확인(tagInfoId 같으면 같은 클래스)
    public List<TagInfo> findSameTagInfo(Long tagInfoId) {
        return em.createQuery("select t from TagInfo t where t.tagInfoId = :tagInfoId ", TagInfo.class)
                .setParameter("tagInfoId", tagInfoId)
                .getResultList();
    }
}

