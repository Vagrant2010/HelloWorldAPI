package HelloWord.HelloWorldAPI.service;

import HelloWord.HelloWorldAPI.dto.MessageEntity;
import HelloWord.HelloWorldAPI.dto.response.MessageResponse;
import HelloWord.HelloWorldAPI.enums.APIResponseStatus;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelloService {


    public MessageEntity<MessageResponse> getHello(String mess) {
        MessageResponse mex = new MessageResponse(mess);
        if(mex.getMessage().equals("Hello World!")){
            return MessageEntity.response(mex,APIResponseStatus.MEX_03,HttpStatus.OK);
        }
        return MessageEntity.response(null, APIResponseStatus.MEX_01, HttpStatus.BAD_REQUEST);
    }
}

