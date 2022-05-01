import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Hero;
import models.Squad;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;


public class App {

    public static void main(String[] args) {
        staticFileLocation("/public");
        //getting homepage
        get("/",(request, response) ->{
            Map<String,Object> model = new HashMap<>();
            List<Hero> list = Hero.getAll();
            model.put("heroes",list);
            return modelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        //getting heroes form
        get("/hero-form",(request, response) ->{
            Map<String,Object> model = new HashMap<>();
            return modelAndView(model,"hero-form.hbs");
        },new HandlebarsTemplateEngine());
        //getting Squad form
        get("/squad-form",(request, response) ->{
            Map<String,Object> model = new HashMap<>();
            return modelAndView(model,"squad-form.hbs");
        },new HandlebarsTemplateEngine());
//process squad form
        post("/success-squad",(request, response) ->{
            Map<String,Object> model = new HashMap<>();
            String name = request.queryParams("squad-name");
            String cause  =request.queryParams("cause");
            int  maxsize = Integer.parseInt(request.queryParams("max-size"));
            String motto = request.queryParams("motto");
            Squad squad = new Squad(name,motto,cause,maxsize);
            return modelAndView(model,"success.hbs");
        },new HandlebarsTemplateEngine());

 //getting heroes form
        get("/hero-form",(request, response) ->{
            Map<String,Object> model = new HashMap<>();
            return modelAndView(model,"hero-form.hbs");
        },new HandlebarsTemplateEngine());
        //processing hero
        post("/success",(request, response) ->{
            Map<String,Object> model = new HashMap<>();
            String name = request.queryParams("hero-name");
            String weakness =request.queryParams("weakness");
            int  age = Integer.parseInt(request.queryParams("age"));
            String power = request.queryParams("power");
            Hero hero = new Hero(name,age,power,weakness);
            return modelAndView(model,"success.hbs");
        },new HandlebarsTemplateEngine());


    }
}
