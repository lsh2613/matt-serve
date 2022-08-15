package mat.mat_t;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.user.Gender;
import mat.mat_t.domain.user.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit() {
            User user1 = new User("test", "123", "testName", "testNickname","0000.00.00",
                    "010-xxxx-xxxx", "test@naver.com", Gender.MAN);
            User user2 = new User("lsh", "123", "이승헌", "닉네임1","2000.01.23",
                    "010-4012-7068", "lsh2613@naver.com", Gender.MAN);
            em.persist(user1);
            em.persist(user2);
        }
    }

}
