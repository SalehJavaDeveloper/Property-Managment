package com.example.property.repository.user;

import com.example.property.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByUsername(String username);

    User findByFullName(String fullName);
    User findByEmail(String email);

    @Query("select u.password from User u where u.username = :username")
    String getPasswordByUsername(String username);

    List<User> findByResetPasswordToken(String token);

}
