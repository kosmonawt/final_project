package pl.sda.final_project.model.user;

import pl.sda.final_project.dto.RegistrationDto;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Address {

    private String city;
    private String street;
    @Enumerated(EnumType.STRING)
    private Countries country;
    private String zipCode;

    static Address apply(RegistrationDto registrationDto) {
        Address address = new Address();
        address.city = registrationDto.getCity();
        address.country = Countries.fromSymbol(registrationDto.getCountry());
        address.street = registrationDto.getStreet();
        address.zipCode = registrationDto.getZipCode();
        return address;
    }
}
