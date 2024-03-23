package online.digital_business;

public class Person {
  private String name;
  private String years;
  private String profession;
  private String country;
  private String entry;
  private String arrivalDate;
  private String procedure;
  private String destiny;

  public String getName() {
    return name;
  }

  public String getYears() {
    return years;
  }

  public String getProfession() {
    return profession;
  }

  public String getCountry() {
    return country;
  }

  public String getEntry() {
    return entry;
  }

  public String getArrivalDate() {
    return arrivalDate;
  }

  public String getProcedure() {
    return procedure;
  }

  public String getDestiny() {
    return destiny;
  }

  public Person(
      String name,
      String years,
      String profession,
      String country,
      String entry,
      String arrivalDate,
      String procedure,
      String destiny) {
    this.name = name;
    this.years = years;
    this.profession = profession;
    this.country = country;
    this.entry = entry;
    this.arrivalDate = arrivalDate;
    this.procedure = procedure;
    this.destiny = destiny;
  }
}
