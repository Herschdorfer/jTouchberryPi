package at.tlphotography.jtouchberry.heating.equipment.db;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "equipment")
public class Equipment implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5384123904697404370L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipment", fetch = FetchType.LAZY)
    private List<Switch> switches;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipment", fetch = FetchType.LAZY)
    private List<Sensor> sensors;

    public Equipment() {

    }

    public Equipment(long id) {
	this.id = id;
    }

    public Equipment(String name) {
	this.name = name;
    }

    public long getId() {
	return id;
    }

    public void setId(long value) {
	this.id = value;
    }

    public String getName() {
	return name;
    }

    public void setName(String value) {
	this.name = value;
    }

    public List<Switch> getSwitches() {
	return switches;
    }

    public void setSwitches(List<Switch> switches) {
	this.switches = switches;
    }

    public List<Sensor> getSensors() {
	return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
	this.sensors = sensors;
    }

}
