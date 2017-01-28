package at.tlphotography.jtouchberry.radio.db;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface RadioDao extends CrudRepository<Radio, Long> {

  public Radio findByUri(String uri);

} 