package bucketlist.server.entities.dto.request;


import bucketlist.server.entities.Client;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClientRegistrationRequest{
    @NotNull
    @Size(min = 3, max = 50)
    private String name;
    @NotNull
    @Email
    @Size(min = 5, max = 100)
    private String email;
    @NotNull
    @Size(min = 3, max = 128)
    private String password;

    public ClientRegistrationRequest() {
    }

    public ClientRegistrationRequest(@NotNull @Size(min = 3, max = 50) String name,
                                     @NotNull @Email @Size(min = 5, max = 100) String email,
                                     @NotNull @Size(min = 3, max = 128) String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
