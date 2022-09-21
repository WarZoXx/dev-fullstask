package fr.polytech.fsback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.polytech.fsback.entity.EvaluationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EvaluationDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("nom_evaluateur")
    @Size(max = 50)
    @NotBlank
    private String nom_evaluateur;

    @JsonProperty("commentaire")
    @Size(max = 255)
    @NotBlank
    private String commentaire;

    @JsonProperty("etoiles")
    private int etoiles;

    public static EvaluationDto fromEntity(EvaluationEntity evaluationEntity) {
        return EvaluationDto.builder().
                id(evaluationEntity.getId())
                .nom_evaluateur(evaluationEntity.getNom_evaluateur())
                .commentaire(evaluationEntity.getCommentaire())
                .etoiles(evaluationEntity.getEtoiles()).build();
    }

}
