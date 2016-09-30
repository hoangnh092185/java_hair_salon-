import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class StylistTest{

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void equals_returnsTrueIfNameAreTheSame(){
    Stylist newStylist1 = new Stylist("Nhat", "I love hair", "3");
    Stylist newStylist2 = new Stylist("Nhat", "I love hair", "3");
    assertTrue(newStylist1.equals(newStylist2));
  }

  @Test
  public void save_returnsSaveStylist_true(){
    Stylist newStylist1 = new Stylist("Nhat", "I love hair", "3");
    newStylist1.save();
    assertEquals(newStylist1, Stylist.all().get(0));
  }

  @Test
  public void all_returnsAllinstancesOfPerRobbin_true(){
    Stylist newStylist1 = new Stylist("Nhat", "I love hair", "3");
    newStylist1.save();
    Stylist newStylist2 = new Stylist("Nate", "I realy love hair", "3");
    newStylist2.save();
    assertEquals(true, Stylist.all().get(0).equals(newStylist1));
    assertEquals(true, Stylist.all().get(1).equals(newStylist2));
  }

  @Test
  public void save_assignsIdToObject(){
    Stylist newStylist1 = new Stylist("Nhat", "I love hair", "3");
    newStylist1.save();
    Stylist saveStylist = Stylist.all().get(0);
    assertEquals(newStylist1.getId(), saveStylist.getId());
  }

  @Test
  public void getId_stylistsInstantiateWithId(){
    Stylist newStylist1 = new Stylist("Nhat", "I love hair", "3");
    newStylist1.save();
    assertTrue(newStylist1.getId()>0);
  }

  @Test
  public void find_returnsStylistWithSameId_newStylist2(){
    Stylist newStylist1 = new Stylist("Nhat", "I love hair", "3");
    newStylist1.save();
    Stylist newStylist2 = new Stylist("Nate", "I realy love hair", "3");
    newStylist2.save();
    assertEquals(Stylist.find(newStylist2.getId()), newStylist2);
  }

  @Test
  public void getClients_returnClients_list(){
    Stylist newStylist1 = new Stylist("Nhat", "I love hair", "3");
    newStylist1.save();
    Client newClient1 = new Client("Batman", newStylist1.getId());
    newClient1.save();
    Client newClient2 = new Client("Robbin", newStylist1.getId());
    newClient2.save();
    assertEquals(newStylist1.getClients().get(0), newClient1);
  }

  @Test
  public void update_updateStylistDescription_true(){
    Stylist myStylist = new Stylist("Jone", "I love hair", "3");
    myStylist.save();
    myStylist.updateDescription("I love wigs");
    assertEquals("I love wigs", Stylist.find(myStylist.getId()).getDescription());
  }
}
