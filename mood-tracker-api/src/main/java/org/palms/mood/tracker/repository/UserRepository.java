package org.palms.mood.tracker.repository;

import org.palms.mood.tracker.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Find user.
     *
     * @param userName user name
     * @return {@link UserEntity}
     */
    UserEntity findByUserName(String userName);
}
