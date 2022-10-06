package mat.mat_t.web.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.Classes;
import mat.mat_t.domain.class_.Wish;
import mat.mat_t.domain.class_.dto.WishDto;
import mat.mat_t.domain.user.User;
import mat.mat_t.web.service.ClassService;
import mat.mat_t.web.service.WishService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;
    private final ClassService classService;

    @ApiOperation(value = "찜하기")
    @PostMapping("wish/{classId}")
    public ResponseEntity putWishClass(@PathVariable Long classId, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        User loginUser = (User) session.getAttribute("loginUser");

        Classes classes = classService.findClassByClassId(classId);

        Wish wish = new Wish(classes, loginUser);

        if (wishService.duplicate(classId, loginUser.getId())) {
            throw new IllegalStateException("이미 찜했습니다.");
        }

        wishService.save(wish);

        return null;
    }

    @ApiOperation(value = "찜삭제하기")
    @DeleteMapping("wish/{classId}")
    public ResponseEntity deleteWishClass(@PathVariable Long classId, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        User loginUser = (User) session.getAttribute("loginUser");

        wishService.deleteByClassIdAndUserId(classId,loginUser.getId());

        return null;
    }

    @ApiOperation(value = "찜한 목록보기")
    @GetMapping("wish")
    public ResponseEntity myWishView(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        User loginUser = (User) session.getAttribute("loginUser");

        List<Wish> wishList = new ArrayList<>();
        List<WishDto> wishDtoList = new ArrayList<>();

        wishList = wishService.findByUserId(loginUser.getId());

        for (int i = 0; i < wishList.size(); i++) {
            wishDtoList.add(new WishDto(wishList.get(i)));
        }

        return ResponseEntity.ok().body(wishDtoList);
    }
}
