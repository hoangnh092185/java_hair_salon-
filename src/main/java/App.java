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

    // get("/service", (request,response) ->{
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   model.put("template","templates/service.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

    // get("/stylist", (request, response)->{
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   model.put("stylists", Stylist.all());
    //   model.put("clients", Client.all());
    //   model.put("template","templates/stylists.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

    get("/stylist", (request, response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template","templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist/new", (request, response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist newStylist = new Stylist(request.queryParams("newStylist"), request.queryParams("description"), request.queryParams("experience"));
      newStylist.save();
      model.put("stylist", newStylist);
      model.put("stylists", Stylist.all());
      model.put("template","templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylist/:id/update", (request, response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", stylist);

      model.put("template","templates/stylist-update.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist/:id/update-success", (request, response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
      String newDescription = request.queryParams("description");
      String newExperience = request.queryParams("experience");
      stylist.updateDescription(newDescription);
      stylist.updateExperience(newExperience);
      model.put("stylists", Stylist.all());
      model.put("template","templates/stylist.vtl");
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
      model.put("stylist", stylist);
      Client clientName = new Client(request.queryParams("clientName"), stylist.getId());
      // Client clientDescription = new Client(request.queryParams("clientDescription"), stylist.getId());
      clientName.save();
      // clientDescription.save();
      model.put("clients", stylist.getClients());
      model.put("template","templates/client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist/:stylist_id/client/:client_id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Client client = Client.find(Integer.parseInt(request.params(":client_id")));
      model.put("client", client);
      client.delete();
      model.put("template","templates/delete-client-success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // get("/client/:id/client", (request, response)->{
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   Client client = Client.find(Integer.parseInt(request.params(":id")));
    //   model.put("client", client);
    //   model.put("template","templates/client-update.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

    // post("/client/:id/update", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   Client client = Client.find(Integer.parseInt(request.params(":id")));
    //   String newDescription = request.queryParams("description");
    //   client.updateDescription(newDescription);
    //   model.put("clients", Client.all());
    //   model.put("template","templates/client.vtl");
    // }, new VelocityTemplateEngine());


  }
}
