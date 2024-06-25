package com.safetKyuchyukhalil.libraryManagementSystem.repository.users;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    UserDetails findByLogin(String login);
}
