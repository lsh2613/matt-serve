package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.Classes;
import mat.mat_t.domain.class_.TagInfo;
import mat.mat_t.web.repository.TagInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagInfoService {

    private final TagInfoRepository tagInfoRepository;

    /**태그 정보 생성**/

    @Transactional
    public Long createTagInfo(Long tagInfoId, String tagName) {

        validateDuplicateTagInfo(tagInfoId); // 중복 태그 검증

        // 엔티티 조회
        TagInfo tagInfo = tagInfoRepository.findById(tagInfoId);

        // 태그 생성

        TagInfo newtagInfo = TagInfo.createTagInfo(tagInfoId, tagName);

        tagInfoRepository.save(newtagInfo);
        return newtagInfo.getTagInfoId();
    }

    /** 중복 클래스 검사 **/
    private void validateDuplicateTagInfo(Long tagInfoId) {
        List<TagInfo> findTagInfo =   tagInfoRepository.findSameTagInfo(tagInfoId);
        if (!findTagInfo.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 태그 정보입니다."); }
    }
    /**태그 수정**/
    public void updateTagInfo(TagInfo tagInfo) {

    }

    /**전체 태그 조회**/
    public List<TagInfo> findTagInfo (){
        return tagInfoRepository.findAll();
    }

    /**태그 개별 조회**/
    public TagInfo findOne(Long tagInfoId) {
        return tagInfoRepository.findById(tagInfoId);
    }

    //삭제
    public void deleteTagInfo() {

    }
}
