package at.tlphotography.jtouchberry.forecast.db;

import java.util.ArrayList;
import java.util.GregorianCalendar;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "forecastList")
public class ForecastList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(unique = true)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date myDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "forecastList", fetch = FetchType.LAZY)
    private List<Forecast> forecasts = new ArrayList<>();

    public ForecastList() {

    }

    public ForecastList(long id) {
	this.id = id;

    }

    public ForecastList(GregorianCalendar cal) {
	this.myDate = cal.getTime();
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public java.util.Date getMyDate() {
	return myDate;
    }

    public void setMyDate(java.util.Date myDate) {
	this.myDate = myDate;
    }

    public List<Forecast> getForecasts() {
	return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
	this.forecasts = forecasts;
    }

}
