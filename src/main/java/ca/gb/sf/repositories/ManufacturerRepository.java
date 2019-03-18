package ca.gb.sf.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.gb.sf.models.Manufacturer;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "manufacturers", path = "manufacturers")
public interface ManufacturerRepository extends CrudRepository<Manufacturer, Long> {

    @Override
    @RestResource(exported = false)
    void delete(Manufacturer entity);

    @Override
    @RestResource(exported = false)
    void deleteAll();

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Manufacturer> entities);

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);

    @RestResource(path = "findByName", rel = "findByName")
    @Query("SELECT t FROM Manufacturer t where t.name = :name")
	Manufacturer findByName(@Param("name") String name);
	
}
