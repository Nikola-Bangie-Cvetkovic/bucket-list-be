package bucketlist.server.entities.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClientUpdateResetPasswordRequest {
    @NotNull
    @Size(min = 3, max = 50)
    private String name;
    @NotNull
    @Size(min = 3, max = 100)
    private String oldPassword;
    @NotNull
    @Size(min = 3, max = 100)
    private String newPassword;
    @NotNull
    @Size(min = 3, max = 100)
    private String repeatNewPassword;

    public ClientUpdateResetPasswordRequest() {
    }

    public ClientUpdateResetPasswordRequest(@NotNull @Size(min = 3, max = 50) String name,
                                            @NotNull @Size(min = 3, max = 100) String oldPassword,
                                            @NotNull @Size(min = 3, max = 100) String newPassword,
                                            @NotNull @Size(min = 3, max = 100) String repeatNewPassword) {
        this.name = name;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.repeatNewPassword = repeatNewPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatNewPassword() {
        return repeatNewPassword;
    }

    public void setRepeatNewPassword(String repeatNewPassword) {
        this.repeatNewPassword = repeatNewPassword;
    }
}
