package mat.mat_t.web.service;


import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.user.User;
import mat.mat_t.web.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final UserService userService;

    public User login(String loginName, String password) {

        List<User> users = userRepository.findByLoginId(loginName);
        User loginUser = users.stream().findFirst().get();

        if (loginUser.getPassword().equals(password)) {
            return loginUser;
        } else {
            return null;
        }
    }
}
