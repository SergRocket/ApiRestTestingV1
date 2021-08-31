package TestsApi;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeserializaTest {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    @JsonProperty("email")
    private String email;

}
