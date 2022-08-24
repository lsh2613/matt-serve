package mat.mat_t.web.service;

import mat.mat_t.domain.class_.Classes;
import mat.mat_t.domain.class_.WaitingStudent;
import mat.mat_t.domain.user.User;
import mat.mat_t.web.repository.WaitingStudentsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WaitingStudentsServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    WaitingStudentsService waitingStudentsService;

    @Autowired
    WaitingStudentsRepository waitingStudentsRepository;

    @Test
    public void add() throws Exception{
        //given
        WaitingStudent waitingStudent = new WaitingStudent();
        waitingStudent.setWaitingId(1L);
        waitingStudent.setContent("test");
//
//        User user = new User();
//        user.setId(1L);
//        waitingStudent.setUserWS(new User());
//
//        Classes classes = new Classes();
//        classes.setClassId(1L);
//        waitingStudent.setClassesWS(new Classes());

        //when
        waitingStudentsService.add(waitingStudent);

        //then
        assertEquals(waitingStudent, waitingStudentsRepository.findOne(1L));

    }

}