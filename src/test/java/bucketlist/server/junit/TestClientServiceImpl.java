/*
    Created: Frosty 14.05.2019
    Updated: /
*/
package bucketlist.server.junit;


import bucketlist.server.entities.Client;
import bucketlist.server.entities.dto.request.ClientRegistrationRequest;
import bucketlist.server.entities.dto.request.ClientUpdateResetPasswordRequest;
import bucketlist.server.respositories.ClientDao;
import bucketlist.server.services.implementation.ClientServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TestClientServiceImpl {
    @InjectMocks
    ClientServiceImpl clientService;

    @Mock
    ClientDao clientDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void registration() {
        ClientRegistrationRequest clientRegistrationRequest = new ClientRegistrationRequest();
        clientService.registration(clientRegistrationRequest);
        Client client = new Client(clientRegistrationRequest);
        Mockito.verify(clientDao).save(client);
    }

    @Test
    public void deactivationAccount() {
        Integer id = 47;
        clientService.deactivationAccount(id);
        Mockito.verify(clientDao).deleteById(id);
    }

    @Test
    public void updateResetPassword() {
        ClientUpdateResetPasswordRequest resetPassword =
                new ClientUpdateResetPasswordRequest(
                        "name", "oldPassword"
                        , "newPassword", "newPassword"
                );

        Client client = new Client();
        Mockito.when(clientDao.findByName(resetPassword.getName())).thenReturn(client);
        clientService.updateResetPassword(resetPassword);
        Assert.assertEquals(resetPassword.getNewPassword(), resetPassword.getRepeatNewPassword());
        Mockito.verify(clientDao).save(client);
    }
}
