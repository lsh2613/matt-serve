package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.user.User;
import mat.mat_t.web.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EntityManager em;

    public Long join(User user) {
        hasDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    public String findPassword(String loginName) {
        Optional<User> optionalUser = userRepository.findByLoginId(loginName).stream().findFirst();
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 아이디 입니다");
        }
        else
            return optionalUser.get().getPassword();
    }

    public User findById(Long id) {
        return em.find(User.class, id);
    }

    private void hasDuplicateUser(User user) {
        List<User> findUsers = userRepository.findByLoginId(user.getLoginId());
        if (!findUsers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 아이디 입니다");
        }
    }

    public User updatePwd(Long id, String pwd) {
        User user = userRepository.findById(id);
        user.setPassword(pwd);
        return user;
    }

    public User updateNickname(Long id, String nickname) {
        User user = userRepository.findById(id);
        user.setNickname(nickname);
        return user;
    }

    public void deleteUser(Long id) {
        User findUser = userRepository.findById(id);
        userRepository.remove(findUser);
    }
}
