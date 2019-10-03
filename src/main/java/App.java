import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;;

public class App {
    public static void main(String[] args) {

        staticFileLocation("/public");
        get("/add", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "newpost-form.hbs");
        }, new HandlebarsTemplateEngine());


        post("/add", (request, response) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String email = request.queryParams("email");
            String link =  request.queryParams("link");
            System.out.println(link);
            String location = request.queryParams("location");
            String purpose = request.queryParams("purpose");
            String meansOfPayment = request.queryParams("meansOfPayment");
           String price = request.queryParams("price");
           Buy newBuy = new Buy(name, email, link, location,purpose,meansOfPayment,price);
            model.put("newBuy", newBuy);
            newBuy.save();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
           List<Buy> posts = Buy.all();
            model.put("posts", posts);

            return new ModelAndView(model, "list.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
