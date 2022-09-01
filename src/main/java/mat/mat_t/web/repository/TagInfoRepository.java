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

    // 태그 정보 생성
    public Long save(TagInfo tagInfo) {
        em.merge(tagInfo);
        return tagInfo.getTagInfoId();
    }
            

    // 태그 정보 생성
    public void update(Long tagInfoId, String tagName) {
        TagInfo findTagInfo = em.find(TagInfo.class, tagInfoId);
        findTagInfo.setTagName(tagName);
    }

    // 태그 정보 삭제
    public void delete(TagInfo tagInfo) {
        TagInfo deleteTagInfo = em.find(TagInfo.class, tagInfo.getTagInfoId());
        em.remove(deleteTagInfo);
    }

    // 전체 태그 정보 검색
    public List<TagInfo> findAll() {
        return em.createQuery("select t from TagInfo t order by t.tagInfoId asc", TagInfo.class)
                .getResultList();
    }

    // 태그 정보 아이디 검색
    public TagInfo findById(Long tagInfoId) {
        return em.find(TagInfo.class, tagInfoId);
    }

}

