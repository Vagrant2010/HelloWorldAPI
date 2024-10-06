package HelloWord.HelloWorldAPI.service;

import HelloWord.HelloWorldAPI.dto.MessageEntity;
import HelloWord.HelloWorldAPI.dto.response.MessageResponse;
import HelloWord.HelloWorldAPI.enums.APIResponseStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HelloServiceTest {
    @InjectMocks
    HelloService helloService;

    @BeforeTestClass
    void setUpBeforeClass() {
        System.out.println("Start HelloServiceTest...");
    }
    @AfterTestClass
    void tearDownAfterClass() {
        System.out.println("End HelloServiceTest...");
    }

    @Test
    void shouldSuccessfully_hello() {
        String message = "Hello World!";

        MessageEntity<?> actualResponse = helloService.getHello(message);
        MessageEntity<?> expectedResponse = MessageEntity.response(new MessageResponse(message), APIResponseStatus.MEX_03, HttpStatus.OK);
        System.out.println("actualResponse: "+actualResponse.toString());
        System.out.println("expectedResponse: "+expectedResponse.toString());
        assertEquals(expectedResponse, actualResponse);
        System.out.println("actualResponse.message: "+ actualResponse.getData().toString());
        System.out.println("expectedResponse.message: "+expectedResponse.getData().toString());
        assertEquals(expectedResponse.getData(), actualResponse.getData());
        assertDoesNotThrow(()->helloService.getHello(message));
    }

    @Test
    void shouldNotSuccessfully_hello() {
        String message = "Hello W";

        MessageEntity<?> actualResponse = helloService.getHello(message);
        MessageEntity<?> expectedResponse = MessageEntity.response(new MessageResponse(message), APIResponseStatus.MEX_03, HttpStatus.OK);
        System.out.println("actualResponse: "+actualResponse.toString());
        System.out.println("expectedResponse: "+expectedResponse.toString());
        assertNotEquals(expectedResponse,actualResponse);
        String messageRespone;
        if(actualResponse.getData() == null) {
            messageRespone = "null";
        }else{
            messageRespone = actualResponse.getData().toString();
        }
        System.out.println("actualResponse.message: "+messageRespone);
        System.out.println("expectedResponse.message: "+expectedResponse.getData().toString());
        assertNotEquals(expectedResponse.getData(), actualResponse.getData());
        assertDoesNotThrow(()->helloService.getHello(message));
    }

}
