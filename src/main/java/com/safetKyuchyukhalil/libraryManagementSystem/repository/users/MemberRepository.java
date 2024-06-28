package com.safetKyuchyukhalil.libraryManagementSystem.repository.users;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByUsername(String username);
}
