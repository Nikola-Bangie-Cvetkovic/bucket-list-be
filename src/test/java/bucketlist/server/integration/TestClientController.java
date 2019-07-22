package bucketlist.server.integration;

import bucketlist.server.entities.dto.request.ClientRegistrationRequest;
import bucketlist.server.respositories.ClientDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestClientController {

    private static final ObjectMapper objMapper = new ObjectMapper();

    private static final String URL = "http://localhost:8080/clients";

    @Autowired
    private MockMvc mockMvc;

//    @MockBean
//    private ClientDao clientDao;

//    @Before
//    public void dbSetup(){
//        for (int i = 1; i <= 3; i++) {
//            clientDao.save(new Client(i, "Test" + i, "test" + i + "@email.com",
//                    bCryptPassword.encode("xxx"),
//                    new Timestamp(System.currentTimeMillis()),
//                    new Timestamp(System.currentTimeMillis()),
//                    true, new ArrayList<>()));
//        }
//    }

    @Test
    public void registrationSuccess() throws Exception {

        ClientRegistrationRequest regClient = new ClientRegistrationRequest(
                "Test4", "test4@email.com", "xxx");

        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objMapper.writeValueAsString(regClient)))
                .andExpect(status().isCreated());
    }

    @Test
    public void registrationError() throws Exception {

        List<ClientRegistrationRequest> clientList = new ArrayList<>();

        clientList.add(new ClientRegistrationRequest(
                "", "test2@email.com", "xxx"));
        clientList.add(new ClientRegistrationRequest(
                "Te", "test2@email.com", "xxx"));
        clientList.add(new ClientRegistrationRequest(
                randomAlphanumeric(51),
                "test2@email.com", "xxx"));
        clientList.add(new ClientRegistrationRequest(
                "Test2", "", "xxx"));
        clientList.add(new ClientRegistrationRequest(
                "Test2", randomAlphanumeric(101)+ "@email.com",
                "xxx"));
        clientList.add(new ClientRegistrationRequest(
                "Test2", "test2email.com",
                "xxx"));
        clientList.add(new ClientRegistrationRequest(
                "Test2", "test2@email.com", ""));
        clientList.add(new ClientRegistrationRequest(
                "Test2", "test2@email.com", randomAlphanumeric(129)));

        for (ClientRegistrationRequest regClient : clientList) {

            mockMvc.perform(post(URL)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(objMapper.writeValueAsString(regClient)))
                    .andExpect(status().isBadRequest());
        }
    }

//    @Test
//    public void updateResetPasswordSuccess() throws Exception{
//
//        ClientUpdateResetPasswordRequest updatePassword = new ClientUpdateResetPasswordRequest(
//                "Test5", "xxx", "xxxx", "xxxx");

//        mockMvc.perform(post(URL)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(objMapper.writeValueAsString(regClient)))
//                .andExpect(status().isCreated());
//    }

//    @Test
//    public void updateResetPasswordError() throws Exception {
//
//        List<ClientUpdateResetPasswordRequest> clientList = new ArrayList<>();
//
//        clientList.add(new ClientUpdateResetPasswordRequest(
//                "", "xxx", "xxxx", "xxxx"));
//        clientList.add(new ClientUpdateResetPasswordRequest(
//                "Te", "test4@email.com", "xxx"));
//        clientList.add(new ClientUpdateResetPasswordRequest(
//                randomAlphanumeric(51),
//                "test4@email.com", "xxx"));
//        clientList.add(new ClientUpdateResetPasswordRequest(
//                "Test2", "", "xxx"));
//        clientList.add(new ClientUpdateResetPasswordRequest(
//                "Test2", randomAlphanumeric(101)+ "@email.com",
//                "xxx"));
//        clientList.add(new ClientUpdateResetPasswordRequest(
//                "Test2", "test2email.com",
//                "xxx"));
//        clientList.add(new ClientUpdateResetPasswordRequest(
//                "Test2", "test2@email.com", ""));
//        clientList.add(new ClientUpdateResetPasswordRequest(
//                "Test2", "test2@email.com", randomAlphanumeric(129)));
//
//        for (ClientUpdateResetPasswordRequest regClient : clientList) {
//
//            mockMvc.perform(post(URL)
//                    .contentType(MediaType.APPLICATION_JSON_VALUE)
//                    .content(objMapper.writeValueAsString(regClient)))
//                    .andExpect(status().isBadRequest());
//        }
//    }
}
