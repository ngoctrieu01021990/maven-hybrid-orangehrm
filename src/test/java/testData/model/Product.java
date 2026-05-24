package testData.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.GlobalConstants;

import java.io.File;


public class Product {
    public static Product getProduct() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(new File(GlobalConstants.DATA_TEST_PATH + "product.json"), Product.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @JsonProperty("Register")
    private Register register;

    @JsonProperty("Login")
    private Login login;

    public static class Register {
        @JsonProperty("fullname")
        String fullName;
    }

    public String getFullName() {
        return register.fullName;
    }

    public static class Login {
        @JsonProperty("username")
        String username;

        @JsonProperty("password")
        String password;
    }

    public String getUsername() {
        return login.username;
    }

    public String getPassword() {
        return login.password;
    }
}
