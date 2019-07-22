package bucketlist.server.entities.dto.request;

import bucketlist.server.entities.Bucket;
import bucketlist.server.entities.Client;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BucketSaveEditRequest {
    @NotNull
    @Size(min = 3, max = 30)
    private String title;
    @Size(max = 1000)
    private String text;
    private Boolean finished;
    @NotNull
    private Client client;

    public BucketSaveEditRequest() {
    }

    public BucketSaveEditRequest(@NotNull @Size(min = 3, max = 30) String title,
                                 @Size(max = 1000) String text,
                                 Boolean finished, @NotNull Client client) {
        this.title = title;
        this.text = text;
        this.finished = finished;
        this.client = client;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
