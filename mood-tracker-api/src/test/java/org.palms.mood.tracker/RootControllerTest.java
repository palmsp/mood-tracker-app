package org.palms.mood.tracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.palms.mood.tracker.config.ExceptionHandler;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
public abstract class RootControllerTest {

    private MockMvc mvc;

    public void mockMvc(Object restController) {
        mvc = MockMvcBuilders.standaloneSetup(restController)
                .setControllerAdvice(new ExceptionHandler())
                .build();
    }

    private ObjectMapper objectMapper = new ObjectMapper();

    protected ResultActions perform(RequestBuilder requestBuilder) throws Exception {
        return mvc.perform(requestBuilder);
    }

    protected String makeContent(Object object) throws Exception {
        return objectMapper.writeValueAsString(object);
    }

    protected <T> void checkSingleResponse(MockHttpServletResponse response, Class<T> clazz, Consumer<T> checker) throws Exception {
        checker.accept(parseSingle(response, clazz));
    }

    protected <T> void checkListResponse(MockHttpServletResponse response, Class<T> clazz, Consumer<List<T>> checker) throws Exception {
        checker.accept(parseList(response, clazz));
    }

    protected <T> T parseSingle(MockHttpServletResponse response, Class<T> clazz) throws Exception {
        return objectMapper.readValue(response.getContentAsString(), clazz);
    }

    protected <T> List<T> parseList(MockHttpServletResponse response, Class<T> clazz) throws Exception {
        CollectionType type = objectMapper.getTypeFactory().constructCollectionType(Collection.class, clazz);
        return new ArrayList<>(objectMapper.readValue(response.getContentAsString(), type));
    }
}
