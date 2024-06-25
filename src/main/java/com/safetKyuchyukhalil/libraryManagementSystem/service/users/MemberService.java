package com.safetKyuchyukhalil.libraryManagementSystem.service.users;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.Member;
import com.safetKyuchyukhalil.libraryManagementSystem.exception.users.UserNotFoundException;
import com.safetKyuchyukhalil.libraryManagementSystem.repository.users.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllUsers() {
        return memberRepository.findAll();
    }

    public Member getUserById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public Member saveUser(Member member) {
        return memberRepository.save(member);
    }

    public void deleteUser(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        memberRepository.deleteById(id);
    }
}
