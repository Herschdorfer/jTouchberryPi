package at.tlphotography.jtouchberry.forecast.db;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface ForecastDao extends CrudRepository<Forecast, Long> {

}