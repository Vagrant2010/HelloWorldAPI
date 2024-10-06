package HelloWord.HelloWorldAPI.dto;

import HelloWord.HelloWorldAPI.enums.APIResponseStatus;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MessageEntity<T> {

    private static final String OK = "OK";
    private static final String KO = "KO";

    private T data;

    private String status;
    private Integer httpStatus;
    private String message;
    private String internalCode;


    public static <T> MessageEntity<T> response(T data, APIResponseStatus message, HttpStatus httpStatus) {
        String apiStatus = (httpStatus.is2xxSuccessful())?OK:KO;
        MessageEntity<T> build = MessageEntity.<T>builder()
                .data(data)
                .httpStatus(httpStatus.value())
                .status(apiStatus)
                .message(message.getStatus())
                .internalCode(message.name())
                .build();
        return build;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageEntity<?> that = (MessageEntity<?>) o;
        return Objects.equals(data, that.data) && Objects.equals(status, that.status) && Objects.equals(httpStatus, that.httpStatus) && Objects.equals(message, that.message) && Objects.equals(internalCode, that.internalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, httpStatus, message, internalCode);
    }
}

