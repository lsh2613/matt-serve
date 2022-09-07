package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassStudents;
import mat.mat_t.domain.review.InstructorReview;
import mat.mat_t.domain.review.StudentReview;
import mat.mat_t.form.InstructorReviewForm;
import mat.mat_t.form.StudentReviewForm;
import mat.mat_t.web.service.ClassStudentsService;
import mat.mat_t.web.service.InstructorReviewService;
import mat.mat_t.web.service.StudentReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final StudentReviewService studentReviewService;
    private final InstructorReviewService instructorReviewService;
    private final ClassStudentsService classStudentsService;

    @ApiOperation(value = "수업 리뷰저장")
    @PostMapping("instructorReview")
    public ResponseEntity<InstructorReview> createInstructorReview(@Valid @RequestBody InstructorReviewForm form, BindingResult bindingResult) {
        InstructorReview instructorReview = new InstructorReview(form.getReviewContent(), form.getScore());
        instructorReviewService.saveReview(instructorReview);

        ClassStudents student = classStudentsService.findByUserIdAndClassId(form.getStudentId(), form.getClassId());
        instructorReview = instructorReviewService.updateClassStudents(student, instructorReview);
        return ResponseEntity.ok().body(instructorReview);
    }

    @ApiOperation(value = "수업 리뷰수정")
    @PatchMapping("instructorReview")
    public ResponseEntity<InstructorReview> updateInstructorReview(@Valid @RequestBody InstructorReviewForm form, Long id) {
        InstructorReview instructorReview = new InstructorReview(form.getReviewContent(), form.getScore());
        instructorReviewService.updateInstructorReview(instructorReview, id);
        return ResponseEntity.ok().body(instructorReview);
    }

    @ApiOperation(value = "수업 리뷰삭제")
    @DeleteMapping("instructorReview")
    public ResponseEntity<InstructorReview> deleteInstructorReview(@Valid @RequestBody InstructorReviewForm form,
                                                                   Long id) {
        InstructorReview instructorReview = new InstructorReview(form.getReviewContent(), form.getScore());
        instructorReviewService.deleteReview(id);
        return ResponseEntity.ok().body(instructorReview);
    }

    @ApiOperation(value = "수업리뷰 전체 조회")
    @GetMapping("instructorReview")
    public ResponseEntity<List<InstructorReviewForm>> checkAllInstructorReviews() {
        List<InstructorReview> instructorReviews = instructorReviewService.checkAll();
        ;
        List<InstructorReviewForm> list = new ArrayList<>();
        instructorReviews.forEach(el -> {
            InstructorReviewForm instructorReviewForm = new InstructorReviewForm(el);
            list.add(instructorReviewForm);
        });
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = "수업리뷰 하나만 조회")
    @GetMapping("instructorReview/{classId}")
    public ResponseEntity<List<InstructorReviewForm>> checkInstructorReview(@PathVariable Long classId) {
        InstructorReview instructorReview = instructorReviewService.check(classId);
        List<InstructorReviewForm> list = new ArrayList<>();

        InstructorReviewForm data = new InstructorReviewForm(instructorReview);
        list.add(data);
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = "학생 리뷰저장")
    @PostMapping("studentReview")
    public ResponseEntity<StudentReview> createStudentReview(@Valid @RequestBody StudentReviewForm form) {
        StudentReview studentReview = new StudentReview(form.getMannerTemperature());
       // studentReviewService.averageTemperature(studentReview,form.getMannerTemperature());
        studentReviewService.saveReview(studentReview);

        ClassStudents student = classStudentsService.findByUserIdAndClassId(form.getStudentId(), form.getClassId());
        studentReview = studentReviewService.updateClassStudents(student, studentReview);

        return ResponseEntity.ok().body(studentReview);
    }

    @ApiOperation(value = "학생 리뷰수정")
    @PatchMapping("studentReview")
    public ResponseEntity<StudentReview> updateStudentReview(@Valid @RequestBody StudentReviewForm form, Long id) {
        StudentReview studentReview = new StudentReview(form.getMannerTemperature());
        studentReviewService.updateStudentReview(studentReview, id);
        return ResponseEntity.ok().body(studentReview);
    }

    @ApiOperation(value = "학생 리뷰삭제")
    @DeleteMapping("studentReview")
    public ResponseEntity<StudentReview> deleteStudentReview(@Valid @RequestBody StudentReviewForm form, Long id) {
        StudentReview studentReview = new StudentReview(form.getMannerTemperature());
        studentReviewService.deleteReview(id);
        return ResponseEntity.ok().body(studentReview);
    }

    @ApiOperation(value = "학생 리뷰전체 조회")
    @GetMapping("studentReview")
    public ResponseEntity<List<StudentReviewForm>> checkAllStudentReviews() {
        List<StudentReview> studentReviews = studentReviewService.checkAll();
        List<StudentReviewForm> list = new ArrayList<>();
        studentReviews.forEach(el -> {
            StudentReviewForm studentReviewForm = new StudentReviewForm(el);
            list.add(studentReviewForm);
        });
        return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = "학생리뷰 하나만 조회")
    @GetMapping("studentReview/{classId}")
    public ResponseEntity<List<StudentReviewForm>> checkStudentReview(@PathVariable Long classId) {
        StudentReview studentReview = studentReviewService.check(classId);
        List<StudentReviewForm> list = new ArrayList<>();
        StudentReviewForm data = new StudentReviewForm(studentReview);
        list.add(data);
        return ResponseEntity.ok().body(list);
    }

    /**
     * 클래스 이름으로 리뷰 조회하는거
     */

    @ApiOperation(value="classId로 리뷰 조회")
    @GetMapping("/instructorReview/{id}}")
    public ResponseEntity<List<InstructorReviewForm>> findInstructorReviewByClassId(@PathVariable Long id) {
        List<InstructorReview> instructorReviews = new ArrayList<>();
        List<InstructorReviewForm> list = new ArrayList<>();

        instructorReviews = instructorReviewService.findReviewByClassId(id);
        instructorReviews.forEach(el -> {
            InstructorReviewForm data = new InstructorReviewForm(el);
            list.add(data);
        });

        return ResponseEntity.ok().body(list);
    }

    /**
     * 입력점수 이상의 리뷰들이 나오게함
     * 만약 50을 입력하면 50점이상들이 나오게함
     */

    @ApiOperation(value="점수로 리뷰 조회")
    @GetMapping("/instructorReviews/{score}}")
    public ResponseEntity<List<InstructorReviewForm>> findInstructorReviewByScore(@PathVariable float score) {
        List<InstructorReview> instructorReviews = new ArrayList<>();
        List<InstructorReviewForm> list = new ArrayList<>();

        instructorReviews = instructorReviewService.findReviewByScore(score);
        instructorReviews.forEach(el -> {
            InstructorReviewForm data = new InstructorReviewForm(el);
            list.add(data);
        });

        return ResponseEntity.ok().body(list);
    }

    /**
     *  닉네임으로 학생 리뷰 조회하는거
     */

    @ApiOperation(value="UserId 로 리뷰 조회")
    @GetMapping("/studentReview/{id}}")
    public ResponseEntity<List<StudentReviewForm>> findStudentReviewByUserId(@PathVariable Long id) {
        List<StudentReview> studentReviews = new ArrayList<>();
        List<StudentReviewForm> list = new ArrayList<>();

        studentReviews = studentReviewService.findReviewByUserId(id);
        studentReviews.forEach(el -> {
            StudentReviewForm data = new StudentReviewForm(el);
            list.add(data);
        });

        return ResponseEntity.ok().body(list);
    }

//    @ApiOperation(value="온도로 리뷰 조회")
//    @GetMapping("/studentReviews/{temperature}}")
//    public ResponseEntity<List<StudentReviewForm>> findStudentReviewByTemperature(@PathVariable float temperature) {
//        List<StudentReview> studentReviews = new ArrayList<>();
//        List<StudentReviewForm> list = new ArrayList<>();
//
//        studentReviews = studentReviewService.findReviewByMannerTemperature(temperature);
//        studentReviews.forEach(el -> {
//            StudentReviewForm data = new StudentReviewForm(el);
//            list.add(data);
//        });
//
//        return ResponseEntity.ok().body(list);
//    }
}