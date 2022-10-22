package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassTag;
import mat.mat_t.domain.class_.dto.ClassInfoDto;
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
     * 클래스 태그 생성
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
     * 클래스 아이디, 태그 아이디 중복 처리
     **/
    public boolean checkClassesAndTagInfoDuplicate(Long classId, Long tagInfoId) {
        return classTagRepository.existsClassTagByClassesCT_ClassIdAndTagInfo_TagInfoId(classId, tagInfoId);
    }

    /**
     * 클래스 태그 클래스 아이디로 조회
     **/
    public List<ClassTag> findClassTagByClassId(Long classId) {
        return classTagRepository.findDistinctClassTagByClassesCT_ClassId(classId);
    }

    /**
     * 클래스 태그 태그 아이디로 조회
     **/
    public List<ClassTag> findClassTagByTagInfoId(Long tagInfoId) {
        return classTagRepository.findClassTagByTagInfo_TagInfoId(tagInfoId);
    }

    public List<ClassTag> findClassTagsByTagInfoList(List<Long> Id){
        return classTagRepository.findByTagInfo_TagInfoIdIn(Id);
    }

    public List<ClassInfoDto> findGroupByClassId(List<Long> classId){
        return classTagRepository.findClassTag(classId);
    }
}