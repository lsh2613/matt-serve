package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
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

    //생성
    public void saveTagInfo (TagInfo tagInfo) {
        tagInfoRepository.save(tagInfo);
    }

    //수정
    public void updateTagInfo(TagInfo tagInfo) {

    }

    // 전체 검색
    public List<TagInfo> findALlTagInfo (){
        return tagInfoRepository.findAll();
    }

    //
    public TagInfo findOne(Long tagInfoId) {
        return tagInfoRepository.findById(tagInfoId);
    }

    //삭제
    public void deleteTagInfo() {

    }
}
