package fr.polytech.fsback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.polytech.fsback.entity.RestaurantEntity;
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
public class RestaurantDto {

    @JsonProperty("id")
    private int id;

    @JsonProperty("nom")
    @Size(max = 90)
    @NotBlank
    private String nom;

    @JsonProperty("adresse")
    @Size(max = 255)
    @NotBlank
    private String adresse;

    @JsonProperty("evaluations")
    private List<EvaluationDto> evaluations;

    @JsonProperty("moyenne")
    private float moyenne;

    public static RestaurantDto fromEntity(RestaurantEntity restaurantEntity) {
        if(restaurantEntity.getEvaluations().size() ==0){
            return RestaurantDto.builder().
                    id(restaurantEntity.getId())
                    .nom(restaurantEntity.getNom())
                    .adresse(restaurantEntity.getAdresse())
                    .evaluations(null)
                    .moyenne(-1)
                    .build();
        }
        else{
            float somme =0;
            for(int i=0;i<restaurantEntity.getEvaluations().size();i++){
                somme+=restaurantEntity.getEvaluations().get(i).getEtoiles();
            }
            float moy = somme/(float)restaurantEntity.getEvaluations().size();
            return RestaurantDto.builder().
                    id(restaurantEntity.getId())
                    .nom(restaurantEntity.getNom())
                    .adresse(restaurantEntity.getAdresse())
                    .evaluations(restaurantEntity.getEvaluations().stream().map(evaluation -> EvaluationDto.fromEntity(evaluation)).collect(Collectors.toList()))
                    .moyenne(moy)
                    .build();
        }

    }

}
