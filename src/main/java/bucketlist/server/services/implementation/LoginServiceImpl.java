package bucketlist.server.services.implementation;

import bucketlist.server.entities.Client;
import bucketlist.server.respositories.ClientDao;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class LoginServiceImpl implements UserDetailsService {

    private ClientDao clientDao;

    public LoginServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientDao.findByName(username);

        if (client == null){
            throw new UsernameNotFoundException(username);
        }

        return new User(client.getName(), client.getPassword(), new ArrayList<>());
    }
}
