package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassTag;
import mat.mat_t.web.repository.ClassTagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClassTagService {

    private final ClassTagRepository classTagRepository;

    /**
     * 클래스 태그 정보 생성
     **/

    public void createClassTag(ClassTag classTag) {
        classTagRepository.save(classTag);
    }

    /**
     * 클래스 태그 삭제
     **/
    public void deleteClassTag(Long ClassTagId) {
        classTagRepository.deleteById(ClassTagId);
    }

    /**
     * 클래스 태그 클래스 아이디로 조회
     **/
    public List<ClassTag> findClassTagByClassId(Long classId) {
        return classTagRepository.findClassTagByClassesCT_ClassId(classId);
    }

    /**
     * 태그 개별 조회
     **/
    public List<ClassTag> findClassTagByTagInfoId(Long tagClId) {
        return classTagRepository.findClassTagByTagInfo_TagInfoId(tagClId);
    }
}
