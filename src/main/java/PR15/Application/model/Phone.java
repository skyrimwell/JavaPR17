package PR15.Application.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Phones")
public class Phone {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name")
    @NotNull
    private String name;

    @CreationTimestamp
    @Column(name = "creation_year")
    private LocalDateTime creationYear;

    @Column(name = "owner")
    @NotNull
    private UUID owner;

    public Phone() {

    }

    public Phone(String name, UUID owner, LocalDateTime creationYear) {
        this.name = name;
        this.owner = owner;
        this.creationYear = creationYear;
    }

    public UUID getId() {
        return id;
    }

    public UUID getOwner() {
        return owner;
    }



    public String getName() {
        return name;
    }

    @ManyToOne
    public Manufacture manufacture;

    public Manufacture getManufacture(){return manufacture;}

    public LocalDateTime getCreationYear() {
        return creationYear;
    }

}