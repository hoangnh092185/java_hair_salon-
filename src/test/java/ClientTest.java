import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest{

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void equals_returnsTrueIfNameAreTheSame(){
    Client newClient1 = new Client("Batman", 1);
    Client newClient2 = new Client("Batman", 1);
    assertTrue(newClient1.equals(newClient2));
  }

  @Test
  public void save_returnsSaveClientAreTheSame(){
    Client newClient1 = new Client("Batman", 1);
    newClient1.save();
    assertTrue(Client.all().get(0).equals(newClient1));
  }

}
