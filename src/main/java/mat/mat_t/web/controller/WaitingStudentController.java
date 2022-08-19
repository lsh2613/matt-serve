package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.Classes;
import mat.mat_t.domain.class_.WaitingStudent;
import mat.mat_t.domain.user.User;
import mat.mat_t.web.service.ClassService;
import mat.mat_t.web.service.WaitingStudentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Enumeration;

@RestController
@RequiredArgsConstructor
public class WaitingStudentController {

    private final WaitingStudentsService waitingStudentsService;
    private final ClassService classService;

    @ApiOperation("클래스 신청한 학생 DB에 저장")
    @PostMapping("/waitingStudent/add/{classId}")
    public ResponseEntity add(@PathVariable Long classId,
                              @RequestParam String content,
                              HttpServletRequest request){

        HttpSession session = request.getSession(false);
        User loginUser = (User)session.getAttribute("loginUser");

        WaitingStudent waitingStudent = new WaitingStudent();
        waitingStudent.setUserWS(loginUser);
        waitingStudent.setClassesWS(classService.findOne(classId));
        waitingStudent.setContent(content);

        return ResponseEntity.ok(waitingStudent);
    }

}
