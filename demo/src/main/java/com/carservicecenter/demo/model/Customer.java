/*
* Customer Entity
*
* One customer to many vehicles
*
 */
package com.carservicecenter.demo.model;

import javax.persistence.*;

@Entity(name = "customer")
@Table(
        name = "customer",
        uniqueConstraints = {
                @UniqueConstraint(name="customer_email_unique", columnNames = "email")
        })
public class Customer {

  @Id
  @SequenceGenerator(
          name = "customer_sequence",
          sequenceName = "customer_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "customer_sequence"
  )
  @Column(
          name = "id",
          updatable = false
  )
  private int id;

  private String first_name;

  private String last_name;

  private String email;

  private String address;

  private String city;

  private String state_of_residence;

  private String telephone;

  private java.sql.Date date_created;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getFirstName() {
    return first_name;
  }

  public void setFirstName(String firstName) {
    this.first_name = firstName;
  }


  public String getLastName() {
    return last_name;
  }

  public void setLastName(String lastName) {
    this.last_name = lastName;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }


  public String getStateOfResidence() {
    return state_of_residence;
  }

  public void setStateOfResidence(String stateOfResidence) {
    this.state_of_residence = stateOfResidence;
  }


  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }


  public java.sql.Date getDateCreated() {
    return date_created;
  }

  public void setDateCreated(java.sql.Date dateCreated) {
    this.date_created = dateCreated;
  }

}
