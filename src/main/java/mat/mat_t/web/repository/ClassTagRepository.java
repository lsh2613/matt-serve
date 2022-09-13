package mat.mat_t.web.repository;

import mat.mat_t.domain.class_.ClassTag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ClassTagRepository extends JpaRepository<ClassTag, Long> {

    //클래스아이디로 클래스 태그 조회
    List findClassTagByClassesCT_ClassId(@Param(value = "class_id") Long classId);

    //tagInfo 아이디로 클래스 태그 조회
    List findClassTagByTagInfo_TagInfoId(@Param(value = "tagInfo_id") Long tagInfoId);

    // tagInfo 아이디 리스트로 클래스 태그 조회
    List findClassTagByTagInfo_TagInfoIdIn(@RequestParam(required = false) List<Long> tagInfoIdList);

    //중복체크
    boolean existsClassTagByClassesCT_ClassIdAndTagInfo_TagInfoId(@Param(value = "class_id") Long classId,
                                                                  @Param(value = "tagInfo_id") Long tagInfoId);
}
