package spring.springbootwebjpathymeleaf.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "monitoring")
@Data
public class Monitoring {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "FIO_analyst")
    private String FIOAnalyst;
    @Column(name = "project_name")
    private String projectName;
    @Column(name = "risk")
    private String risk;


//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "realty_id", referencedColumnName = "id")
//    private List<Contract> contracts;

    public Monitoring() {
    }

    public Monitoring(String FIOAnalyst, String projectName, String risk) {
        this.FIOAnalyst = FIOAnalyst;
        this.projectName = projectName;
        this.risk = risk;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFIOAnalyst() {
        return FIOAnalyst;
    }

    public void setFIOAnalyst(String FIO_analyst) {
        this.FIOAnalyst = FIO_analyst;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String project_name) {
        this.projectName = project_name;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }
}