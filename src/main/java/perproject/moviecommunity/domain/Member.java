package perproject.moviecommunity.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
public class Member {

    private Long id;
    private String username;
    private String password;

    @Builder.Default
    private Set<MemberRole> roles = new HashSet<>();

    public Member addRole(MemberRole role) {
        roles.add(role);
        return this;
    }

}
