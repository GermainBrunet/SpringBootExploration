package ca.gb.sf.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.gb.sf.models.ProductPriceSource;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "productPriceSources", path = "productPriceSources")
public interface ProductPriceSourceRepository extends CrudRepository<ProductPriceSource, Long> {

    @Override
    @RestResource(exported = false)
    void delete(ProductPriceSource entity);

    @Override
    @RestResource(exported = false)
    void deleteAll();

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends ProductPriceSource> entities);

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);

    @RestResource(path = "findByProductWebSite", rel = "findByProductWebSite")
    @Query("SELECT pps FROM ProductPriceSource pps WHERE pps.product.id = :productId AND pps.webSite.id = :webSiteId")
    ProductPriceSource findByProductWebSite(@Param("productId") Long productId, @Param("webSiteId") Long webSiteId);
    
    // Find the most recent price for a given product.
    
    
}
