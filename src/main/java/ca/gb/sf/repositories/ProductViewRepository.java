package ca.gb.sf.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.gb.sf.models.ProductPrice;
import ca.gb.sf.models.ProductView;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "productView", path = "productView")
public interface ProductViewRepository extends CrudRepository<ProductView, Long> {

    @RestResource(path = "productView", rel = "productView")
    // @Query("SELECT pp FROM ProductPrice pp WHERE pp.productPriceSource.id = :productPriceSourceId AND pp.date = :date")
    List<ProductView> findByProductId(@Param("productPriceSourceId") Long productId);
    
    
}
