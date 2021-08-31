package com.carservicecenter.demo.service;

import com.carservicecenter.demo.model.Appointment;
import com.carservicecenter.demo.repository.AppointmentRepository;
import com.carservicecenter.demo.dao.AppointmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class AppointmentService implements AppointmentDao
{

    private AppointmentRepository appointmentRepository;

    @Autowired
    public void setAppointmentRepository(AppointmentRepository repo){
        this.appointmentRepository = repo;
    }

    public void addAppointment(Appointment appointment){
        appointmentRepository.save(appointment);
    }


    public void deleteAppointment(Appointment appointment){
        appointmentRepository.delete(appointment);
    }


    public void updateAppointmentCompleteStatus(Appointment appointment){
        appointmentRepository.updateAppointmentCompleteStatus(appointment.getId(), appointment.getCompleteStatus());
    }


    public List<Appointment> getAppointmentsByVehicleId(int vehicle_id){
        return appointmentRepository.findByVehicleId(vehicle_id);
    }


    public List<Appointment> getAppointmentsByDateRange(Date start, Date end){
        return appointmentRepository.findByDatRange(start, end);
    }
}
