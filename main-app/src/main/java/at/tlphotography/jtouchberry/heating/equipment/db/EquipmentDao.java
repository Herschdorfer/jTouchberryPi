package at.tlphotography.jtouchberry.heating.equipment.db;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface EquipmentDao extends CrudRepository<Equipment, Long> {

}