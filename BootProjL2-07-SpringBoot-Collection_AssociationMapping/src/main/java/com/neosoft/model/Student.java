package com.neosoft.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.ListIndexBase;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="Stud")
public class Student {
	
	@Id
	@SequenceGenerator(name = "gen1",sequenceName = "SQQ1",initialValue = 1,allocationSize = 1)
	@GeneratedValue(generator = "gen1",strategy = GenerationType.SEQUENCE)
	private Integer sno;
	private String name;
	@ElementCollection
	@CollectionTable(name = "Stud_frnds",joinColumns = @JoinColumn(columnDefinition = "fid",referencedColumnName = "sno"))
	@Column(name = "friend_name")
	/*@OrderColumn(name = "Index")
	@ListIndexBase(1)*/
	private List<String> frnds;
	@ElementCollection
	@CollectionTable(name = "Stud_mob",joinColumns = @JoinColumn(columnDefinition = "mob_id",referencedColumnName = "sno"))
	@Column(name = "mobile_number")
	private Set<String> mob;
	@ElementCollection
	@CollectionTable(name="Stud_Id_Details",joinColumns = @JoinColumn(columnDefinition = "cid",referencedColumnName = "sno"))
	@Column(name = "id_value")
	@MapKeyColumn(name = "Id_name")
	private Map<String,String> idDetails;
	
	@OneToMany(targetEntity = Address.class,cascade =CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "aid",referencedColumnName = "sno")
	private List<Address> add;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}
	

	public Student(Integer sno, String name, List<String> frnds, Set<String> mob, Map<String, String> idDetails,
			List<Address> add) {
		super();
		this.sno = sno;
		this.name = name;
		this.frnds = frnds;
		this.mob = mob;
		this.idDetails = idDetails;
		this.add = add;
	}


	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getFrnds() {
		return frnds;
	}

	public void setFrnds(List<String> frnds) {
		this.frnds = frnds;
	}

	public Set<String> getMob() {
		return mob;
	}

	public void setMob(Set<String> mob) {
		this.mob = mob;
	}

	public Map<String, String> getIdDetails() {
		return idDetails;
	}

	public void setIdDetails(Map<String, String> idDetails) {
		this.idDetails = idDetails;
	}

	public List<Address> getAdd() {
		return add;
	}

	public void setAdd(List<Address> add) {
		this.add = add;
	}

	@Override
	public String toString() {
		return "Student [sno=" + sno + ", name=" + name + ", frnds=" + frnds + ", mob=" + mob + ", idDetails="
				+ idDetails + ", add=" + add + "]";
	}
	

}
