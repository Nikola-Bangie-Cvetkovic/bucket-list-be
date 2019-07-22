package bucketlist.server.controller;

import bucketlist.server.entities.dto.request.ClientRegistrationRequest;
import bucketlist.server.entities.dto.request.ClientUpdateResetPasswordRequest;
import bucketlist.server.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping()
    public ResponseEntity registration(@Valid @RequestBody ClientRegistrationRequest client, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        service.registration(client);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity updateResetPassword(@Valid @RequestBody ClientUpdateResetPasswordRequest client, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        service.updateResetPassword(client);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deactivationAccount(@PathVariable Integer id){
        if(id == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        service.deactivationAccount(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
