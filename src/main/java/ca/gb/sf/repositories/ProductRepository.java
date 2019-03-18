package ca.gb.sf.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.gb.sf.models.Product;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Override
    @RestResource(exported = false)
    void delete(Product entity);

    @Override
    @RestResource(exported = false)
    void deleteAll();

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Product> entities);

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);

    @RestResource(path = "byName", rel = "customFindMethod")
    Product findByName(@Param("name") String name);
	
    @RestResource(path = "findByName", rel = "findByName")
    @Query("SELECT p FROM Product p INNER JOIN Manufacturer m ON p.manufacturer.id = m.id WHERE p.name = :name AND p.modelNumber = :number AND m.id = :manufacturerId")
    Product findByManufacturerNameNumber(@Param("manufacturerId") Long manufacturerId, @Param("name") String name, @Param("number") String number);
    
}
