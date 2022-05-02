import java.util.*;

import com.google.common.collect.ArrayListMultimap;
import models.Hero;
import models.Squad;

import com.google.common.collect.Multimap;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.*;

import java.lang.Object;


public class App {

    public static void main(String[] args) {
        staticFileLocation("/public");
        Multimap<String, Hero> squadAllocations = ArrayListMultimap.create();
        //getting homepage
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squadList = Squad.getAllSquads();
            model.put("squads", squadList);
            List<Hero> list = Hero.getAll();
            model.put("heroes", list);
            return modelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //getting heroes form
        get("/hero-form", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return modelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());
        //getting Squad form
        get("/squad-form", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return modelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());
//process squad form
        post("/success-squad", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("squad-name");
            String cause = request.queryParams("squad-cause");
            int maxsize = Integer.parseInt(request.queryParams("maxsize"));
            String motto = request.queryParams("squad-motto");
            Squad squad = new Squad(name, motto, cause, maxsize);
            return modelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
        //individual quad-detail page
        get("/squad/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idToUse = Integer.parseInt(request.params("id"));
            Squad squad = Squad.getSquadById(idToUse);
            String squadname = squad.getName();
            model.put("squad", squad);
            Collection<Hero> heroesInParticularSquad = squadAllocations.get(squadname);
            List<Hero> heroes = new ArrayList<>(heroesInParticularSquad);
            model.put("heroes", heroes);
            return modelAndView(model, "squad-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //getting heroes form
        get("/hero-form", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return modelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());
        //processing hero
        post("/success", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("hero-name");
            String weakness = request.queryParams("weakness");
            int age = Integer.parseInt(request.queryParams("age"));
            String power = request.queryParams("power");
            Hero hero = new Hero(name, age, power, weakness);
            return modelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
//allocate hero a squad form
        get("/squad-allocate", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squadList = Squad.getAllSquads();
            model.put("squads", squadList);
            List<Hero> list = Hero.getAll();
            model.put("heroes", list);
            return new ModelAndView(model, "allocate-squad.hbs");
        }, new HandlebarsTemplateEngine());

        post("/allocate-squad", (request, response) -> {
            int idToUse = Integer.parseInt(request.queryParams("squad-selection"));
            String squadAllocated = Squad.getSquadById(idToUse).getName();
            int selectedHeroId = Integer.parseInt(request.queryParams("hero-selection"));

            Map<String, Object> model = new HashMap<>();

            Squad squad = Squad.getSquadById(idToUse);
            String squadname = squad.getName();
            Collection<Hero> heroesInParticularSquad = squadAllocations.get(squadname);
            List<Hero> heroes = new ArrayList<>(heroesInParticularSquad);

            if (heroes.size() == squad.getMaxsize()) {

                model.put("squad", squad);
                return modelAndView(model, "squad-full.hbs");
            } else {
                Hero.getHeroById(selectedHeroId).setSquad(squadAllocated);
                squadAllocations.put(squadAllocated, Hero.getHeroById(selectedHeroId));
                return modelAndView(squadAllocations, "success.hbs");
            }

        }, new HandlebarsTemplateEngine());


        get("/display-squads", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squadList = Squad.getAllSquads();
            model.put("squads", squadList);
            List<Hero> list = Hero.getAll();
            model.put("heroes", list);
            return modelAndView(model, "display-squads.hbs");
        }, new HandlebarsTemplateEngine());

        get("/display-heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squadList = Squad.getAllSquads();
            model.put("squads", squadList);
            List<Hero> list = Hero.getAll();
            model.put("heroes", list);
            return modelAndView(model, "display-heroes.hbs");
        }, new HandlebarsTemplateEngine());


    }
}
