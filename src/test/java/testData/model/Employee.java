package testData.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.GlobalConstants;

import java.io.File;


public class Employee {
    public static Employee getEmployee() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(new File(GlobalConstants.DATA_TEST_PATH + "employee.json"), Employee.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @JsonProperty("firstname")
    private String employeeFirstName;

    @JsonProperty("lastname")
    private String employeeLastName;

    @JsonProperty("dob")
    private String employeeDob;

    @JsonProperty("email")
    private String employeeEmail;

    @JsonProperty("address")
    private String employeeAddress;

    @JsonProperty("username")
    String userName;

    @JsonProperty("password")
    String password;

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public String getEmployeeDob() {
        return employeeDob;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
