package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.TagInfo;
import mat.mat_t.form.TagInfoForm;
import mat.mat_t.web.service.TagInfoService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TagInfoController {

    private final TagInfoService tagInfoService;

    /**
     * 태그 정보 생성
     **/

    @ApiOperation(value = "태그 정보 생성")
    @PostMapping("/tagInfo")
    public ResponseEntity<TagInfo> createTagInfo(@Valid @RequestBody TagInfoForm form) {
        TagInfo tagInfo = new TagInfo(form);
        tagInfoService.createTagInfo(tagInfo);

        return ResponseEntity.ok().body(tagInfo);
    }

    /**
     * 태그 정보 수정
     **/

    @ApiOperation(value = "태그 정보 수정")
    @PatchMapping("/tagInfo/update")
    public ResponseEntity<TagInfo> updateTagInfo(@Valid @RequestParam("tagInfoId") Long tagInfoId,
                                                 @RequestParam("tagInfoName")String tagInfoName) {
        TagInfo tagInfo = new TagInfo(tagInfoId, tagInfoName);
        tagInfoService.updateTagInfo(tagInfoId, tagInfoName);

        return ResponseEntity.ok().body(tagInfo);
    }

    /**
     * 태그 정보 삭제
     **/

    @ApiOperation(value = "태그 정보 삭제")
    @DeleteMapping("tagInfo/delete")
    public ResponseEntity<TagInfo> TagInfoDelete(@Valid @RequestBody TagInfoForm form) {
        TagInfo tagInfo = new TagInfo(form.getTagInfoId(), form.getTagName());
        tagInfoService.deleteTagInfo(tagInfo);
        return ResponseEntity.ok().body(tagInfo);
    }

    /**
     * 전체 태그 정보 조회
     **/

    @ApiOperation(value = "전체 태그 정보 조회")
    @GetMapping(value = "/tagInfo")
    public ResponseEntity<List<TagInfo>> list() {
        List<TagInfo> tagInfo = tagInfoService.findTagInfo();

        return ResponseEntity.ok().body(tagInfo);
    }

    /**
     * 태그 아이디 조회
     **/

    @ApiOperation(value = "태그 아이디로 조회")
    @GetMapping("/tagInfo/{tagInfoId}")
    public ResponseEntity<TagInfo> getTagInfoById(@PathVariable Long tagInfoId) {
        TagInfo tagInfo = tagInfoService.findByTagId(tagInfoId);

        return ResponseEntity.ok().body(tagInfo);
    }

    @ApiOperation(value = "태그 이름으로 조회")
    @GetMapping("/tagInfos/{tagName}")
    public ResponseEntity<TagInfo> getTagInfoByTagName(@PathVariable String tagName) {
        TagInfo tagInfo = tagInfoService.findByTagInfoName(tagName);
        return ResponseEntity.ok().body(tagInfo);
    }

}
