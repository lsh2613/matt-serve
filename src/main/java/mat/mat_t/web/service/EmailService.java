package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class EmailService {

    //의존성 주입을 통해서 필요한 객체를 가져온다.
    private final JavaMailSender emailSender;
    private String temPasswd; //랜덤 인증 코드

    //랜덤 인증 코드 생성
    public void createCode() {
        StringBuffer key = new StringBuffer();

        for (int i = 0; i < 12; i++) {
            key.append((char) ((Math.random() * 26) + 97));
        }
        temPasswd = key.toString();
    }

    //메일 양식 작성
    public MimeMessage createEmailForm(String email) throws MessagingException, UnsupportedEncodingException {

        createCode(); //인증 코드 생성
        String setFrom = "vkdnjdjxor@naver.com"; //email-config에 설정한 자신의 이메일 주소(보내는 사람)
        String toEmail = email; //받는 사람
        String title = "[MAT_T] 임시 비밀번호 메일"; //제목
        String content = System.getProperty("line.separator") + "안녕하세요 회원님" + System.lineSeparator()
                + "임시 비밀번호는 " + temPasswd + " 입니다." +  "원하는 비밀번호로 변경해 주시기 바랍니다."
                + System.lineSeparator();


        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email); //보낼 이메일 주소
        message.setSubject(title); //제목 설정
        message.setFrom(setFrom); //보내는 이메일 주소
        message.setText(content, "utf-8", "html");

        return message;
    }

    //실제 메일 전송
    public String sendEmail(String toEmail) throws MessagingException, UnsupportedEncodingException {

        //메일전송에 필요한 정보 설정
        MimeMessage emailForm = createEmailForm(toEmail);
        //실제 메일 전송
        emailSender.send(emailForm);

        return temPasswd;
    }
}