package pl.sda.final_project.dto;


import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class RegistrationDto {


//    @NotNull(message = "Name can't be empty")
//    @Size(min = 1, message = "Min size is 1 symbol. from firstname")
    private String firstName;
    //@NotNull(message = "Surname can't be empty")
    //@Size(min = 1, message = "Min size is 1 symbol. from lastname")
    private String lastName;
    private String city;
    private String street;
    private String country;
    private String zipCode;
//    @NotNull(message = "Date can't be empty")
//    @Pattern(regexp = "^(19|20)[\\d]{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", message = "Invalid date format, use a \"YYYY-MM-DD\" format ")
    private String birthDate;
    private String pesel;

   // @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$", message = "Invalid e-mail format, please provide correct email")
    private String login;
   // @Size(min = 1, max = 10, message = "Invalid password length, min length is 1 max - 10 ")
    private String password;
    private String phoneNumber;
    private boolean preferEmails;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isPreferEmails() {
        return preferEmails;
    }

    public void setPreferEmails(boolean preferEmails) {
        this.preferEmails = preferEmails;
    }
}
