package perproject.moviecommunity.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import perproject.moviecommunity.domain.Member;

public class MemoryMemberRepositoryTest {

    MemberRepository repository;

    @Test
    public void save() {
        // given
        Member member = new Member();
        member.setUsername("test1");
        member.setPassword("1234");

        // when
        repository.save(member);

        // then
        String result = member.toString();
        System.out.println(result);

        Member result2 = repository.findById(member.getId()).get();

        Assertions.assertThat(result2).isEqualTo(member);
    }

    @Test
    public void remove() {
        // given
        Member member = new Member();
        member.setUsername("test2");
        member.setPassword("1234");
        repository.save(member);

        // when
//        Member result = repository.remove(member);

        // then
//        Assertions.assertThat(member).isEqualTo(result);
    }
}
