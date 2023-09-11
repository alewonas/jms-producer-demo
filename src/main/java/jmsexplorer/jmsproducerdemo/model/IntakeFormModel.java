package jmsexplorer.jmsproducerdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.constraints.NotEmpty;

/**
 * Represents an intake form model for capturing user information.
 */
@JsonPropertyOrder({
        "firstName",
        "phone",
        "email",
        "address",
        "Age",
        "occupation"
})
public class IntakeFormModel {

    @NotEmpty(message = "First name is required")
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("phone")
    private String phone;
    @NotEmpty(message = "Email is required")
    @JsonProperty("email")
    private String email;
    @JsonProperty("address")
    private String address;
    @JsonProperty("Age")
    private int age;
    @JsonProperty("occupation")
    private String occupation;

    public IntakeFormModel(){
    }

    public IntakeFormModel(String firstName, String phone, String email, String address, int age, String occupation) {
        super();
        this.firstName = firstName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.age = age;
        this.occupation = occupation;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("Age")
    public int getAge() {
        return age;
    }

    @JsonProperty("Age")
    public void setAge(int age) {
        this.age = age;
    }

    @JsonProperty("occupation")
    public String getOccupation() {
        return occupation;
    }

    @JsonProperty("occupation")
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * Converts the IntakeFormModel instance to a JSON string.
     *
     * @return The JSON representation of the IntakeFormModel.
     */
    @Override
    public String toString() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            throw new RuntimeException("Error serializing IntakeFormDao to JSON: " + e.getMessage(), e);
        }
    }
}
