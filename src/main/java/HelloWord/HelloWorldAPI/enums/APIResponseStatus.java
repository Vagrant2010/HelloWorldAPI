package HelloWord.HelloWorldAPI.enums;

import lombok.Getter;

@Getter
public enum APIResponseStatus {
    MEX_01("null message"),MEX_02("invalid message"),MEX_03("correct message");

    private final String status;

    APIResponseStatus(String status) {
        this.status = status;
    }

}

