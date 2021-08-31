package TestsApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
//lombok will give a chance to eliminate getters/setters
@Data
public class DeserializaFullTest {
 private int id;
 private String avatar;
 @JsonProperty("first_name")
    private String firstName;
 @JsonProperty("last_name")
    private String lastName;
 private String email;
}
