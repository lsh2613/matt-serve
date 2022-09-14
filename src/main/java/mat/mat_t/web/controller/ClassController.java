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

    /** 클래스 생성 **/
    @ApiOperation(value = "신규 클래스 생성")
    @PostMapping(value = "/class")
    public ResponseEntity<Classes> createClass(@Valid @RequestBody ClassForm form, BindingResult bindingResult) {

        Classes classes = new Classes(form);

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(classes);
        }

        classService.saveClass(classes);
        return ResponseEntity.ok().body(classes);
    }

    /** 클래스 수정 **/
    /* 클래스 아이디, 강사아이디, 코드아이디는 변경안되게 함 */
    @ApiOperation(value = "클래스 수정")
    @PatchMapping("/class/{classId}")
    public ResponseEntity<Classes> updateClass(@Valid @RequestBody ClassForm form, @PathVariable Long classId) {
        Classes classes = new Classes(form.getClassId(), form.getTitle(), form.getNumberOfStudents(),
                form.getDescriptions(), form.getPlace(),
                form.getStartTime(), form.getEndTime(), form.getCategory(), form.getStartDate(), form.getEndDate());
        classService.updateClass(classes, classId);
        return ResponseEntity.ok().body(classes);
    }

    /** 전체 클래스 조회 **/
    /*
     * clasForm 을 dto 로 사용하여 전체 클래스 출력하기
     * return 결과는 body 에 담아둘 것
     */
    @ApiOperation(value = "전체 클래스 조회")
    @GetMapping(value = "/class")
    public ResponseEntity<List<ClassForm>> findAll() {
        List<ClassForm> classForms = new ArrayList<>();

        classForms = classService.findAllClass();
        return ResponseEntity.ok().body(classForms);
    }

    /** 클래스 아이디 조회 **/
    @ApiOperation(value = "클래스 아이디로 조회")
    @GetMapping("/class/{classId}")
    public ResponseEntity<ClassForm> findClassById(@PathVariable Long classId) {
        ClassForm classForm = new ClassForm();

        classForm = classService.findByClassId(classId);
        return ResponseEntity.ok().body(classForm);
    }

    /** 강사 아이디로 클래스 조회 **/
    @ApiOperation(value = "클래스 강사아이디로 조회")
    @GetMapping("/class/instructor/{instructorId}")
    public ResponseEntity<List<ClassForm>> findClassByInstructorId(@PathVariable Long instructorId) {
        List<ClassForm> classForms = new ArrayList<>();

        classForms = classService.findByInstructorId(instructorId);
        return ResponseEntity.ok().body(classForms);
    }

    /** 클래스 삭제 **/
    @ApiOperation(value = "클래스 삭제")
    @DeleteMapping("/class")
    public ResponseEntity<Classes> DeleteClass(@Valid @RequestBody ClassForm form, Long classId) {
        Classes classes = new Classes(form);
        classService.deleteClass(classId);
        return ResponseEntity.ok().body(classes);
    }

    /** 현재 날짜에 따른 진행상황 조회 **/

    /** 시작 전 강의 **/
    @ApiOperation(value = "진행 전 클래스 조회")
    @GetMapping(value = "/class/before")
    public ResponseEntity<List<ClassForm>> findBeforeDo() {
        List<ClassForm> classForms = new ArrayList<>();
        classForms = classService.findBefore();

        return ResponseEntity.ok().body(classForms);
    }

    /** 진행 중인 강의 **/
    @ApiOperation(value = "진행 중인 클래스 조회")
    @GetMapping(value = "/class/doing")
    public ResponseEntity<List<ClassForm>> findDoingNow() {
        List<ClassForm> classForms = new ArrayList<>();
        classForms = classService.findNow();

        return ResponseEntity.ok().body(classForms);
    }

    /** 진행 완료 강의 **/
    @ApiOperation(value = "진행 완료인 클래스 조회")
    @GetMapping(value = "/class/finished")
    public ResponseEntity<List<ClassForm>> findAfterDo() {
        List<ClassForm> classForms = new ArrayList<>();
        classForms = classService.findAfter();

        return ResponseEntity.ok().body(classForms);
    }

    /** 요일 검색으로 해당 클래스들 조회 **/
    @ApiOperation(value = "요일 검색으로 해당 클래스들 조회")
    @GetMapping("/class/dayName/{dayName}/classesView")
    public ResponseEntity<List<ClassForm>> findClassByDayName(@PathVariable String dayName) {
        List<ClassForm> classForms = new ArrayList<>();

        classForms = classService.findByDayName(dayName);

        return ResponseEntity.ok().body(classForms);
    }

    /**
     * 키워드로 클래스 조회(title, category, description, place 에서 검색)
     * 찾고자하는 문자열이 포함되어 있으면 검색되도록 함( %문자%)
     **/
    @ApiOperation(value = "키워드 검색으로 해당 클래스들 조회")
    @GetMapping("/class/keyword/{keyword}/classesView")
    public ResponseEntity<List<ClassForm>> findClassByKeyword(@PathVariable String keyword) {
        List<ClassForm> classForms = new ArrayList<>();

        classForms = classService.findByKeyword(keyword);
        return ResponseEntity.ok().body(classForms);
    }
}
