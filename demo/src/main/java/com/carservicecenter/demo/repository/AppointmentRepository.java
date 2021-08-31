package com.carservicecenter.demo.repository;

import com.carservicecenter.demo.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Modifying
    @Query("update appointment a set a.complete_status = ?2 where a.id = ?1")
    void updateAppointmentCompleteStatus(long id, boolean status);

    @Query("select a from appointment a where vehicle_id = ?1")
    List<Appointment> findByVehicleId(long vehicleId);

    @Query("select a from appointment a where appt_date between ?1 and ?2 group by a.price")
    List<Appointment> findByDatRange(Date startDate, Date endDate);
}
