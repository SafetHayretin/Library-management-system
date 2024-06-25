package com.safetKyuchyukhalil.libraryManagementSystem.controller.users;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.Member;
import com.safetKyuchyukhalil.libraryManagementSystem.service.users.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getAllUsers() {
        return memberService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getUserById(@PathVariable Long id) {
        Member member = memberService.getUserById(id);

        return ResponseEntity.ok(member);
    }

    @PostMapping
    public ResponseEntity<Member> createUser(@RequestBody Member member) {
        Member createdMember = memberService.saveUser(member);

        return ResponseEntity.status(201).body(createdMember);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        memberService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
