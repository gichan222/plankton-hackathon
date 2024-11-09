package com.example.planktonhackathon.domain.member.repository;

import com.example.planktonhackathon.domain.member.domain.MemberBadge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberBadgeRepository extends JpaRepository<MemberBadge, Long> {

    boolean existsByEmailAndDistrict(String email, String district);
}
