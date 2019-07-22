package bucketlist.server.entities;

import bucketlist.server.entities.dto.request.BucketSaveEditRequest;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(schema = "9O3xmirHTL", name = "buckets")
@DynamicUpdate
public class Bucket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String title;
    @Column
    private String text;
    @Column
    @CreationTimestamp
    private Timestamp created;
    @Column
    @UpdateTimestamp
    private Timestamp updated;
    @Column
    private Boolean finished = false;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client client = new Client();

    public Bucket() {
    }

    public Bucket(String title, String text, Timestamp created, Timestamp updated, Boolean finished, Client client) {
        this.title = title;
        this.text = text;
        this.created = created;
        this.updated = updated;
        this.finished = finished;
        this.client = client;
    }

    public Bucket(String title, String text, Boolean finished, Client client) {
        this.title = title;
        this.text = text;
        this.finished = finished;
        this.client = client;
    }

    public Bucket(BucketSaveEditRequest bucketDto) {
        this.text = bucketDto.getText();
        this.title = bucketDto.getTitle();
        this.client = bucketDto.getClient();
        this.finished = bucketDto.getFinished();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
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
