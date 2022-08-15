package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassInformation;
import mat.mat_t.web.repository.ClassInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ClassInfoService {

    private final ClassInfoRepository classInfoRepository;

    public List<ClassInformation> findAllCodeInfo() {
        return classInfoRepository.findAll();
    }

    public ClassInformation findCodeId(Long codeId) {
        return classInfoRepository.findOne(codeId);
    }
}
