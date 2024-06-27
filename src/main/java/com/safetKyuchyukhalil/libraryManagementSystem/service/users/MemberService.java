package com.safetKyuchyukhalil.libraryManagementSystem.service.users;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.Member;
import com.safetKyuchyukhalil.libraryManagementSystem.exception.users.MemberNotFoundException;
import com.safetKyuchyukhalil.libraryManagementSystem.repository.users.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public void delete(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new MemberNotFoundException("Member not found with id: " + id);
        }
        memberRepository.deleteById(id);
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(()->new MemberNotFoundException("Member not found with id: " + memberId));
    }
}
