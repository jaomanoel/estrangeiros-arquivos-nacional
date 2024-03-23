package online.digital_business;

public class Query {
  private String name;
  private String country;

  public String getName() {
    return name;
  }

  public String getCountry() {
    return country;
  }

  public Query() {
  }

  public Query(String name, String country) {
    this.name = name;
    this.country = country;
  }
}
