package bucketlist.server.services;

import bucketlist.server.entities.Client;
import bucketlist.server.entities.dto.request.ClientRegistrationRequest;
import bucketlist.server.entities.dto.request.ClientUpdateResetPasswordRequest;

public interface ClientService {
    void registration(ClientRegistrationRequest clientRegistration);
    void updateResetPassword(ClientUpdateResetPasswordRequest resetPassword);
    void deactivationAccount(Integer id);
    Client getOne(String name);
}
