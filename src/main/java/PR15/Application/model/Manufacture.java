package PR15.Application.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "manufactures")
public class Manufacture implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "address")
    @NotNull
    private String address;

//    @Column(name = "middle_name")
//    @NotNull
//    private String middleName;
//
//    @Column(name = "birth_date")
//    @NotNull
//    private String birthDate;

    public Manufacture() {

    }

//    public Manufacture(String name, String address, String middleName, String birthDate) {
//        this.name = name;
//        this.address = address;
//        this.middleName = middleName;
//        this.birthDate = birthDate;
//    }
    public Manufacture(String name, String address) {
        this.name = name;
        this.address = address;
    }
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }


    @Override
    public String toString() {
        return "Manufacture #" + id + " " + name + " " + address;
    }
}
