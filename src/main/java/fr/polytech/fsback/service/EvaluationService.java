package fr.polytech.fsback.service;

import fr.polytech.fsback.entity.EvaluationEntity;
import fr.polytech.fsback.entity.RestaurantEntity;
import fr.polytech.fsback.exception.InvalidValueException;
import fr.polytech.fsback.repository.EvaluationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;

    private final RestaurantService restaurantService;

    // Enregistre une nouvelle evaluation sur un restaurant
    public EvaluationEntity addEvaluation(final int restaurantId,final String nom_evaluateur,final String commentaire,final int etoiles) {
        if(etoiles<0 || etoiles>3){
            throw new InvalidValueException("la note ne peut que prendre les valeurs (0,1,2,3).");
        }
        final RestaurantEntity restaurant = restaurantService.getRestaurantById(restaurantId);
        final EvaluationEntity nouvelleEvaluation = EvaluationEntity.builder().nom_evaluateur(nom_evaluateur).commentaire(commentaire).etoiles(etoiles).restaurant(restaurant).build();
        return this.evaluationRepository.save(nouvelleEvaluation);
    }

    // Supprime une evaluation spécifique
    public void deleteEvaluation(final int evaluationId){
        this.evaluationRepository.deleteById(evaluationId);
    }

}
