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
        //class wide objects
        Multimap<String, Hero> squadAllocations = ArrayListMultimap.create();
        //sample heroes
        Hero sample1 = new Hero("Black Bolt",39,"Channeling all available energy into one devastating punch called his Master Blow","Low Self-Control)");
        Hero sample2 = new Hero("Ironman",45,"Has a super suit and is a Bilionaire","Vulnerable Without Suit");
        Hero sample3 = new Hero("Captain America",38,"Super Soldier with Super Human abilities","Hydra Agents");
        Squad sampleSquad = new Squad("Marvel Heroes","One for all","Maintain galactic peace",6);
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
            List<Squad> squadList = Squad.getAllSquads();
            model.put("squads", squadList);
            return modelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());
        //getting Squad form
        get("/squad-form", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squadList = Squad.getAllSquads();
            model.put("squads", squadList);
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
        //individual squad-detail page
        get("/squad/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idToUse = Integer.parseInt(request.params("id"));
            Squad squad = Squad.getSquadById(idToUse);
            String squadName = squad.getName();
            model.put("squad", squad);
            boolean inside = true;
            model.put("position",inside);
            Collection<Hero> heroesInParticularSquad = squadAllocations.get(squadName);
            List<Hero> heroes = new ArrayList<>(heroesInParticularSquad);
            model.put("heroes", heroes);
            return modelAndView(model, "squad-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //getting heroes form
        get("/hero-form", (request, response) -> {

            Map<String, Object> model = new HashMap<>();
            List<Squad> squadList = Squad.getAllSquads();
            model.put("squads", squadList);
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
            for(Squad squad: squadList){
                String squadname = squad.getName();
                Collection<Hero> heroesInParticularSquad = squadAllocations.get(squadname);
                List<Hero> heroes = new ArrayList<>(heroesInParticularSquad);
                if (heroes.size() == squad.getMaxsize()) {
                    squad.setSquadFull(true);}
            }
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
               squad.setSquadFull(true);
                model.put("squad", squad);
                return modelAndView(model, "squad-full.hbs");
            } else if(heroes.contains(Hero.getHeroById(selectedHeroId))){
                model.put("hero",Hero.getHeroById(selectedHeroId));
                return modelAndView(model,"already-allocated.hbs");
            }


            else {
                Hero.getHeroById(selectedHeroId).setSquad(squadAllocated);
                squadAllocations.put(squadAllocated, Hero.getHeroById(selectedHeroId));
                return modelAndView(squadAllocations, "success.hbs");
            }

        }, new HandlebarsTemplateEngine());







        //displaying all squads
        get("/display-squads", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squadList = Squad.getAllSquads();
            model.put("squads", squadList);
            List<Hero> list = Hero.getAll();
            model.put("heroes", list);
            return modelAndView(model, "display-squads.hbs");
        }, new HandlebarsTemplateEngine());

// displaying all heroes
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
