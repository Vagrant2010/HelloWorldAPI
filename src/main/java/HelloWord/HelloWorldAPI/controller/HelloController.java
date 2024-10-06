package HelloWord.HelloWorldAPI.controller;

import HelloWord.HelloWorldAPI.dto.MessageEntity;
import HelloWord.HelloWorldAPI.dto.response.MessageResponse;
import HelloWord.HelloWorldAPI.service.HelloService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
@RequiredArgsConstructor
public class HelloController {

    private final HelloService helloService;

    @Operation(summary = "Get the message \"Hello World!\"", description = "Return a ResponseEntity within \"Hello World!\" message; \n if this message is not equals return a custom error.")
    @GetMapping("{mex}")
    public ResponseEntity<MessageEntity<MessageResponse>> hello(@PathVariable(name = "mex") String mex) {
        MessageEntity<MessageResponse> messageEntity = helloService.getHello(mex);
        return ResponseEntity
                .status(messageEntity.getHttpStatus())
                .body(messageEntity);
    }

}
