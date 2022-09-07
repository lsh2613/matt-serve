package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassTag;
import mat.mat_t.form.ClassTagForm;
import mat.mat_t.web.service.ClassTagService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClassTagController {

    private final ClassTagService classTagService;

    /**
     * 태그 정보 생성
     **/

    @ApiOperation(value = "클래스 태그 생성")
    @PostMapping("/classTag/new")
    public ResponseEntity<ClassTag> createClassTag(@Valid @RequestBody ClassTagForm form) {
        ClassTag classTag = new ClassTag(form);

        //중복검사
        if (classTagService.checkClassesAndTagInfoDuplicate(form.getClassesCT(), form.getTagInfo())) {
            throw new IllegalStateException("이미 클래스에 설정된 태그 입니다.");
        }

        classTagService.createClassTag(classTag);

        return ResponseEntity.ok().body(classTag);
    }


    /**
     * 태그 정보 삭제
     **/

    @ApiOperation(value = "클래스 태그 삭제")
    @DeleteMapping("classTag/delete")
    public ResponseEntity<ClassTag> TagInfoDelete(@Valid @RequestBody ClassTagForm form, Long classTagId) {
        ClassTag classTag = new ClassTag(form);
        classTagService.deleteClassTag(classTagId);
        return ResponseEntity.ok().body(classTag);
    }


    /**
     * 클래스 아이디로 클래스 태그 정보 조회
     **/

    @ApiOperation(value = "클래스태그 클래스 아이디로 조회")
    @GetMapping(value = "/classTag/classId/{classId}")
    public ResponseEntity<List<ClassTagForm>> findClassTagByClassId(@PathVariable Long classId) {
        List<ClassTag> classTags = new ArrayList<>();
        List<ClassTagForm> list = new ArrayList<>();

        classTags = classTagService.findClassTagByClassId(classId);
        classTags.forEach(el -> {
            ClassTagForm data = new ClassTagForm(el);
            list.add(data);
        });
        return ResponseEntity.ok().body(list);
    }

    /**
     * 태그 정보 아이디로 클래스 태그 정보 조회
     **/

    @ApiOperation(value = "클래스태그 태그정보 아이디로 조회")
    @GetMapping(value = "/classTag/tagInfoId/{tagInfoId}")
    public ResponseEntity<List<ClassTagForm>> findClassTagByTagInfoId(@PathVariable Long tagInfoId) {
        List<ClassTag> classTags = new ArrayList<>();
        List<ClassTagForm> list = new ArrayList<>();

        classTags = classTagService.findClassTagByTagInfoId(tagInfoId);
        classTags.forEach(el -> {
            ClassTagForm data = new ClassTagForm(el);
            list.add(data);
        });

        return ResponseEntity.ok().body(list);
    }

    /**
     * 태그 정보 아이디 리스트로 클래스 태그 정보 조회
     **/

    @ApiOperation(value = "클래스태그 태그정보 아이디리스트로 조회")
    @GetMapping(value = "/classTag/tagInfoIdList")
    public ResponseEntity<List<ClassTagForm>> findClassTagByTagInfoIdList(@RequestParam(required = false) List<Long> tagInfoId) {
        List<ClassTag> classTags = new ArrayList<>();
        List<ClassTagForm> list = new ArrayList<>();

        classTags = classTagService.findClassTagByTagInfoIdList(tagInfoId);
        classTags.forEach(el -> {
            ClassTagForm data = new ClassTagForm(el);
            list.add(data);
        });

        return ResponseEntity.ok().body(list);
    }

}
