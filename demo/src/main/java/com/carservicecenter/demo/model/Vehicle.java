/*
* Vehicle Entity
*
* Many vehicles to one customer
 */
package com.carservicecenter.demo.model;

import javax.persistence.*;

@Entity(name = "vehicle")
public class Vehicle {

  @Id
  @SequenceGenerator(
          name = "vehicle_sequence",
          sequenceName = "vehicle_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "vehicle_sequence"
  )
  private int id;

  private long customer_id;

  private String make;

  private String model;

  private int year_of_vehicle;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public long getCustomerId() {
    return customer_id;
  }

  public void setCustomerId(long customerId) {
    this.customer_id = customerId;
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getYearOfVehicle() {
    return year_of_vehicle;
  }

  public void setYearOfVehicle(int yearOfVehicle) {
    this.year_of_vehicle = yearOfVehicle;
  }

}
