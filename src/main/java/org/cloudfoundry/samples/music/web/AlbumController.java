package org.cloudfoundry.samples.music.web;

import org.cloudfoundry.samples.music.domain.Album;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/albums")

public class AlbumController {
    private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);
    private CrudRepository<Album, String> repository;
    private int processingTime;

     /**
     * Just simulates some processing
     */
    public void doProcessing() {
          if(processingTime <= 0) processingTime = 10;
          try {
                java.lang.Thread.sleep(processingTime);
        } catch (InterruptedException e) {
                // there should really be no problem wkth this :-)
        }
    }

    @Autowired
    public AlbumController(CrudRepository<Album, String> repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Album> albums() {
        doProcessing();
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Album add(@RequestBody @Valid Album album) {
        logger.info("Adding album " + album.getId());
        doProcessing();
        return repository.save(album);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Album update(@RequestBody @Valid Album album) {
        logger.info("Updating album " + album.getId());
        doProcessing();
        return repository.save(album);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Album getById(@PathVariable String id) {
        logger.info("Getting album " + id);
        doProcessing();
        return repository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable String id) {
        logger.info("Deleting album " + id);
        doProcessing();
        repository.deleteById(id);
    }
}