package com.nixsolutions.repository;

import com.nixsolutions.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.Path;
import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.login = :login")
    User findByLogin(@Param("login") String login);

    @Query("select u from User u where u.email = :email")
    User findByEmail(@Param("email") String email);

    @Query("select u from User u where u.userId = :userId")
    User findByUserId(@Param("userId") Long userId);

    Optional<User> findOneByLogin(String login);
}
