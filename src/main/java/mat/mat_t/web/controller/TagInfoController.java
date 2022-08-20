package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassInformation;
import mat.mat_t.domain.class_.Classes;
import mat.mat_t.domain.class_.TagInfo;
import mat.mat_t.domain.user.Instructor;
import mat.mat_t.form.ClassForm;
import mat.mat_t.web.service.TagInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TagInfoController {

    private final TagInfoService tagInfoService;

    /** 태그 생성 **/

    @ApiOperation(value="신규 클래스 생성")
    @GetMapping("/TagInfo/new")
    public String createForm(Model model) {
        List<TagInfo> tagInfo = tagInfoService.findTagInfo();

        model.addAttribute("TagInfo", tagInfo);
        model.addAttribute("form", new ClassForm());
        return "TagInfo/createTagInfoForm";
    }

    @ApiOperation(value="신규 태그 정보 생성")
    @GetMapping("/TagInfo/new")
    public String TagInfos(@RequestParam("tagInfoId") Long tagInfo_Id,
                           @RequestParam("tagName") String tag_Name) {
        tagInfoService.createTagInfo(tagInfo_Id, tag_Name);
        return "redirect:/tagInfo";
    }

    /** 전체 태그 정보 조회**/
    @ApiOperation(value="전체 태그 정보 조회")
    @GetMapping(value = "/tagInfo")
    public String list(Model model) {
        List<TagInfo> tagInfo= tagInfoService.findTagInfo();
        model.addAttribute("tagInfo", tagInfo);
        return "tagInfo/tagInfoList";
    }

    /** 태그 아이디 조회**/
    @ApiOperation(value="태그 아이디로 조회")
    @GetMapping("/tagInfo/{tagInfoId}")
    public TagInfo getTagInfoById(@PathVariable Long tagInfoId) {
        return tagInfoService.findOne(tagInfoId);
    }

}
