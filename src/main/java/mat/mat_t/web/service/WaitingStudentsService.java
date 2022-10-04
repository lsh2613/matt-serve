package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassStudents;
import mat.mat_t.domain.class_.WaitingStudent;

import mat.mat_t.web.repository.WaitingStudentsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WaitingStudentsService {

    private final WaitingStudentsRepository waitingStudentsRepository;

    public void add(WaitingStudent student) {

        hasDuplicate(student);
        waitingStudentsRepository.save(student);
    }

    private void hasDuplicate(WaitingStudent student) {
        List<WaitingStudent> findStudents = waitingStudentsRepository.findListByClassIdAndUserId(student.getClassesWS().getClassId(), student.getUserWS().getId());
        if (!findStudents.isEmpty()) {
            throw new IllegalStateException("이미 신청한 클래스입니다.");
        }
    }



    public void delete(Long id) {
        WaitingStudent find = waitingStudentsRepository.findOneById(id);
        waitingStudentsRepository.remove(find);
    }


    public List<WaitingStudent> findStudentsByClassId(Long classId) {
        return waitingStudentsRepository.findListByClassId(classId);
    }

    public WaitingStudent update(Long wsId, String content) {
        WaitingStudent find = waitingStudentsRepository.findOneById(wsId);
        find.setContent(content);
        return find;
    }

    public ClassStudents transfer(Long wsId) {
        WaitingStudent findWs = waitingStudentsRepository.findOneById(wsId);
        ClassStudents cs = new ClassStudents(findWs);
        delete(findWs.getWaitingId());
        return cs;
    }

    public List<WaitingStudent> findClassByUserId(Long userId){
        return waitingStudentsRepository.findListByUserId(userId);
    }
}
