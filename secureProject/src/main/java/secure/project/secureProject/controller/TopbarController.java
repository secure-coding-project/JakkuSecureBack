package secure.project.secureProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import secure.project.secureProject.dto.response.ResponseDto;
import secure.project.secureProject.util.SecurityUtil;

@RestController
@RequestMapping("/topbar")
@RequiredArgsConstructor
public class TopbarController {
    private final SecurityUtil securityUtil;

    @GetMapping("/username")
    public ResponseDto<String> topbarNickname() {
        return new ResponseDto<>(securityUtil.getCurrentUsername());
    }

}
