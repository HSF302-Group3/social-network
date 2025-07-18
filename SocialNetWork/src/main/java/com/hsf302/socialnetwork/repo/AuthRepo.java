package com.hsf302.socialnetwork.repo;


import com.hsf302.socialnetwork.enity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepo extends JpaRepository<Users, Long> {
    public Users findByEmail(String email);
}
