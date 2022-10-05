package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.dto.MailDto;
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

    public User findUserByLoginId(String loginId) {
        User findUser = userRepository.findByUserId(loginId);
        return findUser;
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

    public boolean userEmailCheck(String userId, String email) {

        User user = userRepository.findByUserId(userId);
        if(user!=null && user.getEmail().equals(email)) {
            return true;
        }
        else {
            return false;
        }
    }

    /*public MailDto createMailAndChangePassword(Long id, String userEmail) {
        String str = getTempPassword();
        MailDto dto = new MailDto();
        dto.setAddress(userEmail);
        dto.setTitle("임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. 임시비밀번호 안내 관련 이메일 입니다." + " 회원님의 임시 비밀번호는 "
                + str + " 입니다." + "로그인 후에 비밀번호를 변경을 해주세요");
        updatePwd(id, userEmail);
        return dto;
    }



    //랜덤함수로 임시비밀번호 구문 만들기
    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }*/

}
