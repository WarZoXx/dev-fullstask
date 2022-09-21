package service;

import fr.polytech.fsback.entity.RestaurantEntity;
import fr.polytech.fsback.exception.InvalidValueException;
import fr.polytech.fsback.exception.ResourceDoesntExistException;
import fr.polytech.fsback.repository.RestaurantRepository;
import fr.polytech.fsback.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    @InjectMocks
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Test()
    public void shouldThrowAInvalidValueException() {
        assertThrows(InvalidValueException.class, () -> this.restaurantService.updateRestaurant(1, null,null));
    }

    @Test()
    public void shouldThrownAResourceDoesntExist() {
        when(restaurantRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(ResourceDoesntExistException.class, () -> this.restaurantService.updateRestaurant(1, "nouveau nom","nouvelle adresse"));
    }

    @Test()
    public void shouldReturnARestaurantEntity() {
        RestaurantEntity expected = RestaurantEntity.builder().id(3).build();
        when(restaurantRepository.findById(any())).thenReturn(Optional.of(expected));
        RestaurantEntity result = this.restaurantService.getRestaurantById(3);
        assertEquals(expected, result);
    }

}
