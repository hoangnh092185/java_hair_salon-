import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Stylist {
  private String name;
  private int id;
  private String description;
  private String experience;

  public Stylist(String _name, String _description, String _experience){
    name = _name;
    description = _description;
    experience = _experience;
  }

  public String getName(){
    return name;
  }

  public int getId(){
    return id;
  }
  // public void setDescription(String _description) {
  //   this.description = _description;
  // }
  //
  // public void setExperience(String _experience) {
  //   this.experience = _experience;
  // }

  public String getDescription() {
    return description;
  }

  public String getExperience() {
    return experience;
  }

  public static List<Stylist> all() {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM stylists";
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  public List<Client> getClients(){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM clients where stylistId=:id";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Client.class);
    }
  }

  public static Stylist find (int _id){
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT * FROM stylists where id=:id";
      Stylist stylist = con.createQuery(sql)
        .addParameter("id", _id)
        .executeAndFetchFirst(Stylist.class);
        return stylist;
      }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO stylists(name, description, experience) VALUES(:name,:description, :experience)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("description", this.description)
        .addParameter("experience", this.experience)
        .executeUpdate()
        .getKey();
    }
  }

  public void updateDescription(String _description){
    try(Connection con= DB.sql2o.open()){
        String sql = "UPDATE stylists SET description = :description WHERE id = :id ";
        con.createQuery(sql)
          .addParameter("description", _description)
          .addParameter("id", this.id)
          .executeUpdate();
    }
  }

  public void updateExperience(String _experience){
    try(Connection con= DB.sql2o.open()){
      String sql = "UPDATE Stylists SET experience=:experience WHERE id=:id ";
      con.createQuery(sql)
        .addParameter("experience", _experience)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void delete(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void deleteClients(){
    for(Client client : this.getClients()){
      client.delete();
      }
  }

  @Override
  public boolean equals(Object otherStylist){
    if(!(otherStylist instanceof Stylist)){
      return false;
    }else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName()) &&
        this.getId() == newStylist.getId();
    }
  }
}
