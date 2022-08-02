package mat.mat_t.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.user.User;
import mat.mat_t.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long join(User user) {
        hasDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    private void hasDuplicateUser(User user) {
        List<User> findUsers = userRepository.findByName(user.getName());
        if (!findUsers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 아이디 입니다");
        }
    }
}
