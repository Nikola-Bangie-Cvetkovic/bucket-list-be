package bucketlist.server.junit;

import bucketlist.server.controller.BucketController;
import bucketlist.server.entities.dto.request.BucketSaveEditRequest;
import bucketlist.server.services.BucketService;
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

public class TestBucketController {
    @InjectMocks
    BucketController bucketController;

    @Mock
    BucketService bucketService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createSuccess(){
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        BucketSaveEditRequest bucket = new BucketSaveEditRequest();

        ResponseEntity responseEntity = bucketController.create(bucket, bindingResult);
        Mockito.verify(bucketService).save(bucket);
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void createError(){
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        BucketSaveEditRequest bucket = new BucketSaveEditRequest();

        ResponseEntity responseEntity = bucketController.create(bucket, bindingResult);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void updateSuccess(){
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        BucketSaveEditRequest bucket = new BucketSaveEditRequest();

        ResponseEntity responseEntity = bucketController.update(bucket, bindingResult);
        Mockito.verify(bucketService).update(bucket);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void updateError(){
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        BucketSaveEditRequest bucket = new BucketSaveEditRequest();

        ResponseEntity responseEntity = bucketController.update(bucket, bindingResult);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void deleteSuccess(){
        Integer id = 47;

        ResponseEntity responseEntity = bucketController.delete(id);
        Mockito.verify(bucketService).delete(id);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void deleteError(){
        Integer id = null;

        ResponseEntity responseEntity = bucketController.delete(id);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
