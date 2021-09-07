package com.carservicecenter.demo.test;

import com.carservicecenter.demo.controller.AppointmentController;
import com.carservicecenter.demo.model.Appointment;
import com.carservicecenter.demo.repository.AppointmentRepository;
import com.carservicecenter.demo.service.AppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import mockit.Expectations;
import mockit.Tested;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AppointmentControllerTest {

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Tested
    AppointmentController appointmentController = new AppointmentController();

    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(appointmentController).build();
        appointmentController.setAppointmentService(appointmentService);

        //check to ensure everything is wired up
        assertNotNull(appointmentController);
        assertNotNull(appointmentService);
        assertNotNull(appointmentRepository);
    }


    @Test
    void addAppointment() throws Exception{
        String apptJson = createAppointmentJson();
        assertNotNull(apptJson);

        mockMvc.perform(post ("/cscdemo/appointment/new")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(apptJson))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAppointment() throws Exception{
        String apptJson = createAppointmentJson();
        assertNotNull(apptJson);

        mockMvc.perform(delete ("/cscdemo/appointment/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(apptJson))
                .andExpect(status().isOk());
    }

    @Test
    void updateAppointmentByCompleteStatus() throws Exception{
        String apptJson = createAppointmentJson();

        mockMvc.perform(put ("/cscdemo/appointment/updateCompleteStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(apptJson))
                .andExpect(status().isOk());
    }

    @Test
    void getAppointmentByVehicleId() throws Exception{

        //set up the Expectations
        Appointment appointment = getAppointmentObject();
        List<Appointment> apptList = new ArrayList<Appointment>();
        apptList.add(appointment);
//        new Expectations(){{
//            appointmentService.getAppointmentsByVehicleId(appointment.getVehicleId());
//            result = apptList;
//        }};

        ResultActions resultActions = mockMvc.perform(get("/cscdemo/appointment/getByVehicleId/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF_8"))
                .andExpect(status().isOk());
    }

    @Test
    void getAppointmentsByDateRange() throws Exception {

        //set up the Expectations
        //the appointment(s) is retrieved by date range
        Appointment appointment = getAppointmentObject();
        List<Appointment> apptList = new ArrayList<Appointment>();
        apptList.add(appointment);
//        new Expectations(){{
//            appointmentService.getAppointmentsByDateRange(appointment.getApptDate(), appointment.getApptDate());
//            result = apptList;
//        }};

        ResultActions resultActions = mockMvc.perform(get("/cscdemo/appointment/getByDateRange/{start}/{end}",
                appointment.getApptDate(),
                appointment.getApptDate())
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF_8"))
                .andExpect(status().isOk());
    }


    //creates Appointment object Json
    private String createAppointmentJson(){
        Appointment appt = getAppointmentObject();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            String apptJson = objectMapper.writeValueAsString(appt);
            return apptJson;
        }catch(Exception e) {
            //could not write object, will return null
        }
        return null;

    }

    private Appointment getAppointmentObject(){
        Date today = new Date(Calendar.getInstance().getTime().getTime());
        Time apptTime = Time.valueOf("09:00:00");
        return new Appointment(1, "oil change", today, apptTime, 99.00, false);
    }
}