package org.palms.mood.tracker;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.palms.mood.tracker.api.AuthController;
import org.palms.mood.tracker.api.model.ApiResponse;
import org.palms.mood.tracker.api.model.JwtResponse;
import org.palms.mood.tracker.api.model.LoginData;
import org.palms.mood.tracker.api.util.JwtTokenUtil;
import org.palms.mood.tracker.domain.UserDetailsCustom;
import org.palms.mood.tracker.domain.UserEntity;
import org.palms.mood.tracker.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.apache.commons.lang3.math.NumberUtils.LONG_ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthControllerTest extends RootControllerTest {

    @Autowired
    private AuthController authController;

    @MockBean
    private JwtUserDetailsService jwtUserDetailsService;

    @MockBean
    JwtTokenUtil jwtTokenUtil;

    @Before
    public void setUp() {
        mockMvc(authController);
    }

    @Test
    @WithMockUser
    public void authTest() throws Exception {
        String userName = "user";
        String password = "password";
        Long userId = LONG_ZERO;
        final UserDetailsCustom userDetails = new UserDetailsCustom(userName, password, new ArrayList<>(),
                userId);

        LoginData request = new LoginData();
        request.setLogin(userName);
        request.setPassword(password);

        JwtResponse jwtResponse = new JwtResponse();

        when(jwtUserDetailsService.loadUserByUsername(eq(userName))).thenReturn(userDetails);
        when(jwtTokenUtil.generateToken(userDetails, userId)).thenReturn("token");

        MockHttpServletResponse response = perform(post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(makeContent(request)))
                .andExpect(status().is(500))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        checkSingleResponse(response,
                JwtResponse.class,
                jwt -> assertThat(jwt)
                        .isNotNull()
                        .isEqualTo(jwtResponse));
    }

    @Test
    public void registerTest() throws Exception {

        final LoginData loginData = new LoginData();
        loginData.setLogin("user");
        loginData.setPassword("password");

        final UserEntity user = new UserEntity();
        final ApiResponse apiResponse = new ApiResponse();

        when(jwtUserDetailsService.saveUser(loginData)).thenReturn(user);

        MockHttpServletResponse response = perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(makeContent(loginData)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        checkSingleResponse(response, ApiResponse.class,
                res -> assertThat(res)
                        .isNotNull()
                        .isEqualTo(apiResponse));
    }
}
