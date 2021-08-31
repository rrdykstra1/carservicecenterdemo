package com.carservicecenter.demo.test;

import com.carservicecenter.demo.controller.AppointmentController;
import com.carservicecenter.demo.model.Appointment;
import com.carservicecenter.demo.service.AppointmentService;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit5.JMockitExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(JMockitExtension.class)
public class AppointmentControllerTest {

    @Injectable
    AppointmentService appointmentService;

    @Tested
    AppointmentController appointmentController = new AppointmentController();

    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(appointmentController).build();
        appointmentController.setAppointmentService(appointmentService);
    }

    @Test
    void addAppointment() throws Exception{
        String apptJson = getAppointmentJson();
        //String json = TestUtils.getObjectAsJson(appointment);

        mockMvc.perform(post ("/cscdemo/appointment/new")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(apptJson))
                .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true));;

    }

    @Test
    void deleteAppointment() throws Exception{
        String apptJson = getAppointmentJson();

        mockMvc.perform(delete ("/cscdemo/appointment/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(apptJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void updateAppointmentByCompleteStatus() throws Exception{
        String apptJson = getAppointmentJson();

        mockMvc.perform(put ("/cscdemo/appointment/updateCompleteStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(apptJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void getAppointmentByVehicleId() throws Exception{

        //set up the Expectations
        Appointment appointment = getAppointmentObject();
        List<Appointment> apptList = new ArrayList<Appointment>();
        apptList.add(appointment);
        new Expectations(){{
            appointmentService.getAppointmentsByVehicleId(appointment.getVehicleId());
            result = apptList;
        }};

        ResultActions resultActions = mockMvc.perform(get("/cscdemo/appointment/getByVehicleId/")
                .param("id", String.valueOf(appointment.getVehicleId()))
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF_8"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void getAppointmentsByDateRange() throws Exception {

        //set up the Expectations
        //the appointment(s) is retrieved by date range
        Appointment appointment = getAppointmentObject();
        List<Appointment> apptList = new ArrayList<Appointment>();
        apptList.add(appointment);
        new Expectations(){{
            appointmentService.getAppointmentsByDateRange(appointment.getApptDate(), appointment.getApptDate());
            result = apptList;
        }};

        ResultActions resultActions = mockMvc.perform(get("/cscdemo/appointment/getByDateRange/")
                .param("start", String.valueOf(appointment.getApptDate().getTime()))
                .param("end", String.valueOf(appointment.getApptDate().getTime()))
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF_8"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.success").value(true));
    }

    //creates custom json string because objectmapper returns wrong order
    //even though JsonProperty order is defined on object Appointment
    private String getAppointmentJson(){
        Appointment appointment = getAppointmentObject();
        return "{\"vehicle_id\":" + appointment.getVehicleId() + ", \"service_desc\":"
                + "\"" + appointment.getServiceDesc() + "\"" +  ", \"appt_date\":" + appointment.getApptTime().getTime() + ", \"appt_time\":"
                + "\"" + appointment.getApptTime() + "\"" + ", \"price\":" + appointment.getPrice() + ", \"complete_status\":"
                + appointment.getCompleteStatus() + "}";
    }

    private Appointment getAppointmentObject(){
        Date today = new Date(Calendar.getInstance().getTime().getTime());
        Time apptTime = Time.valueOf("09:00:00");
        return new Appointment(1, "oil change", today, apptTime, 99.00, false);
    }
}