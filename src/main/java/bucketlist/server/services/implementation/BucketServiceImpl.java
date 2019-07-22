package bucketlist.server.services.implementation;

import bucketlist.server.entities.Bucket;
import bucketlist.server.entities.dto.request.BucketSaveEditRequest;
import bucketlist.server.respositories.BucketDao;
import bucketlist.server.services.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BucketServiceImpl implements BucketService {

    @Autowired
    private BucketDao bucketDao;

    @Override
    public void save(BucketSaveEditRequest bucketSaveEditRequest) {
        Bucket bucket = new Bucket(bucketSaveEditRequest);
        bucketDao.save(bucket);
    }
    //not good!!!!!!!!!!!!!!!
    @Override
    public void update(BucketSaveEditRequest bucketSaveEditRequest) {
        Bucket bucket = new Bucket(bucketSaveEditRequest);
        bucketDao.save(bucket);
    }

    @Override
    public void delete(Integer id) {
        bucketDao.deleteById(id);
    }
}