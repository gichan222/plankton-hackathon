package com.example.planktonhackathon.domain.member.repository;

import com.example.planktonhackathon.domain.member.domain.MemberBadge;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberBadgeRepository extends JpaRepository<MemberBadge, Long> {

    boolean existsByEmailAndDistrict(String email, String district);
    List<MemberBadge> findAllByEmail(String email);
    int countByEmail(String email);
}
