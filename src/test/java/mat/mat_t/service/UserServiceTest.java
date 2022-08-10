package mat.mat_t.service;

import lombok.extern.slf4j.Slf4j;
import mat.mat_t.domain.user.Gender;
import mat.mat_t.domain.user.User;
import mat.mat_t.web.repository.UserRepository;
import mat.mat_t.web.service.LoginService;
import mat.mat_t.web.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Slf4j
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LoginService loginService;
    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception{
        //given
        User user = new User();
        user.setLoginId("kim");

        //when
        Long savedId = userService.join(user);

        //then
        assertEquals(user, userRepository.findById(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복회원검증() throws Exception{
        //given
        User user1 = createUser();
        User user2 = createUser();

        //when
        userService.join(user1);
        userService.join(user2);

        //then
        fail("예외가 발생해야 한다");

    }

    @Test
    public void 비밀번호찾기() throws Exception{
        //given
        User user = createUser();
        userService.join(user);

        //when
        String findPw = userService.findPassword(user.getLoginId());

        //then
        assertEquals(user.getPassword(), findPw);
    }

    @Test
    public void 로그인() throws Exception{
        //given
        User user = createUser();
        userService.join(user);

        //when
        User loginMember = loginService.login(user.getLoginId(), user.getPassword());

        //then
        assertEquals(user, loginMember);
    }

    public User createUser() {
        User user = new User("lsh", "123", "이승헌", "닉네임1","2000.01.23",
                "010-4012-7068", "lsh2613@naver.com", Gender.MAN);
        return user;
    }
}