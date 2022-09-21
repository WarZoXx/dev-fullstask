package fr.polytech.fsback.service;

import fr.polytech.fsback.entity.RestaurantEntity;
import fr.polytech.fsback.exception.InvalidValueException;
import fr.polytech.fsback.exception.ResourceDoesntExistException;
import fr.polytech.fsback.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    // Renvoi les informations d'un restaurant spécifique (spécifié par son ID)
    public RestaurantEntity getRestaurantById(final int id) {
        RestaurantEntity restaurant = this.restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceDoesntExistException("le restaurant d'id " + id + " n'existe pas."));
        return restaurant;
    }

    // Renvoi la liste de tous les restaurants
    public List<RestaurantEntity> getAllRestaurants() {
        return this.restaurantRepository.findAll();
    }

    // Ajoute un restaurant à la base de données avec comme valeurs les paramétres passés par la couche controller
    public RestaurantEntity addRestaurant(final String nom,final String adresse) {
        final RestaurantEntity nouveaurestaurant = RestaurantEntity.builder().nom(nom).adresse(adresse).build();
        return this.restaurantRepository.save(nouveaurestaurant);
    }

    // Modifie les valeurs d'un restaurant en fonction des nouvelles valeurs des champs passés en paramètres
    public RestaurantEntity updateRestaurant(final int id,final String nouveauNom,final String nouvelleAdresse) {
        if (nouveauNom == null) {
            throw new InvalidValueException("le nouveau nom du restaurant ne doit pas être null.");
        }
        if (nouvelleAdresse == null) {
            throw new InvalidValueException("la nouvelle adresse du restaurant ne doit pas être null.");
        }
        final RestaurantEntity restaurantToUpdate = this.restaurantRepository.findById(id).orElseThrow(() -> new ResourceDoesntExistException("le restaurant d'id " + id + " n'existe pas."));
        restaurantToUpdate.setNom(nouveauNom);
        restaurantToUpdate.setAdresse(nouvelleAdresse);
        restaurantRepository.save(restaurantToUpdate);
        return restaurantToUpdate;
    }


}
