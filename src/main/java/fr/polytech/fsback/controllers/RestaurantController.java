package fr.polytech.fsback.controllers;

import fr.polytech.fsback.dto.RestaurantDto;
import fr.polytech.fsback.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController()
//@RequiredArgsConstructor
@Slf4j
public class RestaurantController {
    public final RestaurantService RestaurantService; //final : indiq pour dire que ça change pas plus tard (constante et pas variable)

    public RestaurantController(RestaurantService rService) {
        this.RestaurantService = rService;
    }

    // Appel la méthode de la couche service qui renvoi les informations d'un restaurant en fonction d'un id
    @GetMapping("/restaurants/{id}")
    public @ResponseBody RestaurantDto getRestaurantById(@PathVariable int id) {
        return RestaurantDto.fromEntity(this.RestaurantService.getRestaurantById(id));
    }

    // Appel la méthode de la couche service qui renvoi tous les restaurants
    @GetMapping("/restaurants")
    public @ResponseBody List<RestaurantDto> getRestaurants() {
        return this.RestaurantService.getAllRestaurants().stream().map(entity -> RestaurantDto.fromEntity(entity)).collect(Collectors.toList());
    }

    // Appel la méthode de la couche service qui créé un restaurant
    @PostMapping("/restaurants")
    public RestaurantDto postRestaurant(@Valid @RequestBody RestaurantDto restaurant) {
        return RestaurantDto.fromEntity(this.RestaurantService.addRestaurant(restaurant.getNom(),restaurant.getAdresse()));
    }

    // Appel la méthode de la couche service qui update un restaurant
    @PutMapping("/restaurants/{id}")
    public RestaurantDto updateRestaurant(@PathVariable int id,@Valid @RequestBody RestaurantDto restaurant){
        return RestaurantDto.fromEntity(this.RestaurantService.updateRestaurant(id,restaurant.getNom(),restaurant.getAdresse()));
    }
}
