import java.util.*;

import com.google.common.collect.ArrayListMultimap;
import models.Hero;
import models.Squad;

import com.google.common.collect.Multimap;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static models.Squad.squadAllocations;
import static spark.Spark.*;

import java.lang.Object;


public class App {

    public static void main(String[] args) {
        staticFileLocation("/public");
        //class wide objects
        Hero sample1 = new Hero("Black Bolt",39,"Channeling all available energy into one devastating punch called his Master Blow","Low Self-Control)","/images/superhero (7).png","Black Bolt was born to two of Attilan's top geneticists, Agon, head of the ruling Council of Genetics, and Rynda, director of the Prenatal Care Center. Subjected to the mutagenic Terrigen Mist while still an embryo,[16] Bolt was born with strange powers surpassing even the Inhumans' norm. As an infant, he demonstrated certain energy-manipulative abilities which he could not yet control, particularly that of producing quasi-sonic energy of great destructive potential through his voice. To protect the community, he was placed inside a soundproof chamber and given an energy-harnessing suit.[16] There he was schooled in the art of controlling his powers until the age of nineteen, when he was permitted to enter society.");
        Hero sample2 = new Hero("Ironman",45,"Has a super suit and is a Bilionaire","Vulnerable Without Suit","/images/batman.png","Tony Stark is a genius inventor and billionaire industrialist, who suits up in his armor of cutting-edge technology to become the super hero Iron Man. The adopted son of weapons manufacturer Howard Stark,[48] Tony inherited his family's company at a young age following his parents' death.[49] While overseeing a manufacturing plant in a foreign country, Stark was kidnapped by local terrorists. Instead of giving in to his captors' demands to build weapons for them, Stark created a powerful suit of armor for himself to escape.[50] Returning to America, Stark further upgraded the armor and put his vast resources and intellect to use for the betterment of the world as Iron Man.");
        Hero sample3 = new Hero("Captain America",38,"Super Soldier with Super Human abilities","Hydra Agents","/images/superhero.png","Cpt. Steven \"Steve\" Rogers[1] aka Captain America,[39] the First Avenger,[27] was rejected by the U.S. Army during World War II due to numerous health problems. He ultimately volunteered for Project Rebirth where he received the Super Soldier Serum developed by Dr. Abraham Erskine. The serum greatly enhanced his frail body to the peak of human physicality.[40] As the Sentinel of Liberty,[7] alongside his best friend Bucky Barnes[39] and the Invaders,[41] Rogers and others helped the allies win the war, but not before he was lost at sea and frozen in ice in a form of suspended animation for decades. He was thawed out in the modern age to continue the battle alongside his new allies the Avengers.[42] Cap is loyal to no politician or government and instead upholds the \"timeless principles of freedom, equality and justice of the American Dream\"");
        List<Hero> samplesToDisplay = new ArrayList<>();
        samplesToDisplay.add(sample1);
        samplesToDisplay.add(sample2);
        samplesToDisplay.add(sample3);
        Squad sampleSquad = new Squad("Marvel Heroes","One for all","Maintain galactic peace",6);
        //getting homepage
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squadList = Squad.getAllSquads();
            model.put("squads", squadList);
            model.put("heroes", samplesToDisplay);
            return modelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //deleting all squads
     get("squad/delete",(request, response) -> {
         Map<String, Object> model = new HashMap<>();
         boolean inside = true;
         model.put("position",inside);
         Squad.deleteAllSquads();
         return modelAndView(model,"display-squads.hbs");
     },new HandlebarsTemplateEngine());
//deleting all heros
        get("hero/delete",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            boolean inside = true;
            List<Squad> squadList = Squad.getAllSquads();
            model.put("squads", squadList);
            model.put("position",inside);
            Hero.clearAllHeroes();
            return modelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());


//delete hero
        get("hero-delete/:id",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
          int idToUse = Integer.parseInt(request.params(":id"));
           Hero.getHeroById(idToUse).deleteParicularHero(idToUse);
            List<Squad> squadList = Squad.getAllSquads();
            model.put("squads", squadList);
            boolean inside = true;
            model.put("position",inside);
            List<Hero> list = Hero.getAll();
            model.put("heroes", list);
            return new ModelAndView(model,"display-heroes.hbs");
        },new HandlebarsTemplateEngine());
//delete squad one
        get("squad-delete/:id",(request, response) -> {
            Map<String,Object> model = new HashMap<>();
            int idToUse = Integer.parseInt(request.params(":id"));
            Squad.getSquadById(idToUse).deleteParicularSquad(idToUse);
            List<Squad> squadList = Squad.getAllSquads();
            model.put("squads", squadList);
            boolean inside = true;
            model.put("position",inside);
            List<Hero> list = Hero.getAll();
            model.put("heroes", list);
            return new ModelAndView(model,"display-heroes.hbs");
        },new HandlebarsTemplateEngine());



