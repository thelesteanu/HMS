package com.hele.repository;

import com.hele.entity.User;
import com.hele.utils.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Modifying
    @Query("update user r set r.username =?2, r.role = ?3 where r.id =?1")
    User updateUsernameAndRole(final Long id, final String username, final Role role);
}
