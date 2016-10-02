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

  @Test
  public void all_returnsAllInstancesOfClient_true(){
    Client newClient1 = new Client("Batman", 1);
    newClient1.save();
    Client newClient2 = new Client("Son", 1);
    newClient2.save();
    assertEquals(true, Client.all().get(0).equals(newClient1));
    assertEquals(true, Client.all().get(1).equals(newClient2));
  }

  @Test
  public void save_assignsIdToObject(){
    Client newClient1 = new Client("Batman", 1);
    newClient1.save();
    Client saveClient = Client.all().get(0);
    assertEquals(newClient1.getId(), saveClient.getId());
  }

  @Test
  public void getId_clientInstantiateWithId(){
    Client newClient1 = new Client("Batman", 1);
    newClient1.save();
    assertTrue(newClient1.getId()>0);
  }

  public void find_returnsClientWithSameId_newClient2(){
    Client newClient1 = new Client("Batman", 1);
    newClient1.save();
    Client newClient2 = new Client("Son", 1);
    newClient2.save();
    assertEquals(Client.find(newClient2.getId()), newClient2);
  }
  public void update_returnUpdatedClientDescription_string(){
    Client newClient1 = new Client("Batman", 1);
    newClient1.save();
    newClient1.updateDescription("Is bald.");
    assertEquals(Client.find(newClient1.getId()).getDescription(), "Is bald.");
  }
}
