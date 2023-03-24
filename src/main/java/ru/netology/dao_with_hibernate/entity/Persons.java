package ru.netology.dao_with_hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Persons {

    @EmbeddedId
    private Contact contact;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "city_of_living")
    private String city;

    /*public Persons() {
    }

    public Persons(String name, String surname, int age, String phoneNumber, String cityOfLiving) {
        this.contact = new Contact(name, surname, age);
        this.phoneNumber = phoneNumber;
        this.city = cityOfLiving;
    }*/

    public String getName() {
        return contact.getName();
    }

    public void setName(String name) {
        this.contact.setName(name);
    }

    public String getSurname() {
        return this.contact.getSurname();
    }

    public void setSurname(String surname) {
        this.contact.setSurname(surname);
    }

    public int getAge() {
        return this.contact.getAge();
    }

    public void setAge(int age) {
        this.contact.setAge(age);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String cityOfLiving) {
        this.city = cityOfLiving;
    }

    @Override
    public String toString() {
        return "Persons{" +
                "name='" + contact + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", cityOfLiving='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Persons person = (Persons) o;

        if (contact.getAge() != person.getAge()) return false;
        if (!contact.getName().equals(person.getName())) return false;
        if (!contact.getSurname().equals(person.getSurname())) return false;
        if (!Objects.equals(phoneNumber, person.phoneNumber)) return false;
        return Objects.equals(city, person.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contact.getName(), contact.getSurname(), contact.getAge(), getPhoneNumber(), getCity());
    }
}
