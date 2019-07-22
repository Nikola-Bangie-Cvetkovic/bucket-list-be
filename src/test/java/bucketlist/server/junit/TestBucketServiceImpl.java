package bucketlist.server.junit;

import bucketlist.server.entities.Bucket;
import bucketlist.server.entities.Client;
import bucketlist.server.entities.dto.request.BucketSaveEditRequest;
import bucketlist.server.respositories.BucketDao;
import bucketlist.server.services.implementation.BucketServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;

public class TestBucketServiceImpl {

	@InjectMocks
	BucketServiceImpl bucketServiceImpl;

	@Mock
	BucketDao bucketDao;

	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void save(){
		BucketSaveEditRequest bucketSaveEditRequest = new BucketSaveEditRequest("Title1", "Text1", null, new Client());
		bucketServiceImpl.save(bucketSaveEditRequest);
		Bucket bucket = new Bucket(bucketSaveEditRequest);
		Mockito.verify(bucketDao, times(1)).save(bucket);
	}

	@Test
	public void delete(){
		bucketServiceImpl.delete(1);
		Mockito.verify(bucketDao).deleteById(1);
	}

	@Test
	public void update(){
		BucketSaveEditRequest bucketSaveEditRequest = new BucketSaveEditRequest("Title1", "Text2", true, new Client());
		bucketServiceImpl.update(bucketSaveEditRequest);
		Bucket bucket = new Bucket(bucketSaveEditRequest);
		Mockito.verify(bucketDao).save(bucket);
	}
}
