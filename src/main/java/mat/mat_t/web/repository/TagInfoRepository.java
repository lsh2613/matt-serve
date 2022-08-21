package mat.mat_t.web.repository;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.TagInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TagInfoRepository {

    private final EntityManager em;

    // 태그 정보 생성
    public void save(TagInfo tagInfo) {
        em.persist(tagInfo);
    }

    //같은 태그 정보가 존재하는지 확인(tagInfoId 같으면 같은 클래스)
    public List<TagInfo> findSameTagInfo(String tagName) {
        return em.createQuery("select t from TagInfo t where t.tagName = :tagName ", TagInfo.class)
                .setParameter("tagName", tagName)
                .getResultList();
    }

    // 태그 정보 삭제
    public void delete(TagInfo tagInfo) {
        em.remove(em.contains(tagInfo) ? tagInfo : em.merge(tagInfo));
    }

    // 전체 태그 정보 검색
    public List<TagInfo> findAll() {
        return em.createQuery("select t from TagInfo t", TagInfo.class)
                .getResultList();
    }

    // 태그 정보 아이디 검색
    public TagInfo findById(Long tagInfoId) {
        return em.find(TagInfo.class, tagInfoId);
    }

}

