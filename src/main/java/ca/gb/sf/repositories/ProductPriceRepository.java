package ca.gb.sf.repositories;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.gb.sf.models.ProductPrice;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "productPrices", path = "productPrices")
public interface ProductPriceRepository extends CrudRepository<ProductPrice, Long> {

    @Override
    @RestResource(exported = false)
    void delete(ProductPrice entity);

    @Override
    @RestResource(exported = false)
    void deleteAll();

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends ProductPrice> entities);

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);
	
    @RestResource(path = "findByProductPriceSourceTimestamp", rel = "findByProductPriceSourceTimestamp")
    @Query("SELECT pp FROM ProductPrice pp WHERE pp.productPriceSource.id = :productPriceSourceId AND pp.date = :date")
    ProductPrice findByProductPriceSourceTimestamp(@Param("productPriceSourceId") Long productPriceSourceId, @Param("date") Timestamp date);
    
    // ProductPrice
    
    
    
}
