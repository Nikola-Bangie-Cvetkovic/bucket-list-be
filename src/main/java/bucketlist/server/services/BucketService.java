package bucketlist.server.services;

import bucketlist.server.entities.dto.request.BucketSaveEditRequest;

public interface BucketService {
    void save(BucketSaveEditRequest bucketSaveEditRequest);
    void update(BucketSaveEditRequest bucketSaveEditRequest);
    void delete(Integer id);
}
