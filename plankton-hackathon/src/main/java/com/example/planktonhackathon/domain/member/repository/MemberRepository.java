package com.example.planktonhackathon.domain.member.repository;

import com.example.planktonhackathon.domain.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByEmail(String email);
    Optional<Member> findByNickName(String nickName);
}
