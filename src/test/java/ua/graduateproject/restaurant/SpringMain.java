package ua.graduateproject.restaurant;

import org.springframework.context.support.GenericXmlApplicationContext;
import ua.graduateproject.restaurant.web.controllers.MealRestController;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resources management
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.getEnvironment().setActiveProfiles(Profiles.getActiveDbProfile(), Profiles.DATAJPA);
            appCtx.load("spring/spring-app.xml", "spring/spring-db.xml");
            appCtx.refresh();

            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

            MealRestController mealController = appCtx.getBean(MealRestController.class);
        }
    }
}
