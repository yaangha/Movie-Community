package perproject.moviecommunity.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class MemberRegisterDto {

    @NotBlank(message = "아이디는 필수 입력값입니다.")
    @Pattern(regexp = "(?=.*[a-z0-9]).{4,10}$", message = "아이디는 4~10자 영문과 숫자 조합만 가능합니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z]).{8,16}", message = "비밀번호는 8~16자 영문과 숫자를 사용하세요.")
    private String password;
}
