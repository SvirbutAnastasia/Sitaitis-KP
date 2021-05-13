package spring.springbootwebjpathymeleaf.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "project")
@Data
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "ready_percent")
    private Double readyPercent;
    @Column(name = "FIO_employee")
    private String FIOEmployee;


@OneToMany(cascade = CascadeType.ALL)
@JoinColumn(name = "project_id", referencedColumnName = "id")
private List<Monitoring> monitorings;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String rentalOperationName) {
        this.name = rentalOperationName;
    }

    public Double getReadyPercent() {
        return readyPercent;
    }

    public void setReadyPercent(Double readyPercent) {
        this.readyPercent = readyPercent;
    }

    public String getFIOEmployee() {
        return FIOEmployee;
    }

    public void setFIOEmployee(String FIOEmployee) {
        this.FIOEmployee = FIOEmployee;
    }
}