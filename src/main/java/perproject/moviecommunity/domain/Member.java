package perproject.moviecommunity.domain;

import lombok.*;

//import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
//@Entity
//@SequenceGenerator(name = "MEMBER_SEQ_GEN", sequenceName = "MEMBER_SEQ", allocationSize = 1)
public class Member {

    //@Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GEN")
    private Long id;

    //@Column(nullable = false)
    private String username;

    //@Column(nullable = false)
    private String password;

    //@ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roles = new HashSet<>();

    public Member addRole(MemberRole role) {
        roles.add(role);
        return this;
    }

}
