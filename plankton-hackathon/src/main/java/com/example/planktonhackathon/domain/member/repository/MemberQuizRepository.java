package com.example.planktonhackathon.domain.member.repository;

import com.example.planktonhackathon.domain.member.domain.MemberQuiz;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberQuizRepository extends JpaRepository<MemberQuiz, String> {

    boolean existsByEmailAndLocalDate(String email, LocalDate localDate);
    void deleteByEmailAndLocalDate(String email, LocalDate localDate);
    Optional<MemberQuiz> findByEmailAndLocalDate(String email, LocalDate localDate);
}
