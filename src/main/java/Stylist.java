import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Stylist {
  private String name;
  private int id;

  public Stylist(String _name){
    name = _name;
  }

  public String getName(){
    return name;
  }

  public int getId(){
    return id;
  }

  public static List<Stylist> all() {
    try(Connection con = DB.sql2o.open()){
      String sql = "SELECT id, name FROM stylists";
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
      String sql = "SELECT id, name FROM stylists where id=:id";
      Stylist stylist = con.createQuery(sql)
        .addParameter("id", _id)
        .executeAndFetchFirst(Stylist.class);
        return stylist;
      }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()){
      String sql = "INSERT INTO stylists(name, description, experience) VALUES(:name, '0', '0')";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
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
