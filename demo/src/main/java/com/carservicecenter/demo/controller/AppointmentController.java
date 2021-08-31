/*
 * The controller for all appointment activity Rest API's.
 *
 * Create new appointments
 * Delete appointments
 * Update the status of an appointment
 * Retrieve a specific appointment
 * Retrieve all appointments between a date range, sorted by price
 *
 */
package com.carservicecenter.demo.controller;

import com.carservicecenter.demo.model.Appointment;
import com.carservicecenter.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


@Controller
@RequestMapping("/cscdemo/appointment")
public class AppointmentController {


    private AppointmentService appointmentService;

    @Autowired
    public void setAppointmentService(AppointmentService apptService){
        this.appointmentService = apptService;
    }

    @PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addAppointment(@RequestBody Appointment appointment){
        appointmentService.addAppointment(appointment);
    }

    @PostMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAppointment(@RequestBody Appointment appointment){
        appointmentService.deleteAppointment(appointment);
    }

    @PostMapping(path = "/updateCompleteStatus", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateAppointmentByCompleteStatus(@RequestBody Appointment appointment){
        appointmentService.updateAppointmentCompleteStatus(appointment);
    }

    @RequestMapping(path = "/getByVehicleId/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Appointment> getAppointmentByVehicleId(@PathVariable("id") int id){
        return appointmentService.getAppointmentsByVehicleId(id);
    }

    @RequestMapping(path = "/getByDateRange/{start}/{end}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Appointment> getAppointmentsByDateRange(@PathVariable("start") Date start, @PathVariable("end") Date end){
        return appointmentService.getAppointmentsByDateRange(start, end);
    }

    /*
     * Creates new appointments with random delay between 5 to 10 seconds
     * Appointment date is set to current date
     * Appointment time is set to a random time, up to 18:00
     * Price is $99.00
     * Vehicle_id is a persisted Vehicle entity
     */
    @Scheduled(fixedDelayString = "#{new Double((T(java.lang.Math).random() + 1) * 5000).intValue()}")
    public void scheduleAppointments(){
        Date today = new Date(Calendar.getInstance().getTime().getTime());
        int randomHour = new Random().nextInt(18);
        Time apptTime = Time.valueOf(Integer.toString(randomHour) + ":00:00");
        this.addAppointment(new Appointment(1, "maint", today, apptTime, 99.00, false));
    }

}
