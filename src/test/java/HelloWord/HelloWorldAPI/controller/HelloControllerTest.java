package HelloWord.HelloWorldAPI.controller;

import HelloWord.HelloWorldAPI.dto.MessageEntity;
import HelloWord.HelloWorldAPI.dto.response.MessageResponse;
import HelloWord.HelloWorldAPI.enums.APIResponseStatus;
import HelloWord.HelloWorldAPI.service.HelloService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HelloService helloService;

    @Autowired
    private ObjectMapper objectMapper;

    private MessageEntity<MessageResponse> validResponse;
    private MessageEntity<MessageResponse> invalidResponse;

    @BeforeEach
    void setUp() {
        MessageResponse validMessage = new MessageResponse("Hello World!");
        validResponse = MessageEntity.response(validMessage, APIResponseStatus.MEX_03, HttpStatus.OK);

        invalidResponse = MessageEntity.response(null, APIResponseStatus.MEX_01, HttpStatus.BAD_REQUEST);
    }

    @Test
    void hello_ValidMessage_ShouldReturnOk() throws Exception {
        // Arrange
        when(helloService.getHello("Hello World!")).thenReturn(validResponse);

        // Act & Assert
        ResultActions response = mockMvc.perform(get("/hello/Hello World!")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(validResponse)))
                .andDo(print());

        response.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"));
        response.andExpect(MockMvcResultMatchers.jsonPath("$.data.message").value("Hello World!"));
    }

    @Test
    void hello_InvalidMessage_ShouldReturnBadRequest() throws Exception {
        // Arrange
        when(helloService.getHello(anyString())).thenReturn(invalidResponse);

        // Act & Assert
        ResultActions response = mockMvc.perform(get("/hello/Invalid Message")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(invalidResponse)))
                .andDo(print());

        response.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("KO"));
        response.andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty()); //This to verify that path is null
    }
}
