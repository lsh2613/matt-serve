package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.ClassStudents;
import mat.mat_t.domain.class_.dto.InstructorReviewDto;
import mat.mat_t.domain.review.InstructorReview;
import mat.mat_t.form.InstructorReviewForm;
import mat.mat_t.web.service.ClassStudentsService;
import mat.mat_t.web.service.InstructorReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class InstructorReviewController {

    private final InstructorReviewService instructorReviewService;
    private final ClassStudentsService classStudentsService;

    @ApiOperation(value = "수업 리뷰저장")
    @PostMapping("instructor/review")
    public ResponseEntity<InstructorReview> createInstructorReview(@Valid @RequestBody InstructorReviewForm form) {

        InstructorReview instructorReview = new InstructorReview(form.getReviewContent(), form.getScore());
        if (instructorReviewService.countInstructorReviews(form.getClassId(), form.getStudentId()) == 1) {
            throw new IllegalStateException("이미 등록되어 있습니다.");
        }

        if(instructorReviewService.countClassId(form.getClassId())!=1){
            throw new IllegalStateException("수강등록된 클래스가 아닙니다.");
        }

        if(instructorReviewService.countUserId(form.getStudentId())!=1){
            throw new IllegalStateException("수강등록된 학생이 아닙니다.");
        }

        instructorReviewService.saveReview(instructorReview);

        ClassStudents student = classStudentsService.findByUserIdAndClassId(form.getStudentId(), form.getClassId());
        instructorReview = instructorReviewService.updateClassStudents(student, instructorReview);
        return ResponseEntity.ok().body(instructorReview);
    }

    @ApiOperation(value = "수업 리뷰수정")
    @PatchMapping("instructor/review")
    public ResponseEntity<InstructorReview> updateInstructorReview(@Valid @RequestBody InstructorReviewForm form, Long id) {
        InstructorReview instructorReview = new InstructorReview(form.getReviewContent(), form.getScore());
        instructorReviewService.updateInstructorReview(instructorReview, id);
        return ResponseEntity.ok().body(instructorReview);
    }

    @ApiOperation(value = "수업 리뷰삭제")
    @DeleteMapping("instructor/review")
    public ResponseEntity<InstructorReview> deleteInstructorReview(@Valid @RequestBody InstructorReviewForm form,
                                                                   Long id) {
        InstructorReview instructorReview = new InstructorReview(form.getReviewContent(), form.getScore());
        instructorReviewService.deleteReview(id);
        return ResponseEntity.ok().body(instructorReview);
    }

    @ApiOperation(value = "수업리뷰 전체 조회")
    @GetMapping("instructor/review/all")
    public ResponseEntity<List<InstructorReviewDto>> checkAllInstructorReviews() {
        List<InstructorReview> instructorReviews = instructorReviewService.checkAll();
        List<InstructorReviewDto> instructorReviewDtoList=new ArrayList<>();

        for(int i=0;i<instructorReviews.size();i++){
            instructorReviewDtoList.add(new InstructorReviewDto(instructorReviews.get(i)));
        }

        return ResponseEntity.ok().body(instructorReviewDtoList);
    }

    @ApiOperation(value = "수업리뷰 하나만 조회")
    @GetMapping("instructor/review/{reviewId}")
    public ResponseEntity<InstructorReviewDto> checkInstructorReview(@PathVariable Long reviewId) {

        InstructorReview instructorReview = instructorReviewService.check(reviewId);
        InstructorReviewDto instructorReviewDto=new InstructorReviewDto(instructorReview);

        return ResponseEntity.ok().body(instructorReviewDto);
    }

    /**
     * 클래스ID로 리뷰 조회하는거
     */

    @ApiOperation(value = "classId로 리뷰 조회")
    @GetMapping("/instructor/review/classId/{classId}}")
    public ResponseEntity<List<InstructorReviewDto>> findInstructorReviewByClassId(@PathVariable Long classId) {
        List<InstructorReview> instructorReviews = new ArrayList<>();
        List<InstructorReviewDto> instructorReviewDtoList = new ArrayList<>();

        instructorReviews = instructorReviewService.findReviewByClassId(classId);

        for (int i = 0; i < instructorReviews.size(); i++) {
            instructorReviewDtoList.add(new InstructorReviewDto(instructorReviews.get(i)));
        }

        return ResponseEntity.ok().body(instructorReviewDtoList);
    }

    /**
     * 입력점수 이상의 리뷰들이 나오게함
     * 만약 50을 입력하면 50점이상들이 나오게함
     */

    @ApiOperation(value = "점수로 리뷰 조회")
    @GetMapping("/instructor/review/score/{score}}")
    public ResponseEntity<List<InstructorReviewDto>> findInstructorReviewByScore(@PathVariable float score) {
        List<InstructorReview> instructorReviews = new ArrayList<>();
        List<InstructorReviewDto> instructorReviewDtoList = new ArrayList<>();

        instructorReviews = instructorReviewService.findReviewByScore(score);

        for (int i = 0; i < instructorReviews.size(); i++) {
            instructorReviewDtoList.add(new InstructorReviewDto(instructorReviews.get(i)));
        }

        return ResponseEntity.ok().body(instructorReviewDtoList);
    }
}
