package perproject.moviecommunity.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import perproject.moviecommunity.domain.Member;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MemberSecurityDto extends User {

    private String username;
    private String password;

    public MemberSecurityDto(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.username = username;
        this.password = password;
    }

    public static MemberSecurityDto fromEntity(Member member) {
        List<GrantedAuthority> authorities = member.getRoles().stream()
                .map(x -> new SimpleGrantedAuthority(x.getRole()))
                .collect(Collectors.toList());
        MemberSecurityDto dto = new MemberSecurityDto(member.getUsername(), member.getPassword(), authorities);
        return dto;
    }

}
