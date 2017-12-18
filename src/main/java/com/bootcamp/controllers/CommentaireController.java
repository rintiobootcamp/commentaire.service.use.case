package com.bootcamp.controllers;

import com.bootcamp.commons.ws.usecases.pivotone.CommentaireWS;
import com.bootcamp.services.CommentaireService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author Bello
 */
@RestController("CommentaireController")
@RequestMapping("/commentaires")
@Api(value = "Commentaire API", description = "Commentaire API")
@CrossOrigin(origins = "*")
public class CommentaireController {

    @Autowired
    CommentaireService commentaireService;

    /**
     * Get all the comments related to the given entity in the database
     *
     * @param entityType
     * @param entityId
     * @return comments list
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{entityType}/{entityId}")
    @ApiOperation(value = " Get all commentaires", notes = "Get all the commentaires")
    public ResponseEntity<List<CommentaireWS>> getByEntity(@PathVariable("entityType") String entityType, @PathVariable("entityId") int entityId) throws IOException {
        List<CommentaireWS> commentaires = commentaireService.getAll(entityId, entityType);
        return new ResponseEntity<>(commentaires, HttpStatus.OK);

    }

    /**
     * Get a comment knowing its id
     *
     * @param id the comment id
     * @return a comment
     * @throws IOException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = " Get one commentaire", notes = "Get particular commentaire by it id")
    public ResponseEntity<CommentaireWS> getById(@PathVariable int id) throws IOException {
        CommentaireWS commentaire = commentaireService.getCommentaire(id);
        return new ResponseEntity<>(commentaire, HttpStatus.OK);
    }
}
