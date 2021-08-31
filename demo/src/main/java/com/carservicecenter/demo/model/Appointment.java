/*
* Appointment Entity
* requires a Vehicle id (Vehicle entity)
*
 */
package com.carservicecenter.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity(name = "appointment")
@JsonPropertyOrder({"id", "vehicle_id", "service_desc", "appt_date", "appt_time", "price", "complete_status"})
public class Appointment {

  @Id
  @SequenceGenerator(
          name = "appointment_sequence",
          sequenceName = "appointment_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "appointment_sequence"
  )
  private int id;

  private int vehicle_id;
  private java.sql.Date appt_date;
  private java.sql.Time appt_time;
  private String service_desc;
  private double price;
  private boolean complete_status;

      public Appointment(@JsonProperty("vehicle_id") int vehicleId,
                       @JsonProperty("service_desc") String serviceDesc,
                       @JsonProperty("appt_date") Date apptDate,
                       @JsonProperty("appt_time") Time apptTime,
                       @JsonProperty("price") double price,
                       @JsonProperty("complete_status") boolean completeStatus){

        this.vehicle_id = vehicleId;
        this.service_desc = serviceDesc;
        this.appt_date = apptDate;
        this.appt_time = apptTime;
        this.price = price;
        this.complete_status = completeStatus;
    }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public int getVehicleId() {
    return vehicle_id;
  }

  public void setVehicleId(int vehicleId) {
    this.vehicle_id = vehicleId;
  }


  public java.sql.Date getApptDate() {
    return appt_date;
  }

  public void setApptDate(java.sql.Date apptDate) {
    this.appt_date = apptDate;
  }


  public java.sql.Time getApptTime() {
    return appt_time;
  }

  public void setApptTime(java.sql.Time apptTime) {
    this.appt_time = apptTime;
  }


  public String getServiceDesc() {
    return service_desc;
  }

  public void setServiceDesc(String serviceDesc) {
    this.service_desc = serviceDesc;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public boolean getCompleteStatus() {
    return complete_status;
  }

  public void setCompleteStatus(boolean completeStatus) {
    this.complete_status = completeStatus;
  }

}
