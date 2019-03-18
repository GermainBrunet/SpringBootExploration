package ca.gb.sf.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import ca.gb.sf.models.WebSite;

@CrossOrigin
@RepositoryRestResource(collectionResourceRel = "websites", path = "websites")
public interface WebSiteRepository extends CrudRepository<WebSite, Long> {

    @Override
    @RestResource(exported = false)
    void delete(WebSite entity);

    @Override
    @RestResource(exported = false)
    void deleteAll();

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends WebSite> entities);

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);
	
    @RestResource(path = "findByUrl", rel = "findByUrl")
    @Query("SELECT w FROM WebSite w where w.url = :url")
	WebSite findByUrl(@Param("url") String url);
    
}
