package at.tlphotography.jtouchberry.heating.equipment.db;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "equipment_sensors")
public class Sensor implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -3028295673947902083L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equimpent_id")
    private Equipment equipment;

    private String uri;

    private String parseRegex;

    public Sensor() {

    }

    public Sensor(String name, String uri, String parseRegex, Equipment equipment) {
	this.name = name;
	this.uri = uri;
	this.parseRegex = parseRegex;
	this.equipment = equipment;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setEquipment(Equipment equipment) {
	this.equipment = equipment;

    }

    public String getUri() {
	return uri;
    }

    public void setUri(String uri) {
	this.uri = uri;
    }

    public String getParseRegex() {
	return parseRegex;
    }

    public void setParseRegex(String parseRegex) {
	this.parseRegex = parseRegex;
    }
}
