package spring.springbootwebjpathymeleaf.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name =  "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "FIO")
	private String FIO;
	
	@Column(name = "post")
	private String post;

	@Column(name = "efficiency_percent")
	private String efficiencyPercent;

	@Column(name = "problem_areas")
	private String problemAreas;
	
	private String email;
	
	private String password;

//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "FIO", referencedColumnName = "id")
//	private List<Contract> contracts;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private List<Project> projects;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(
		            name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
				            name = "role_id", referencedColumnName = "id"))
	
	private Collection<Role> roles;
	
	public User() {
		
	}
	
	public User(String FIO, String post, String email, String password, Collection<Role> roles) {
		super();
		this.FIO = FIO;
		this.post = post;
		this.email = email;
		this.password = password;
//		this.efficiencyPercent = efficiencyPercent;
//		this.problemAreas = problemAreas;
		this.roles = roles;

	}

	public String getEfficiencyPercent() {
		return efficiencyPercent;
	}

	public void setEfficiencyPercent(String efficiencyPercent) {
		this.efficiencyPercent = efficiencyPercent;
	}

	public String getProblemAreas() {
		return problemAreas;
	}

	public void setProblemAreas(String problemAreas) {
		this.problemAreas = problemAreas;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFIO() {
		return FIO;
	}
	public void setFIO(String FIO) {
		this.FIO = FIO;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
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
	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

}
