package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.TagInfo;
import mat.mat_t.form.TagInfoForm;
import mat.mat_t.web.repository.TagInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TagInfoService {

    private final TagInfoRepository tagInfoRepository;

    /**
     * 태그 정보 생성
     **/

    public void createTagInfo(TagInfo form) {

        //validateDuplicateTagInfo(form.getTagName()); // 중복 태그 검증
        tagInfoRepository.save(form);
    }

    /**
     * 중복 클래스 검사
     **/
    public void validateDuplicateTagInfo(String tagName) {
        List<TagInfo> findTagInfo = tagInfoRepository.findSameTagInfo(tagName);
        if (!findTagInfo.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 태그 정보입니다.");
        }
    }

    /**
     * 태그 수정
     **/
    public void updateTagInfo(TagInfo tagInfo) {
        tagInfoRepository.save(tagInfo);
    }

    /**
     * 태그 삭제
     **/
    public void deleteTagInfo(TagInfo tagInfo) {
        tagInfoRepository.delete(tagInfo);
    }

    /**
     * 전체 태그 조회
     **/
    public List<TagInfo> findTagInfo() {
        return tagInfoRepository.findAll();
    }

    /**
     * 태그 개별 조회
     **/
    public TagInfo findOne(Long tagInfoId) {
        return tagInfoRepository.findById(tagInfoId);
    }

}
