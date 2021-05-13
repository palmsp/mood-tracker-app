package org.palms.mood.tracker.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Data
public class UserDetailsCustom extends User {

    private Long userId;

    public UserDetailsCustom(String username, String password,
                             Collection<? extends GrantedAuthority> authorities, Long userId) {
        super(username, password, authorities);
        this.userId = userId;
    }
}
