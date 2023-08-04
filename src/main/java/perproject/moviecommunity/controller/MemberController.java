package perproject.moviecommunity.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class MemberController {

    @GetMapping("join")
    public String join() {
        return "/member/join";
    }
}
