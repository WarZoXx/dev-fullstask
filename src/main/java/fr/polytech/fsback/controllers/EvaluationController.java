package fr.polytech.fsback.controllers;

import fr.polytech.fsback.dto.EvaluationDto;
import fr.polytech.fsback.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationService evaluationService;

    // Appel de la méthode d'ajout de commentaire sur un restaurant
    // L'ID du restaurant est passé dans l'url tandis que le nom de l'evaluateur,
    // le commentaire et le nombre d'étoiles sont dans le RequestBody
    @PostMapping("restaurants/{restaurantId}/evaluations")
    public @ResponseBody EvaluationDto addEvaluation(@Valid @RequestBody EvaluationDto dto, @PathVariable int restaurantId) {
        return EvaluationDto.fromEntity(this.evaluationService.addEvaluation(restaurantId, dto.getNom_evaluateur(),dto.getCommentaire(),dto.getEtoiles()));
    }

    // Appel de la méthode du suppression d'un commentaire en fonction d'un ID passé dans le body
    @DeleteMapping("restaurants/evaluations/{evalId}")
    public void deleteEvaluation(@PathVariable int evalId){
        this.evaluationService.deleteEvaluation(evalId);
    }

}
