package org.palms.mood.tracker.service;

import org.palms.mood.tracker.api.model.LoginData;
import org.palms.mood.tracker.domain.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
public interface JwtUserDetailsService extends UserDetailsService {

    /**
     * Save user.
     *
     * @param data {@link LoginData}
     * @return {@link UserEntity}
     */
    UserEntity saveUser(LoginData data);

    /**
     * Find user.
     *
     * @param id id
     * @return {@link UserEntity}
     */
    UserEntity findUser(Long id);
}
