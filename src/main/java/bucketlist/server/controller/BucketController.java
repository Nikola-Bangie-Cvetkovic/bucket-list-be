package bucketlist.server.controller;

import bucketlist.server.entities.dto.request.BucketSaveEditRequest;
import bucketlist.server.services.BucketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/buckets")
public class BucketController {

    private BucketService service;

    public BucketController(BucketService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity create(@Valid @RequestBody BucketSaveEditRequest bucket, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        service.save(bucket);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity update(@Valid @RequestBody BucketSaveEditRequest bucket, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        service.update(bucket);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        if (id == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        service.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
