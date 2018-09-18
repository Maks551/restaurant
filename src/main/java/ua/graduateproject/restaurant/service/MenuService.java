package ua.graduateproject.restaurant.service;

import ua.graduateproject.restaurant.model.Menu;
import ua.graduateproject.restaurant.util.exception.NotFoundException;

import java.util.List;

public interface MenuService {

    Menu get(int id, int restaurantId) throws NotFoundException;

    List<Menu> getAll (int userId) throws NotFoundException;


}
