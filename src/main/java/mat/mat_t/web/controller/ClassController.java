package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.Classes;
import mat.mat_t.form.ClassForm;
import mat.mat_t.web.service.ClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;

    /**클래스 생성**/
    @ApiOperation(value="신규 클래스 생성")
    @PostMapping(value = "/class/new")
    public ResponseEntity<Classes> createClass(@Valid @RequestBody ClassForm form, BindingResult bindingResult) {

        Classes classes = new Classes(form);

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(classes);
        }

        classService.saveClass(classes);
        return ResponseEntity.ok().body(classes);
    }

    /**클래스 수정**/
    /*클래스 아이디, 강사아이디, 코드아이디는 변경안되게 함*/
    @ApiOperation(value="클래스 수정")
    @PatchMapping("/class/edit/{classId}")
    public ResponseEntity<Classes> updateClass(@Valid @RequestBody ClassForm form, @PathVariable Long classId) {
        Classes classes = new Classes(form.getClassId(), form.getTitle(), form.getNumberOfStudents(),form.getDescriptions(), form.getPlace(),
                form.getStartTime(), form.getEndTime(), form.getCategory(), form.getStartDate(), form.getEndDate());
        classService.updateClass(classes, classId);
        return ResponseEntity.ok().body(classes);
    }


    /** 전체 클래스 조회**/
    /*
     * clasForm 을 dto 로 사용하여 전체 클래스 출력하기
     * return 결과는 body 에 담아둘 것
     */
    @ApiOperation(value="전체 클래스 조회")
    @GetMapping(value = "/class")
    public ResponseEntity<List<ClassForm>> findAll() {
        List<Classes> classes = new ArrayList<>();
        List<ClassForm> list = new ArrayList<>();

        classes = classService.findAllClass();
        classes.forEach(el -> {
            ClassForm data = new ClassForm(el);
            list.add(data);
        });

        return ResponseEntity.ok().body(list);
    }


    /** 클래스 아이디 조회**/
    @ApiOperation(value="클래스 아이디로 조회")
    @GetMapping("/class/{classId}")
    public ResponseEntity<List<ClassForm>> findClassById( @PathVariable Long classId) {
        Classes classes = new Classes();
        List<ClassForm> list = new ArrayList<>();

        classes = classService.findById(classId);
        ClassForm data = new ClassForm(classes);
        list.add(data);
        return ResponseEntity.ok().body(list);
    }

    /**강사 아이디로 클래스 조회**/
    @ApiOperation(value="클래스 강사아이디로 조회")
    @GetMapping("/class/instructor/{instructorId}")
    public ResponseEntity<List<ClassForm>> findClassByInstructorId( @PathVariable Long instructorId) {
        List<Classes> classes = new ArrayList<>();
        List<ClassForm> list = new ArrayList<>();

        classes = classService.findByInstructorId(instructorId);
        classes.forEach(el -> {
            ClassForm data = new ClassForm(el);
            list.add(data);
        });

        return ResponseEntity.ok().body(list);
    }

    /**클래스 삭제**/
    @ApiOperation(value = "클래스 삭제")
    @DeleteMapping("/class/delete")
    public ResponseEntity<Classes> DeleteClass(@Valid @RequestBody ClassForm form, Long classId) {
        Classes classes = new Classes(form);
        classService.deleteClass(classId);
        return ResponseEntity.ok().body(classes);
    }


    /**현재 날짜에 따른 진행상황 조회**/

    /**시작 전 강의**/
    @ApiOperation(value="진행 전 클래스 조회")
    @GetMapping(value = "/class/before")
    public ResponseEntity<List<Classes>> findBeforeDo() {
        List<Classes> classes = new ArrayList<>();
        classes = classService.findBefore();

        return ResponseEntity.ok().body(classes);
    }

    /**진행 중인 강의**/
    @ApiOperation(value="진행 중인 클래스 조회")
    @GetMapping(value = "/class/doing")
    public ResponseEntity<List<Classes>> findDoingNow() {
        List<Classes> classes = new ArrayList<>();
        classes = classService.findNow();

        return ResponseEntity.ok().body(classes);
    }

    /**진행 완료 강의**/
    @ApiOperation(value="진행 완료인 클래스 조회")
    @GetMapping(value = "/class/finished")
    public ResponseEntity<List<Classes>> findAfterDo() {
        List<Classes> classes = new ArrayList<>();
        classes = classService.findAfter();

        return ResponseEntity.ok().body(classes);
    }
}
