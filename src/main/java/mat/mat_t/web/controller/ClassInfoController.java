package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassInformation;
import mat.mat_t.form.ClassInfoForm;
import mat.mat_t.web.service.ClassInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClassInfoController {

    private final ClassInfoService classInfoService;

    /**클래스 정보 생성**/
    @ApiOperation(value="클래스정보 생성")
    @PostMapping(value = "/classInfo/new")
    public ResponseEntity<ClassInformation> createClassInfo(@Valid @RequestBody ClassInfoForm form, BindingResult bindingResult) {

        ClassInformation classInformation = new ClassInformation(form.getCodeId(), form.getCodeName());

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(classInformation);
        }

        classInfoService.saveClassInfo(classInformation);
        return ResponseEntity.ok().body(classInformation);
    }

    /**클래스 정보 수정**/
    @ApiOperation(value="클래스 정보 수정")
    @PatchMapping("/classInfo/{codeId}/edit")
    public ResponseEntity<ClassInformation> updateClassInfo(@Valid @RequestBody ClassInfoForm form, Long codeId) {
        ClassInformation classInformation = new ClassInformation(form.getCodeId(), form.getCodeName());
        classInfoService.updateClassInfo(classInformation, codeId);
        return ResponseEntity.ok().body(classInformation);
    }

    /**클래스 정보 조회**/
    @ApiOperation(value="클래스 정보 조회")
    @GetMapping(value = "/classInfo")
    public ResponseEntity<List<ClassInformation>> findAll() {
        return ResponseEntity.ok(classInfoService.findAllCodeInfo());
    }

    /**클래스 정보 삭제**/
    @ApiOperation(value = "클래스 정보 삭제")
    @DeleteMapping("/classInfo/delete")
    public ResponseEntity<ClassInformation> deleteClassInfo(@Valid @RequestBody ClassInfoForm form, Long codeId) {
        ClassInformation classInformation = new ClassInformation(form.getCodeId(), form.getCodeName());
        classInfoService.deleteClassInfo(codeId);
        return ResponseEntity.ok().body(classInformation);
    }

}
