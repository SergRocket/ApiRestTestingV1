package TestsApi;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateUserResponseTest {
    private String name;
    private String position;
    private int id;
    //creating needed for us time format
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss.SSSZ")
    @JsonDeserialize(using= DateDeserializer.class)
    private LocalDateTime createdAt;
}
