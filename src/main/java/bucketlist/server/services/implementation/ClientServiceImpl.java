package bucketlist.server.services.implementation;

import bucketlist.server.entities.Client;
import bucketlist.server.entities.dto.request.ClientRegistrationRequest;
import bucketlist.server.entities.dto.request.ClientUpdateResetPasswordRequest;
import bucketlist.server.respositories.ClientDao;
import bucketlist.server.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public void registration(ClientRegistrationRequest clientRegistration) {
        clientRegistration.setPassword(bCryptPasswordEncoder.encode(clientRegistration.getPassword()));
        Client client = new Client(clientRegistration);
        clientDao.save(client);
    }

    @Override
    public void updateResetPassword(ClientUpdateResetPasswordRequest resetPassword) {
        Client client = clientDao.findByName(resetPassword.getName());
        if(resetPassword.getNewPassword().equals(resetPassword.getRepeatNewPassword())){
            client.setPassword(bCryptPasswordEncoder.encode(resetPassword.getNewPassword()));
            clientDao.save(client);
        }
    }

    @Override
    public void deactivationAccount(Integer id) {
        clientDao.deleteById(id);
    }

    @Override
    public Client getOne(String name) {
        return clientDao.findByName(name);
    }
}
