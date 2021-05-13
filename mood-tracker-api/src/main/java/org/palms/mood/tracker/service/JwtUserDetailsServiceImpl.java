package org.palms.mood.tracker.service;

import org.palms.mood.tracker.api.model.LoginData;
import org.palms.mood.tracker.domain.UserDetailsCustom;
import org.palms.mood.tracker.domain.UserEntity;
import org.palms.mood.tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static java.util.Objects.nonNull;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    /**
     * Load User details info.
     *
     * @param username user name
     * @return {@link UserDetailsCustom}
     * @throws UsernameNotFoundException error
     */
    public UserDetailsCustom loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity entity = userRepository.findByUserName(username);
        if (nonNull(entity)) {
            final UserDetailsCustom userDetails = new UserDetailsCustom(entity.getUserName(),
                    entity.getPass(), new ArrayList<>(), entity.getId());
            return userDetails;
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }

    @Override
    public UserEntity saveUser(LoginData data) {
        final UserEntity user = new UserEntity();
        user.setUserName(data.getLogin());
        user.setPass(bcryptEncoder.encode(data.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserEntity findUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
