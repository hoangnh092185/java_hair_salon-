import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Client {
  private String name;
  private int id;
  private int stylistId;


  public Client(String _name, int _stylistId){
    name = _name;
    stylistId = _stylistId;
  }

  public String getName(){
    return name;
  }

  public int getId() {
    return id;
  }

  public int getStylistId() {
    return stylistId;
  }

  public static List<Client> all() {
  String sql = "SELECT id, name, stylistId FROM clients";
  try(Connection con = DB.sql2o.open()) {
    return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public void save() {
    try(Connection con= DB.sql2o.open()){
      String sql = "INSERT INTO clients(name, stylistId) VALUES (:name, :stylistId)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("stylistId", this.stylistId)
        .executeUpdate()
        .getKey();
    }
  }

  public static Client find(int _id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE id=:id";
      Client client = con.createQuery(sql)
        .addParameter("id", _id)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }

  public void delete(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void updateDescription() {
    try(Connection con= DB.sql2o.open()){
      String sql = "UPDATE clients SET description=:description WHERE id=:id ";
      con.createQuery(sql)
        .addParameter("description", this.description)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void updateStars() {
    try(Connection con= DB.sql2o.open()){
      String sql = "UPDATE clients SET experience=:experience WHERE id=:id ";
      con.createQuery(sql)
        .addParameter("experience", this.experience)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }


  @Override
  public boolean equals(Object otherClient){
    if(!(otherClient instanceof Client)){
      return false;
    }else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName()) &&
              this.getStylistId() == newClient.getStylistId();
    }
  }


}
