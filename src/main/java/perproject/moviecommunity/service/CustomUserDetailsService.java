package perproject.moviecommunity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import perproject.moviecommunity.domain.Member;
import perproject.moviecommunity.dto.MemberSecurityDto;
import perproject.moviecommunity.repository.MemoryMemberRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemoryMemberRepository memoryMemberRepository;

    @Autowired
    public CustomUserDetailsService(MemoryMemberRepository memoryMemberRepository) {
        this.memoryMemberRepository = memoryMemberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> member = memoryMemberRepository.findByUsername(username);
        System.out.println("member.toString() = " + member.toString());
        if (member.isPresent()) {
            return MemberSecurityDto.fromEntity(member.get());
        } else {
            throw new UsernameNotFoundException(username + ": not fount!");
        }
    }
}
