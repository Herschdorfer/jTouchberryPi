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
@Table(name = "equipment_switches")
public class Switch implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8061990548015324788L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String onUri;

    @NotNull
    private String offUri;

    @NotNull
    private String statusUri;

    private String invokationPayload;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equimpent_id")
    private Equipment equipment;

    public Switch() {

    }

    public Switch(String name, String onUri, String offUri, String statusUri, String invokationPayload,
	    Equipment equipment) {
	this.name = name;
	this.onUri = onUri;
	this.offUri = offUri;
	this.statusUri = statusUri;
	this.setInvokationPayload(invokationPayload);
	this.equipment = equipment;
    }

    public long getId() {
	return id;
    }

    public String getInvokationPayload() {
	return invokationPayload;
    }

    public String getName() {
	return name;
    }

    public String getOffUri() {
	return offUri;
    }

    public String getOnUri() {
	return onUri;
    }

    public String getStatusUri() {
	return statusUri;
    }

    public void setEquipment(Equipment equipment) {
	this.equipment = equipment;
    }

    public void setId(long id) {
	this.id = id;
    }

    public void setInvokationPayload(String invokationPayload) {
	this.invokationPayload = invokationPayload;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setOffUri(String offUri) {
	this.offUri = offUri;
    }

    public void setOnUri(String onUri) {
	this.onUri = onUri;
    }

    public void setStatusUri(String statusUri) {
	this.statusUri = statusUri;
    }

}