        //getting heroes form
        get("/hero-form", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squadList = Squad.getAllSquads();
            model.put("squads", squadList);
            String[] icons = Hero.icons;
            model.put("icons",icons);
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
            List<Squad> squadList = Squad.getAllSquads();
            model.put("squads", squadList);
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
          //  boolean inside = true;
            List<Squad> squadList = Squad.getAllSquads();
            model.put("squads", squadList);
           // model.put("position",inside);
            Collection<Hero> heroesInParticularSquad = squadAllocations.get(squadName);
            List<Hero> heroes = new ArrayList<>(heroesInParticularSquad);
            int heroesPresent = squad.getHeroesPresentCount()*100;
            int maxsize = squad.getMaxsize();
            int percentage = (heroesPresent/maxsize);
            squad.setPercentageFull(percentage);
//
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
        //processing hero form
        post("/success", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("hero-name");
            String weakness = request.queryParams("weakness");
            int age = Integer.parseInt(request.queryParams("age"));
            String power = request.queryParams("power");
            String iconChoice = request.queryParams("icon-input");
String bio = request.queryParams("bio");
            List<Squad> squadList = Squad.getAllSquads();
            model.put("squads", squadList);
            Hero hero = new Hero(name, age, power, weakness,iconChoice,bio);
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
//process squad allocation logic to compute so that there is no repeated allocation and to show progressbar info
        post("/allocate-squad", (request, response) -> {
            int idToUse = Integer.parseInt(request.queryParams("squad-selection"));
            String squadAllocated = Squad.getSquadById(idToUse).getName();
            int selectedHeroId = Integer.parseInt(request.queryParams("hero-selection"));
            Map<String, Object> model = new HashMap<>();
            Squad squad = Squad.getSquadById(idToUse);
            String squadname = squad.getName();
            Collection<Hero> heroesInParticularSquad = squadAllocations.get(squadname);
            List<Hero> heroes = new ArrayList<>(heroesInParticularSquad);
            Squad.getSquadById(idToUse).setHeroesPresentCount(heroes.size());
            //adding percentage to squad squad.setPercentageFull(((squad.getHeroesPresentCount()/squad.getMaxsize())*100));
            List<Squad> squadList = Squad.getAllSquads();
            model.put("squads", squadList);

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
                squad.setHeroesPresentCount();
//                Collection<Hero> heroesInParticularSquad1 = squadAllocations.get(squadAllocated);
//                List<Hero> heroes1 = new ArrayList<>(heroesInParticularSquad1);
                int heroesPresent = squad.getHeroesPresentCount()*100;
                int maxsize = squad.getMaxsize();
                int percentage = (heroesPresent/maxsize);
                squad.setPercentageFull(percentage);
                squad.getAllocatedHeroes().add(Hero.getHeroById(selectedHeroId));
                return modelAndView(squadAllocations, "success.hbs");

            }

        }, new HandlebarsTemplateEngine());







        //displaying all squads
        get("/display-squads", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Squad> squadList = Squad.getAllSquads();
            model.put("squads", squadList);
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
