package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassInformation;
import mat.mat_t.domain.class_.Classes;
import mat.mat_t.domain.user.Instructor;
import mat.mat_t.form.ClassForm;
import mat.mat_t.web.service.ClassInfoService;
import mat.mat_t.web.service.ClassService;
import mat.mat_t.web.service.InstructorService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;
    private final InstructorService instructorService;
    private final ClassInfoService classInfoService;

    /** 클래스 생성**/
    @ApiOperation(value="신규 클래스 생성")
    @GetMapping("/class/new")
    public String createForm(Model model) {
        //instructService, classInfoService 만들어야함
        List<Instructor> instructorId = instructorService.findInstructorId();
        List<ClassInformation> codeId = classInfoService.findCodeId();
        model.addAttribute("instructorId", instructorId);
        model.addAttribute("codeId", codeId);
        model.addAttribute("form", new ClassForm());
        return "class/createClassForm";
    }

    @ApiOperation(value="신규 클래스 생성")
    @PostMapping(value = "/class/new")
    public String classes(@RequestParam("codeId") Long codeId,
                        @RequestParam("instructorId") Long instructorId,
                          @RequestParam("title") String  title,
                          @RequestParam("numberOfStudents") Long  numberOfStudents,
                          @RequestParam("descriptions") String  descriptions,
                          @RequestParam("place") String  place,
                          @RequestParam("startTime") String  startTime,
                          @RequestParam("endTime") String  endTime,
                          @RequestParam("days") String  days,
                          @RequestParam("category") String  category,
                          @RequestParam("date") Long  date) {
        classService.create(codeId, instructorId, title, numberOfStudents, descriptions, place, startTime, endTime, days, category, date);
        return "redirect:/class";
    }


    /**클래스 수정**/
    @ApiOperation(value="클래스 수정")
    @GetMapping(value = "/class/{classId}/edit")
    public String updateClassForm(@PathVariable("classId") Long classId, Model model) {
        Classes classes = (Classes) classService.findOne(classId);
        ClassForm form = new ClassForm();
        form.setClassId(classes.getClassId());
        form.setTitle(classes.getTitle());
        form.setNumberOfStudents(classes.getNumberOfStudents());
        form.setDescriptions(classes.getDescriptions());
        form.setPlace(classes.getPlace());
        form.setStartTime(classes.getStartTime());
        form.setEndTime(classes.getEndTime());
        form.setDays(classes.getDays());
        form.setCategory(classes.getCategory());
        form.setDate(classes.getDate());
        model.addAttribute("form", form);
        return "class/updateClassForm";
    }

    @ApiOperation(value="클래스 수정")
    @PostMapping(value = "/class/{classId}/edit")
    public String updateClass(@ModelAttribute("form") ClassForm form) {
        Classes classes = new Classes();
        classes.setClassId(form.getClassId());
        classes.setTitle(form.getTitle());
        classes.setNumberOfStudents(form.getNumberOfStudents());
        classes.setDescriptions(form.getDescriptions());
        classes.setPlace(form.getPlace());
        classes.setStartTime(form.getStartTime());
        classes.setEndTime(form.getEndTime());
        classes.setDays(form.getDays());
        classes.setCategory(form.getCategory());
        classes.setDate(form.getDate());
        classService.updateClass(classes);
        return "redirect:/class";
    }


    /** 전체 클래스 조회**/
    @ApiOperation(value="전체 클래스 조회")
    @GetMapping(value = "/class")
    public String list(Model model) {
        List<Classes> classes = classService.findClasses();
        model.addAttribute("classes", classes);
        return "class/classList";
    }

    /** 클래스 아이디 조회**/
    @ApiOperation(value="클래스 아이디로 조회")
    @GetMapping("/class/{classId}")
    public Classes getClassById(@PathVariable Long classId) {
        return classService.findOne(classId);
    }

    /*
    //클래스 삭제
    @ApiOperation(value="클래스 삭제")
    @DeleteMapping("/class/{classId}")
    public void delete(@PathVariable("classId") Long classId){
        ??
        return "redirect:/";
    }

    */

}
