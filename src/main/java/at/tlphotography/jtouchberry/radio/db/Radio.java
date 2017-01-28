package at.tlphotography.jtouchberry.radio.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "radio")
public class Radio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String name;

	@NotNull
	private String uri;

	public Radio() {
	}

	public Radio(long id) {
		this.id = id;
	}

	public Radio(String uri, String name) {
		this.uri = uri;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long value) {
		this.id = value;
	}

	public String getUri() {
		return uri;
	}

	public void setSet(String uri) {
		this.uri = uri;
	}

	public String getName() {
		return name;
	}

	public void setName(String value) {
		this.name = value;
	}

}