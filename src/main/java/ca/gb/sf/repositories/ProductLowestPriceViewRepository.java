package ca.gb.sf.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.gb.sf.models.ProductLowestPriceView;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "productView", path = "productView")
public interface ProductLowestPriceViewRepository extends CrudRepository<ProductLowestPriceView, Long> {

    Page<ProductLowestPriceView> findAll(Pageable pageable);
    
    @Query(value = "SELECT p "
    		+ "     FROM ProductLowestPriceView p "
    		+ "     WHERE "
    		+ "       lower(p.productName) LIKE :searchString OR "
    		+ "       lower(p.manufacturerName) LIKE :searchString OR "
    		+ "       lower(p.modelNumber) LIKE :searchString")
    Page<ProductLowestPriceView> searchByName(Pageable pageable, @Param("searchString") String searchString);
    
}
