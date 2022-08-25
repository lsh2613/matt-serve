package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.TagInfo;
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
        tagInfoRepository.save(form);
    }


    /**
     * 태그 수정
     **/
    public void updateTagInfo(Long tagInfoId, String tagInfoName) {
        tagInfoRepository.update(tagInfoId, tagInfoName);
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
