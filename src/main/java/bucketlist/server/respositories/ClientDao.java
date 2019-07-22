package bucketlist.server.respositories;

import bucketlist.server.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public interface ClientDao extends JpaRepository<Client, Integer> {
    Client findByName(String name);
}