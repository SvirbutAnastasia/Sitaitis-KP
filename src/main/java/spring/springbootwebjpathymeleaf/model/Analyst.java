package spring.springbootwebjpathymeleaf.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "analyst")
@Data
public class Analyst implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "FIO")
    private String FIO;
    @Column(name = "department")
    private String department;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "analyst_id", referencedColumnName = "id")
    private List<Monitoring> monitorings;

    public Analyst() {
    }

    public Analyst(String FIO, String department) {
        this.FIO = FIO;
        this.department = department;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}