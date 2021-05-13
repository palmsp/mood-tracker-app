package org.palms.mood.tracker.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.palms.mood.tracker.api.model.ApiResponse;
import org.palms.mood.tracker.api.model.JwtResponse;
import org.palms.mood.tracker.api.model.LoginData;
import org.palms.mood.tracker.api.util.JwtTokenUtil;
import org.palms.mood.tracker.domain.UserDetailsCustom;
import org.palms.mood.tracker.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for register and auth.
 *
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Api("Register and auth controller")
@Slf4j
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;
    
    /**
     * Auth for user.
     *
     * @param request {@link LoginData}
     * @return {@link JwtResponse}
     * @throws Exception {@link Exception}
     */
    @PostMapping
    @ApiOperation(value = "Auth user", response = JwtResponse.class)
    public JwtResponse auth(@RequestBody LoginData request) throws Exception {
        log.info("AuthController.auth with login: {}", request.getLogin());
        authenticate(request.getLogin(), request.getPassword());
        final UserDetailsCustom userDetails = (UserDetailsCustom) userDetailsService
                .loadUserByUsername(request.getLogin());
        final String token = jwtTokenUtil.generateToken(userDetails, userDetails.getUserId());
        return new JwtResponse(token);
    }

    /**
     * Register user.
     *
     * @param request {@link LoginData}
     * @return {@link JwtResponse}
     */
    @PostMapping("/register")
    @ApiOperation(value = "Register user", response = ApiResponse.class)
    public ApiResponse register(@RequestBody LoginData request) {
        log.info("AuthController.register user with login: {}", request.getLogin());
        userDetailsService.saveUser(request);
        return new ApiResponse();
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
