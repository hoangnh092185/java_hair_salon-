import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

public class Movie {
  private String name;
  private int id;
  private int stylistId;
  private String description;
  private String experience;

  public Movie(String _name, int _stylistId){
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

  public void setDescription(String _description) {
    this.description = _description;
  }

  public void setStars(String _experience) {
    this.experience = _experience;
  }

  public String getDescription() {
    return description;
  }

  public String getStars() {
    return experience;
  }


  public static List<Movie> all() {
  String sql = "SELECT id, name, stylistId FROM movies";
  try(Connection con = DB.sql2o.open()) {
    return con.createQuery(sql).executeAndFetch(Movie.class);
    }
  }

  public void save() {
    try(Connection con= DB.sql2o.open()){
      String sql = "INSERT INTO movies(name, stylistId, description, experience) VALUES (:name, :stylistId,'n/a', 'n/a')";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("stylistId", this.stylistId)
        .executeUpdate()
        .getKey();
    }
  }

  public static Movie find(int _id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM movies WHERE id=:id";
      Movie movie = con.createQuery(sql)
        .addParameter("id", _id)
        .executeAndFetchFirst(Movie.class);
      return movie;
    }
  }

  public void delete(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM movies WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void updateDescription() {
    try(Connection con= DB.sql2o.open()){
      String sql = "UPDATE movies SET description=:description WHERE id=:id ";
      con.createQuery(sql)
        .addParameter("description", this.description)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void updateStars() {
    try(Connection con= DB.sql2o.open()){
      String sql = "UPDATE movies SET experience=:experience WHERE id=:id ";
      con.createQuery(sql)
        .addParameter("experience", this.experience)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }


  @Override
  public boolean equals(Object otherMovie){
    if(!(otherMovie instanceof Movie)){
      return false;
    }else {
      Movie newMovie = (Movie) otherMovie;
      return this.getName().equals(newMovie.getName()) &&
              this.getStylistId() == newMovie.getStylistId();
    }
  }


}
