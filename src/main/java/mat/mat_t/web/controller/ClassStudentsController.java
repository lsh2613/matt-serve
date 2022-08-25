package mat.mat_t.web.controller;

import lombok.RequiredArgsConstructor;
import mat.mat_t.web.service.ClassStudentsService;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ClassStudentsController {
    private final ClassStudentsService classStudentsService;


}
