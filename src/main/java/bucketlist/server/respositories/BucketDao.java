package bucketlist.server.respositories;

import bucketlist.server.entities.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public interface BucketDao extends JpaRepository<Bucket, Integer> {
}
