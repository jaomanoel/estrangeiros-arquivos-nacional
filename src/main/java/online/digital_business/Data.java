package online.digital_business;

import java.util.ArrayList;
import java.util.List;

public class Data {

  private List<Person> datas = new ArrayList<Person>();

  public List<Person> getDatas() {
    return datas;
  }

  public void setData(Person person) {
    datas.add(person);
  }
}
