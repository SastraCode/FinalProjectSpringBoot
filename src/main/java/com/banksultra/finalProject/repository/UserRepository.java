package com.banksultra.finalProject.repository;

import com.banksultra.finalProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    String sql1 = "SELECT * FROM FINAL.USER WHERE ID=:id";

    @Query(value = sql1, nativeQuery = true)
    public User getUserById(Long id);

    String sql2 = "SELECT * FROM FINAL.USER WHERE EMAIL=:email";
    @Query(value = sql2, nativeQuery = true)
    User getUserByEmail(String email);

    String sqlRole = "INSERT INTO FINAL.USER_ROLE (USER_ID, ROLE_ID)VALUES(:user_id, :role_id)";
    @Modifying
    @Transactional
    @Query(value = sqlRole, nativeQuery = true)
    Integer addUserRole(Long user_id, Long role_id);

    String sqlURole = "UPDATE FINAL.USER_ROLE SET ROLE_ID = :role_id WHERE USER_ID = :user_id";
    @Modifying
    @Transactional
    @Query(value = sqlURole, nativeQuery = true)
    Integer updateUserRole(Long user_id, Long role_id);
}

