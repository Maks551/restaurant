package ua.graduateproject.restaurant;

import org.springframework.context.support.GenericXmlApplicationContext;
import ua.graduateproject.restaurant.web.meal.MealAdminRestController;

import java.util.Arrays;

import static ua.graduateproject.restaurant.RestaurantTestData.RESTAURANT_ID;
import static ua.graduateproject.restaurant.TestUtil.mockAuthorize;
import static ua.graduateproject.restaurant.UserTestData.USER_1;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resources management
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.getEnvironment().setActiveProfiles(Profiles.getActiveDbProfile(), Profiles.DATAJPA);
            appCtx.load("spring/spring-app.xml", "spring/spring-db.xml");
            appCtx.refresh();

            mockAuthorize(USER_1);

            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

            MealAdminRestController mealController = appCtx.getBean(MealAdminRestController.class);
            System.out.println(mealController.getAll(RESTAURANT_ID));
        }
    }
}
