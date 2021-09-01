package TestsApi;

public class UserGenerator {
    public static CreateUserTest getSimpleUser(){
        return CreateUserTest.builder()
                .name("Man")
                .position("Unemployed")
                .build();
    }
}
