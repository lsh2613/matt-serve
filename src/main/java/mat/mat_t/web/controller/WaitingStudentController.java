package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassStatus;
import mat.mat_t.domain.class_.ClassStudents;
import mat.mat_t.domain.class_.WaitingStudent;
import mat.mat_t.domain.user.User;
import mat.mat_t.web.service.ClassService;
import mat.mat_t.web.service.ClassStudentsService;
import mat.mat_t.web.service.WaitingStudentsService;
import mat.mat_t.web.service.WishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class WaitingStudentController {

    private final WaitingStudentsService waitingStudentsService;
    private final ClassService classService;
    private final ClassStudentsService classStudentsService;
    private final WishService wishService;

    @ApiOperation("클래스 신청한 학생 DB에 저장")
    @PostMapping("/waitingStudent/{classId}")
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
    @GetMapping("/waitingStudents/{classId}")
    public ResponseEntity listStudents(@PathVariable Long classId) {
        List<WaitingStudent> classStudents = waitingStudentsService.findStudentsByClassId(classId);

        List<WsDto> classStudentsDto = classStudents.stream()
                .map(c -> new WsDto(c))
                .collect(Collectors.toList());

        return ResponseEntity.ok(classStudentsDto);
    }

    @ApiOperation("wsId로 waitingStuent 보기")
    @GetMapping("/waitingStudent/{wsId}")
    public ResponseEntity waitingStudent(@PathVariable Long wsId) {
        WaitingStudent waitingStudent=waitingStudentsService.findWaitingStudentByWsId(wsId);

        WsDto wsDto=new WsDto(waitingStudent);

        return ResponseEntity.ok(wsDto);
    }

    @ApiOperation("클래스 신청 수정")
    @PatchMapping("/waitingStudent/{wsId}")
    public ResponseEntity editWs(@PathVariable Long wsId,
                                 @RequestBody String content) {
        WaitingStudent updateStudent = waitingStudentsService.update(wsId, content);
        return ResponseEntity.ok(updateStudent);
    }

    @ApiOperation("클래스 신청 삭제")
    @DeleteMapping("/waitingStudent/{wsId}")
    public void deleteWs(@PathVariable Long wsId) {
        waitingStudentsService.delete(wsId);
    }

    @ApiOperation("강사가 수락 시 ws는 삭제 cs는 추가")
    @PostMapping("/waitingStudent/transfer/{wsId}")
    public ResponseEntity transferFromWsToCs(@PathVariable Long wsId) {
        ClassStudents classStudent = waitingStudentsService.transfer(wsId);
        classStudentsService.saveClassStudents(classStudent);
        CsDto csDto = new CsDto();
        csDto.setCsDto(classStudent);
        if (wishService.duplicate(classStudent.getClassesCS().getClassId(), classStudent.getUserCS().getId())) {
            wishService.deleteByClassIdAndUserId(classStudent.getClassesCS().getClassId(), classStudent.getUserCS().getId());
        }
        return ResponseEntity.ok().body(csDto);
    }

    @ApiOperation("자기꺼 아직 waiting중인 클래스들 보기")
    @GetMapping("/waitingStudent/class")
    public ResponseEntity WaitingClassByUserId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User loginUser = (User) session.getAttribute("loginUser");

        List<WaitingStudent> waitingStudents=new ArrayList<>();
        List<ClassWsDto> classWsDtos=new ArrayList<>();

        waitingStudents=waitingStudentsService.findClassByUserId(loginUser.getId());

        for(int i=0;i<waitingStudents.size();i++){
            classWsDtos.add(new ClassWsDto(waitingStudents.get(i)));
        }

        return ResponseEntity.ok().body(classWsDtos);
    }



    @Getter
    static class ClassWsDto{
         Long classId;
         Long waitingStudentId;
         String title;
         Long numberOfStudents;
         String descriptions;
         String category;

         public ClassWsDto(WaitingStudent waitingStudent){
             this.classId=waitingStudent.getClassesWS().getClassId();
             this.waitingStudentId=waitingStudent.getWaitingId();
             this.title=waitingStudent.getClassesWS().getTitle();
             this.numberOfStudents=waitingStudent.getClassesWS().getNumberOfStudents();
             this.descriptions=waitingStudent.getClassesWS().getDescriptions();
             this.category=waitingStudent.getClassesWS().getCategory();
         }
    }

    @Getter
    static class WsDto {
        Long waitingStudentId;
        String name;
        String content;
        String date;

        public WsDto(WaitingStudent waitingStudent) {
            this.waitingStudentId = waitingStudent.getWaitingId();
            this.name = waitingStudent.getUserWS().getName();
            this.content = waitingStudent.getContent();
            this.date = waitingStudent.getDate();
        }
    }

    @Getter
    static class CsDto {

        Long cs_id;
        ClassStatus status;
        Long class_id;
        Long student_id;
        String contents;
        String date;

        public void setCsDto(ClassStudents classStudents) {
            this.cs_id = classStudents.getClassStudentId();
            this.status = classStudents.getStatus();
            this.contents = classStudents.getContents();
            this.class_id = classStudents.getClassesCS().getClassId();
            this.student_id = classStudents.getUserCS().getId();
            this.date = classStudents.getDate();
        }
    }

}
