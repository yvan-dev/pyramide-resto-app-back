package com.restaurant.restaurant.controller;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import com.restaurant.restaurant.dao.FoodDao;
import com.restaurant.restaurant.dao.ImageFoodDao;
import com.restaurant.restaurant.interfac.ComposeFoodInterface;
import com.restaurant.restaurant.model.Food;
import com.restaurant.restaurant.model.ImageFood;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
@RequestMapping("/composeFood")
public class ComposeFood implements ComposeFoodInterface {
    @Autowired
    FoodDao foodDao;

    @Autowired
    ImageFoodDao imageFoodDao;

    String apiHost = "yummly2.p.rapidapi.com";
    String apiKey = "537bbcf41emsh9ee94a912a95002p10feb8jsn8037c444c970";

    @Override
    public ResponseEntity<String> composeFoodOfDay() {
        LocalDate date = LocalDate.now();
        return composeFoodOfDay(date.getDayOfWeek().toString());
    }

    public ResponseEntity<String> composeFoodOfDay(String day) {
        LocalDate date = LocalDate.now().with(DayOfWeek.valueOf(day.toUpperCase())); // Date correspondant au jour de la semaine actuelle
        int limitFood = date.getDayOfYear() / (date.getDayOfWeek().getValue() * date.getDayOfMonth()); // Calcul des plats du jour en fonction des paramètres du jour {day}
        if (limitFood == 0)
            limitFood = 1;
        limitFood *= 5; // Maximum 5 plats par jour
        String url = "https://yummly2.p.rapidapi.com/feeds/list?limit=" + limitFood + "&start=" + (limitFood - 5) +"&tag=list.recipe.popular";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", apiHost)
                .addHeader("x-rapidapi-key", apiKey)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.code() != 200)
                throw new Exception();
            JSONArray responseArray = new JSONObject(response.body().string()).getJSONArray("feed");
            for (int i = 0; i < 5; i++) { // responseArray.length()
                JSONObject object = responseArray.getJSONObject(i);
                Food food = new Food();
                food.setName(object.getJSONObject("display").getString("displayName"));
                if (foodDao.findByNameAndDate(food.getName(), date) != null)
                    continue;
                // return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Le plat " +
                // food.getName() + " existe déjà");
                if (object.getJSONObject("content").getJSONObject("tags").has("cuisine"))
                    food.setType(object.getJSONObject("content").getJSONObject("tags").getJSONArray("cuisine")
                            .getJSONObject(0).getString("display-name").concat(" dish"));
                if (!object.getJSONObject("content").isNull("description"))
                    food.setDescription(object.getJSONObject("content").getJSONObject("description").getString("text"));
                JSONArray ingredientsArray = object.getJSONObject("content").getJSONArray("ingredientLines");
                String ingredients = "";
                for (int j = 0; j < ingredientsArray.length(); j++)
                    ingredients += ingredientsArray.getJSONObject(j).getString("ingredient").concat(", ");
                food.setIngredients(ingredients);
                food.setDate(date);
                food.setDay(date.getDayOfWeek().toString().toUpperCase());
                foodDao.save(food);
                /* Images des plats */
                int idSavedFood = foodDao.findByNameAndDate(food.getName(), date).getId();
                JSONArray images = object.getJSONObject("display").getJSONArray("images");
                for (int j = 0; j < images.length(); j++) {
                    if (imageFoodDao.findByImageUrl(images.getString(0)) != null)
                        continue;
                    ImageFood imageFood = new ImageFood();
                    imageFood.setImageUrl(images.getString(0));
                    imageFood.setIdFood(idSavedFood);
                    imageFood.setName(object.getJSONObject("display").getString("displayName"));
                    imageFoodDao.save(imageFood);
                }
                // Image supplémentaire
                if (object.getJSONObject("content").has("videos") && imageFoodDao.findByImageUrl(
                        object.getJSONObject("content")
                                .getJSONObject("videos").getString("snapshotUrl")) != null) {
                    ImageFood imageFood = new ImageFood();
                    imageFood.setIdFood(idSavedFood);
                    imageFood.setName(object.getJSONObject("display").getString("displayName"));
                    imageFood.setImageUrl(object.getJSONObject("content")
                            .getJSONObject("videos").getString("snapshotUrl"));
                    imageFoodDao.save(imageFood);
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body("Plats du jour ajoutés avec succès");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<String> composeFoodOfWeek() {
        for (DayOfWeek day : DayOfWeek.values()) {
            composeFoodOfDay(day.toString());
        }
        return ResponseEntity.ok().body("Plats de la semaine ajoutés avec succès");
    }
}