package online.digital_business;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.opencsv.CSVWriter;

public class Main {

  public static void loadPage(WebDriver driver) {
    driver.get("https://bases.an.gov.br/rv/Menu_Externo/");

    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

    driver.findElement(By.id("j1_3")).click();

    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(4000));

    driver.switchTo().frame("iframe_Menu_Externo");
  }

  public static void getData(WebDriver driver, String name, String country) {
    driver.findElement(By.xpath("//*[@id='SC_nome']")).sendKeys(name);

    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

    driver.findElement(By.xpath("//*[@id='SC_idnacao']")).sendKeys(country);

    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

    driver.findElement(By.xpath("//*[@id='sc_b_pesq_bot']")).click();

    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(25000));
  }

  public static void saveData(WebDriver driver, Data data) throws InterruptedException {
    String isEmpty = driver.findElement(By.xpath("//*[@id='sc_grid_body']")).getText();

    if (!isEmpty.equals("Registros não encontrados")) {
      String name = driver.findElement(By.xpath("//*[@id='id_sc_field_nome_1']")).getText();
      String years = driver.findElement(By.xpath("//*[@id='id_sc_field_idadeano_1']")).getText();
      String profession = driver.findElement(By.xpath("//*[@id='id_sc_field_idprofissao_1']")).getText();
      String country = driver.findElement(By.xpath("//*[@id='id_sc_field_idnacao_1']")).getText();
      String entry = driver.findElement(By.xpath("//*[@id='id_sc_field_datachegada_1']")).getText();
      String arrivalDate = driver.findElement(By.xpath("//*[@id='id_sc_field_idporto_1']")).getText();
      String procedure = driver.findElement(By.xpath("//*[@id='id_sc_field_idprocedencia_1']")).getText();
      String destiny = driver.findElement(By.xpath("//*[@id='id_sc_field_iddestino_1']")).getText();

      Person person = new Person(name, years, profession, country, entry, arrivalDate, procedure, destiny);
      data.setData(person);

      String nextButton = driver.findElement(By.id("forward_top")).getAttribute("class");

      if (nextButton.equals("scButton_default")) {
        driver.findElement(By.id("forward_top")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

        Thread.sleep(2000);

        saveData(driver, data);
      } else {
        return;
      }
    }
  }

  public static void saveOnCSV(Data data) {
    File file = new File("data.csv");

    try {
      FileWriter outputfile = new FileWriter(file);
      CSVWriter writer = new CSVWriter(outputfile);

      String[] header = { "Name", "Years", "Profession", "Country", "Entry", "Arrival Date", "Procedure", "Destiny" };
      writer.writeNext(header);

      data.getDatas().forEach(person -> {
        writer.writeNext(new String[] {
            person.getName(),
            person.getYears(),
            person.getProfession(),
            person.getCountry(),
            person.getEntry(),
            person.getArrivalDate(),
            person.getProcedure(),
            person.getDestiny()
        });
      });

      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    try {
      ChromeOptions options = new ChromeOptions();
      options.setCapability("platformName", Platform.LINUX);

      WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
      Data data = new Data();

      List<Query> queries = new ArrayList<Query>();
      queries.add(new Query("Nome", "NACAO/NACIONALIDADE"));
      queries.add(new Query("OUTRO NOME", "NACAO/NACIONALIDADE"));

      queries.forEach(query -> {
        loadPage(driver);
        getData(driver, query.getName(), query.getCountry());
        try {
          saveData(driver, data);
        } catch (InterruptedException e) {
          driver.quit();
        }
      });

      saveOnCSV(data);
      driver.quit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
