package com.safetKyuchyukhalil.libraryManagementSystem.repository.users;

import com.safetKyuchyukhalil.libraryManagementSystem.entity.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
