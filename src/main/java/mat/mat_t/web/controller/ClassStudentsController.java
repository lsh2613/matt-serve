package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassStatus;
import mat.mat_t.domain.class_.ClassStudents;
import mat.mat_t.domain.class_.Classes;
import mat.mat_t.domain.review.InstructorReview;
import mat.mat_t.domain.review.StudentReview;
import mat.mat_t.form.ClassForm;
import mat.mat_t.form.ClassStudentsForm;
import mat.mat_t.web.service.ClassStudentsService;
import mat.mat_t.web.service.InstructorReviewService;
import mat.mat_t.web.service.StudentReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClassStudentsController {

    private final ClassStudentsService classStudentsService;
    private final InstructorReviewService instructorReviewService;
    private final StudentReviewService studentReviewService;

    @ApiOperation(value = "클래스스튜던트 저장")
    @PostMapping(value = "class/students")
    public ResponseEntity<ClassStudents> createClassStudents(@Valid @RequestBody ClassStudentsForm form) {
        ClassStudents classStudents = new ClassStudents(form);
        if(classStudentsService.countClassStudents(form.getClassId(),form.getStudentId())==1){
            throw new IllegalStateException("이미 등록되어 있습니다.");
        }
        classStudentsService.saveClassStudents(classStudents);
        return ResponseEntity.ok().body(classStudents);
    }

    @ApiOperation(value="클래스스튜던트 수정")
<<<<<<< HEAD
    @PatchMapping("classStudents/update")
=======
    @PatchMapping("class/students")
>>>>>>> 2ede074a8aff9506dc9daa03fb65bec51b12935f
    public ResponseEntity<ClassStudents> updateClassStudents(@Valid @RequestBody ClassStudentsForm form, Long cs_Id) {
        ClassStudents classStudents=new ClassStudents(form.getStatus());
        classStudentsService.updateClassStudents(classStudents, cs_Id);
        return ResponseEntity.ok().body(classStudents);
    }

    @ApiOperation(value="클래스스튜던트 삭제")
<<<<<<< HEAD
    @DeleteMapping("classStudents/delete")
=======
    @DeleteMapping("class/students")
>>>>>>> 2ede074a8aff9506dc9daa03fb65bec51b12935f
    public ResponseEntity<ClassStudents> deleteClassStudents(@Valid @RequestBody ClassStudentsForm form, Long cs_Id,Long insRe_id,Long stRe_id) {
        ClassStudents classStudents=new ClassStudents();

        //review 값들 매핑정보 null 값으로 만드는거
        if(insRe_id!=null) {
            InstructorReview instructorReview=instructorReviewService.findByInsReviewId(insRe_id);
            instructorReview=instructorReviewService.deleteClassStudents(new ClassStudents(),instructorReview);
            instructorReviewService.deleteReview(insRe_id);
        }

        if(stRe_id!=null) {
            StudentReview studentReview = studentReviewService.findByStReId(stRe_id);
            studentReview=studentReviewService.deleteClassStudents(new ClassStudents(),studentReview);
            studentReviewService.deleteReview(stRe_id);
        }

        classStudentsService.deleteClassStudents(cs_Id);
        return ResponseEntity.ok().body(classStudents);
    }

    @ApiOperation(value = "클래스스튜던트 전체 조회")
    @GetMapping("class/students/all")
    public ResponseEntity<List<ClassStudentsForm>> checkAllClassStudents() {
        List<ClassStudents> classStudents= classStudentsService.checkAll();;
        List<ClassStudentsForm> list = new ArrayList<>();
        classStudents.forEach(el -> {
            ClassStudentsForm classStudentsForm = new ClassStudentsForm(el);
            list.add(classStudentsForm);
        });
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = "클레스스튜던트 하나만 조회")
    @GetMapping("class/students/{classStudentId}")
    public ResponseEntity<List<ClassStudentsForm>> checkClassStudents(@PathVariable Long classStudentId) {
        ClassStudents classStudents = classStudentsService.check(classStudentId);
        List<ClassStudentsForm> list = new ArrayList<>();

        ClassStudentsForm data = new ClassStudentsForm(classStudents);
        list.add(data);
        return ResponseEntity.ok().body(list);
    }


    /**
     * stduentid 로 검색했을때,  cs에서 status가 doing인 아이템을 클래스 정보랑 매핑해서 리턴하는 api 하나
     */

    @ApiOperation(value="userId랑 status 검색하면 클래스 정보 나오는거")
    @GetMapping("/class/students/{status}/{userId}}")
    public ResponseEntity<List<ClassStudentsForm>> findClassStudentsByUserIdAndStatus(@PathVariable Long userId,ClassStatus status) {
        List<ClassStudents> classStudents = new ArrayList<>();
        List<ClassStudentsForm> list = new ArrayList<>();

        List<Classes> classes = new ArrayList<>();
        List<ClassForm> list =  new ArrayList<>();

        classStudents = classStudentsService.findClassStudentsByUserIdAndStatus(userId,status);
        classStudents.forEach(el -> {
            ClassStudentsForm data = new ClassStudentsForm(el);
            list.add(data);
        });

        return ResponseEntity.ok().body(list);
    }

}