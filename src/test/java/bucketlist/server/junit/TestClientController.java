/*
    Created: Frosty 14.05.2019
    Updated: /
*/
package bucketlist.server.junit;

import bucketlist.server.controller.ClientController;
import bucketlist.server.entities.dto.request.ClientRegistrationRequest;
import bucketlist.server.entities.dto.request.ClientUpdateResetPasswordRequest;
import bucketlist.server.services.ClientService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;


public class TestClientController {
    @InjectMocks
    ClientController clientController;

    @Mock
    ClientService clientService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void registrationSuccess() {
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        ClientRegistrationRequest client = new ClientRegistrationRequest();

        ResponseEntity responseEntity = clientController.registration(client, bindingResult);
        Mockito.verify(clientService).registration(client);
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void registrationError() {
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        ClientRegistrationRequest client = new ClientRegistrationRequest();

        ResponseEntity responseEntity = clientController.registration(client, bindingResult);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void updateResetPasswordSuccess() {
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        ClientUpdateResetPasswordRequest client = new ClientUpdateResetPasswordRequest();

        ResponseEntity responseEntity = clientController.updateResetPassword(client, bindingResult);
        Mockito.verify(clientService).updateResetPassword(client);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void updateResetPasswordError() {
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        ClientUpdateResetPasswordRequest client = new ClientUpdateResetPasswordRequest();

        ResponseEntity responseEntity = clientController.updateResetPassword(client, bindingResult);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void deactivationAccountSuccess() {
        Integer id = 47;
        Assert.assertNotNull(id);
        ResponseEntity responseEntity = clientController.deactivationAccount(id);
        Mockito.verify(clientService).deactivationAccount(id);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void deactivationAccountError() {
        Integer id = null;
        Assert.assertNull(id);
        ResponseEntity responseEntity = clientController.deactivationAccount(id);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
