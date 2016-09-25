import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request,response) ->{
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template","templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylist", (request, response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template","templates/stylists.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/employment", (request, response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template","templates/employment.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/employment/new", (request, response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist newStylist = new Stylist(request.queryParams("newStylist"), request.queryParams("description"), request.queryParams("experience"));
      newStylist.save();
      model.put("stylist", newStylist);
      model.put("stylists", Stylist.all());
      model.put("template","templates/employment.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist/:id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", stylist);
      stylist.deleteClients();
      stylist.delete();
      model.put("template","templates/delete-stylist-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylist/:id/client", (request, response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", stylist);
      model.put("clients", stylist.getClients());
      model.put("template","templates/client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist/:id/client/new", (request, response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      Client clientName = new Client(request.queryParams("clientName"), stylist.getId());
      // Client clientTime = new Client(request.queryParams("time"), person.getId());
      clientName.save();
      model.put("template","templates/client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // post("/stylist/:stylist_id/client/:client_id", (request, response)->{
    //
    //   model.put("template","templates/appointment.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());


  }
}
