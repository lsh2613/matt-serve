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

        waitingStudent.setContent("test");

        User user = new User();
        waitingStudent.setUserWS(user);

        Classes classes = new Classes();
        waitingStudent.setClassesWS(classes);

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

    @Test(expected = IllegalStateException.class)
    public void 중복신청() throws Exception{
        //given
        WaitingStudent waitingStudent = new WaitingStudent();
        waitingStudent.setContent("test");

        User user = new User();
        waitingStudent.setUserWS(user);

        Classes classes = new Classes();
        waitingStudent.setClassesWS(classes);

        //when
        waitingStudentsService.add(waitingStudent);
        em.flush();
        waitingStudentsService.add(waitingStudent);

        //then
        fail("예외 발생");

    }

    @Test
    public void findListByClassId() throws Exception{
        //given
        WaitingStudent waitingStudent1 = new WaitingStudent();
        waitingStudent1.setContent("test");

        User user = new User();
        waitingStudent1.setUserWS(user);

        Classes classes = new Classes();
        waitingStudent1.setClassesWS(classes);
        waitingStudentsService.add(waitingStudent1);

        WaitingStudent waitingStudent2 = new WaitingStudent();
        waitingStudent2.setContent("test");

        waitingStudent2.setUserWS(user);
        waitingStudent2.setClassesWS(classes);

        //when
        em.flush();
        waitingStudentsService.add(waitingStudent2);

        //then
        assertEquals(2, waitingStudentsRepository.findListByClassId(1L).size());
    }

    @Test
    public void remove() throws Exception{
        //given
        WaitingStudent waitingStudent = new WaitingStudent();
        waitingStudent.setContent("test");

        User user = new User();
        waitingStudent.setUserWS(user);

        Classes classes = new Classes();
        waitingStudent.setClassesWS(classes);

        //when
        waitingStudentsService.add(waitingStudent);
        waitingStudentsService.delete(waitingStudent);

        //then
        assertNull(waitingStudentsRepository.findOne(1L));


    }

}