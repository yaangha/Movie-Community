package perproject.moviecommunity.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Member {

    private Long id;
    private String name;
    private String pw;

    @Builder.Default
    private Set<MemberRole> roles = new HashSet<>();

    public Member addRole(MemberRole role) {
        roles.add(role);
        return this;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pw='" + pw + '\'' +
                ", roles=" + roles +
                '}';
    }
}
