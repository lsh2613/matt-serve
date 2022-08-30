package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassDay;
import mat.mat_t.form.ClassDayForm;
import mat.mat_t.web.service.ClassDayService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClassDayController {

    private final ClassDayService classDayService;

    /**
     * 클래스데이의 경우 다중값 속성을 위해 만든 테이블로
     * 예시로
     * 1클래스 - 월요일
     * 1클래스 -화요일
     * 2클래스 -수요일
     * 이런식으로 한 클래스가 여러 개의 요일을 가질 수 있어서
     * 수정이 아닌 생성, 삭제를 통해 관리해야 할 것 같음
     * **/

    /**클래스데이 생성**/
    @ApiOperation(value="클래스데이 생성")
    @PostMapping(value = "/classDay/new")
    public ResponseEntity<ClassDay> createClassDay(@Valid @RequestBody ClassDayForm form, BindingResult bindingResult) {

        ClassDay classDay = new ClassDay(form);

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(classDay);
        }

        //중복검사
        if(classDayService.checkClassesAndDaysDuplicate(form.getClassesID(), form.getDayId())){
            throw new IllegalStateException("이미 존재하는 클래스데이입니다.");
        }
        classDayService.saveClassDay(classDay);
        return ResponseEntity.ok().body(classDay);
    }

    /**클래스데이 삭제**/
    @ApiOperation(value = "클래스데이 삭제")
    @DeleteMapping("classDay/delete")
    public ResponseEntity<ClassDay> DeleteClassDay(@Valid @RequestBody ClassDayForm form, Long classDayId) {
        ClassDay classDay = new ClassDay(form);
        classDayService.deleteClassDay(classDayId);
        return ResponseEntity.ok().body(classDay);
    }

    /**클래스 아이디로 클래스데이 조회**/
    @ApiOperation(value="클래스데이 클래스아이디로 조회")
    @GetMapping("/classDay/classId/{classId}")
    public ResponseEntity<List<ClassDayForm>> findClassByClassId(@PathVariable Long classId) {
        List<ClassDay> classDays = new ArrayList<>();
        List<ClassDayForm> list = new ArrayList<>();

        classDays = classDayService.findClassDayByClassId(classId);
        classDays.forEach(el -> {
            ClassDayForm data = new ClassDayForm(el);
            list.add(data);
        });

        return ResponseEntity.ok().body(list);
    }

    /**day 아이디로(= ex. 1---> 월요일을 뜻함) 클래스데이 조회**/
    @ApiOperation(value="클래스데이 day 아이디로 조회")
    @GetMapping("/classDay/dayId/{dayId}")
    public ResponseEntity<List<ClassDayForm>> findClassByDayId(@PathVariable Long dayId) {
        List<ClassDay> classDays = new ArrayList<>();
        List<ClassDayForm> list = new ArrayList<>();

        classDays = classDayService.findClassDayByDayId(dayId);
        classDays.forEach(el -> {
            ClassDayForm data = new ClassDayForm(el);
            list.add(data);
        });

        return ResponseEntity.ok().body(list);
    }

    /**dayName 으로(= ex. 월요일) 바로 클래스데이 조회**/
    @ApiOperation(value="클래스데이 dayName으로 조회")
    @GetMapping("/classDay/dayName/{dayName}")
    public ResponseEntity<List<ClassDayForm>> findClassByDayName(@PathVariable String dayName) {
        List<ClassDay> classDays = new ArrayList<>();
        List<ClassDayForm> list = new ArrayList<>();

        classDays = classDayService.findClassDayByDayName(dayName);
        classDays.forEach(el -> {
            ClassDayForm data = new ClassDayForm(el);
            list.add(data);
        });

        return ResponseEntity.ok().body(list);
    }

}
