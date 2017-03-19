package at.tlphotography.jtouchberry.forecast.db;

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
@Table(name = "forecast")
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String picture;

    @NotNull
    private String temperatue;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forecastList")
    private ForecastList forecastList;

    public Forecast() {
	// need to be public
    }

    public Forecast(long id) {
	this.id = id;
    }

    public Forecast(String picture, String temperature) {
	this.picture = picture;
	this.temperatue = temperature;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getPicture() {
	return picture;
    }

    public void setPicture(String picture) {
	this.picture = picture;
    }

    public String getTemperatue() {
	return temperatue;
    }

    public void setTemperatue(String temperatue) {
	this.temperatue = temperatue;
    }

}