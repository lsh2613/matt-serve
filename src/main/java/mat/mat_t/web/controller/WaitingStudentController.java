package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.Getter;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class WaitingStudentController {

    private final WaitingStudentsService waitingStudentsService;
    private final ClassService classService;

    @ApiOperation("클래스 신청한 학생 DB에 저장")
    @PostMapping("/waitingStudent/add/{classId}")
    public ResponseEntity add(@PathVariable Long classId,
                              @RequestParam String content,
                              HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        User loginUser = (User) session.getAttribute("loginUser");

        WaitingStudent waitingStudent = new WaitingStudent();
        waitingStudent.setUserWS(loginUser);
        waitingStudent.setClassesWS(classService.findById(classId));
        waitingStudent.setContent(content);
        waitingStudent.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        waitingStudentsService.add(waitingStudent);

        return ResponseEntity.ok(waitingStudent);
    }

    @ApiOperation("해당 클래스에 신청한 학생들 조회")
    @GetMapping("/waitingStudent/list/{classId}")
    public ResponseEntity listStudents(@PathVariable Long classId) {
        List<WaitingStudent> classStudents = waitingStudentsService.findStudentsByClassId(classId);

        List<WsDto> classStudentsDto=classStudents.stream()
                .map(c -> new WsDto(c))
                .collect(Collectors.toList());

        return ResponseEntity.ok(classStudentsDto);
    }

    @Getter
    static class WsDto {
        Long id;
        String name;
        String content;
        String date;

        public WsDto(WaitingStudent waitingStudent) {
            id = waitingStudent.getWaitingId();
            name = waitingStudent.getUserWS().getName();
            content = waitingStudent.getContent();
            date = waitingStudent.getDate();
        }
    }


}
