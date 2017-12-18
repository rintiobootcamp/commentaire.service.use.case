/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.helpers;

import com.bootcamp.client.MediaClient;
import com.bootcamp.commons.ws.usecases.pivotone.CommentaireWS;
import com.bootcamp.commons.ws.usecases.pivotone.MediaWs;
import com.bootcamp.entities.Commentaire;
import com.bootcamp.entities.Media;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bello
 */
public class CommentaireHelper {

    /**
     * Build the CommentaireWS object from a Commentaire object and the medias
     * list
     *
     * @param commentaire
     * @param medias
     * @return commentaireWS
     * @throws IOException
     */
    public CommentaireWS buildCommentaireWsObject(Commentaire commentaire, List<MediaWs> medias) throws IOException {
        CommentaireWS commentaireWS = new CommentaireWS();
        commentaireWS.setEntityId(commentaire.getEntityId());
        commentaireWS.setEntityType(commentaire.getEntityType());
        commentaireWS.setContenu(commentaire.getContenu());
        commentaireWS.setPseudo(commentaire.getPseudo());
        commentaireWS.setUserMail(commentaire.getUserMail());
        commentaireWS.setUserId(commentaire.getUserId());
        commentaireWS.setDateCreation(commentaire.getDateCreation());
        commentaireWS.setDateMiseAJour(commentaire.getDateMiseAJour());
        commentaireWS.setMedias(medias);

        return commentaireWS;
    }

    /**
     * Build the MediaWs object from a Media object
     *
     * @param media
     * @return mediaWs
     * @throws IOException
     */
    public MediaWs buildMediaWsObject(Media media) throws IOException {
        MediaWs mediaWs = new MediaWs();
        mediaWs.setId(media.getId());
        mediaWs.setLien(media.getLien());
        mediaWs.setNom(media.getInternalName());
        mediaWs.setType(media.getType());

        return mediaWs;
    }
    
    /**
     * Get all the medias related to the given comment in the database
     *
     * @param commentaireId
     * @return comments list
     * @throws IOException
     */
    public List<MediaWs> getAllMedias(int commentaireId) throws IOException {
        MediaClient mediaClient = new MediaClient();
        List<Media> medias = mediaClient.getByEntityTypeAndEntityId(commentaireId, "COMMENTAIRE");
        
        List<MediaWs> mediaWss = new ArrayList<MediaWs>();
        for (Media current : medias) {
            MediaWs mediaWs = new MediaWs();
            mediaWs = this.buildMediaWsObject(current);
            mediaWss.add(mediaWs);
        }
        return mediaWss;
    }

}
