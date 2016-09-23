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
      model.put("stlylists", Stylist.all());
      model.put("template","templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist/new", (request, response)->{
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      Stylist newStylist = new Stylist(request.queryParams("newStylist"));
      newStylist.save();
      model.put("stylist", newStylist);
      model.put("template","templates/employment.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    // post("/stylist/:id/client/new", (request, response)->{
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
    //   Client clientName = new Client(request.queryParams("name"), person.getId());
    //   Client clientTime = new Client(request.queryParams("time"), person.getId());
    //
    //   model.put("template","templates/employment.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

    // post("/stylist/:stylist_id/client/:client_id", (request, response)->{
    //
    //   model.put("template","templates/appointment.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());


  }
}
