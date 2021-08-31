package com.carservicecenter.demo.dao;

import com.carservicecenter.demo.model.Appointment;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public interface AppointmentDao {

    void addAppointment(Appointment appointment);

    void deleteAppointment(Appointment appointment);

    void updateAppointmentCompleteStatus(Appointment appointment);

     List<Appointment> getAppointmentsByVehicleId(int vehicle_id);

     List<Appointment> getAppointmentsByDateRange(Date startDate, Date endDate);


}
