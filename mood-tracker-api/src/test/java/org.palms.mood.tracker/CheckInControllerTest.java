package org.palms.mood.tracker;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.palms.mood.tracker.api.CheckInController;
import org.palms.mood.tracker.api.model.Activity;
import org.palms.mood.tracker.api.model.ApiResponse;
import org.palms.mood.tracker.api.model.Feeling;
import org.palms.mood.tracker.api.model.checkin.CheckInList;
import org.palms.mood.tracker.api.model.checkin.CheckInOptions;
import org.palms.mood.tracker.api.model.checkin.CheckInRequest;
import org.palms.mood.tracker.api.util.JwtTokenUtil;
import org.palms.mood.tracker.domain.ActivityEntity;
import org.palms.mood.tracker.domain.CheckInEntity;
import org.palms.mood.tracker.domain.FeelingEntity;
import org.palms.mood.tracker.domain.UserDetailsCustom;
import org.palms.mood.tracker.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ZERO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CheckInControllerTest extends RootControllerTest {

    private final UserDetailsCustom userDetailsCustom = new UserDetailsCustom("user", "password", new ArrayList<>(), LONG_ZERO);
    private final String token = "Bearer token";
    private final Long userId = LONG_ZERO;

    @Autowired
    private CheckInController checkInController;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private CheckInService checkInService;

    @Before
    public void setUp() {
        mockMvc(checkInController);
    }

    @Test
    @WithMockUser
    public void getCheckInTest() throws Exception {

        String dateFrom = "20.04.2021";
        String dateTo = "30.05.2021";

        final List<CheckInEntity> checkInEntities = new ArrayList<>();

        validateToken();

        when(checkInService.findCheckIns(userId, new Date(), new Date())).thenReturn(checkInEntities);

        MockHttpServletResponse response = perform(get("/check-in")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, token)
                .param("dateFrom", dateFrom)
                .param("dateTo", dateTo))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        checkSingleResponse(response, CheckInList.class,
                list -> assertThat(list)
                        .isNotNull());
    }

    @Test
    public void checkInTest() throws Exception {
        final CheckInRequest checkInRequest = new CheckInRequest();
        checkInRequest.setMoodScore(INTEGER_ONE);
        checkInRequest.setDate(new Date());
        final CheckInEntity checkInEntity = new CheckInEntity();
        final ApiResponse apiResponse = new ApiResponse();

        validateToken();
        doNothing().when(checkInService).saveCheckIn(checkInEntity);

        MockHttpServletResponse response = perform(post("/check-in")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, token)
                .content(makeContent(checkInRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        checkSingleResponse(response, ApiResponse.class,
                res -> assertThat(res)
                        .isNotNull()
                        .isEqualTo(apiResponse));
    }

    @Test
    public void selectTest() throws Exception {
        final CheckInOptions checkInOptions = new CheckInOptions();
        checkInOptions.setFeelings(new ArrayList<>());
        checkInOptions.setActivities(new ArrayList<>());

        validateToken();

        MockHttpServletResponse response = perform(get("/check-in/options")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        checkSingleResponse(response, CheckInOptions.class,
                res -> assertThat(res)
                        .isNotNull().isEqualTo(checkInOptions));
    }

    @Test
    public void saveActivityTest() throws Exception {
        final Activity activity = new Activity();
        final ApiResponse apiResponse = new ApiResponse();

        validateToken();
        doNothing().when(checkInService).saveActivity(new ActivityEntity());

        MockHttpServletResponse response = perform(post("/check-in/activity")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, token)
                .content(makeContent(activity)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        checkSingleResponse(response, ApiResponse.class,
                res -> assertThat(res)
                        .isNotNull().isEqualTo(apiResponse));
    }

    @Test
    public void saveFeelingTest() throws Exception {
        final Feeling feeling = new Feeling();
        final ApiResponse apiResponse = new ApiResponse();

        validateToken();
        doNothing().when(checkInService).saveFeeling(new FeelingEntity());

        MockHttpServletResponse response = perform(post("/check-in/feeling")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, token)
                .content(makeContent(feeling)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn().getResponse();

        checkSingleResponse(response, ApiResponse.class,
                res -> assertThat(res)
                        .isNotNull().isEqualTo(apiResponse));
    }

    private void validateToken() {
        when(jwtTokenUtil.getUsernameFromToken(token)).thenReturn(token);
        when(jwtTokenUtil.validateToken(token, userDetailsCustom)).thenReturn(Boolean.TRUE);
    }
}
