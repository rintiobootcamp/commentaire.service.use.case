package com.bootcamp.services;

import com.bootcamp.client.CommentaireClient;
import com.bootcamp.commons.ws.usecases.pivotone.CommentaireWS;
import com.bootcamp.commons.ws.usecases.pivotone.MediaWs;
import com.bootcamp.entities.Commentaire;
import com.bootcamp.helpers.CommentaireHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bello
 */
@Component
public class CommentaireService {

    CommentaireClient commentaireClient;
    CommentaireHelper commentaireHelper = new CommentaireHelper();

    /**
     * Loading Commentaire Web Service client
     */
    @PostConstruct
    public void init() {
        commentaireClient = new CommentaireClient();
    }

    /**
     * Get all the comments related to the given entity in the database
     *
     * @param entityId
     * @param entityType
     * @return comments list
     * @throws IOException
     */
    public List<CommentaireWS> getAll(int entityId, String entityType) throws IOException {
        List<Commentaire> commentaires = commentaireClient.getCommentByEntity(entityType, entityId);
        List<CommentaireWS> result = new ArrayList<CommentaireWS>();
        for (Commentaire current : commentaires) {
            CommentaireWS commentaireWS = new CommentaireWS();
            List<MediaWs> medias = commentaireHelper.getAllMedias(current.getId());
            commentaireWS = commentaireHelper.buildCommentaireWsObject(current, medias);
            result.add(commentaireWS);
        }
        return result;
    }

    /**
     * Get a comment knowing its id
     *
     * @param idCommentaire the comment id
     * @return a comment
     * @throws IOException
     */
    public CommentaireWS getCommentaire(int idCommentaire) throws IOException {
        Commentaire commentaire = commentaireClient.getById(idCommentaire);
        List<MediaWs> medias = commentaireHelper.getAllMedias(idCommentaire);
        return commentaireHelper.buildCommentaireWsObject(commentaire, medias);
    }
}
