package Test_Falabella;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class  ATC_Paquetes {
    private WebDriver driver;

    @BeforeClass
    public static void setup() {
        System.out.println("Setup necesario antes de Instanciar");
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void init() {
        System.out.println("instanciar");
        driver = new ChromeDriver();
        //Page practice
        driver.get("https://www.viajesfalabella.cl/paquetes/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void TC003_Vuelo_con_2_Alojamientos() throws InterruptedException{

        /* 1. Seleccionar paquete de Vuelo y Dos Alojamientos ok
        2. Ingresar Origen ok
        3. Ingresar Destino internacional ok
        4. Seleccionar fecha de ida ok
        5. Seleccionar fecha de vuelta ok
        6. Añadir primer destino ok
        7. Ingresar fecha límite del primer destino ok
        8. Añadir segundo destino ok
        9. Ingresar fecha límite del segundo destino ok
        10. Añadir cantidad de habitaciones y adultos
        11. Hacer click en el botón buscar */

        //wait until element is visible - click en traslados
        WebDriverWait driverWithMoreWait = new WebDriverWait(driver, 20);

        // paquete vuelo 2 alojamientos //*[contains(@class, 'sbox-radio-vhh')]
        driver.findElement(By.xpath("//*[contains(@class, 'sbox-radio-vhh')]")).click();
        //Thread.sleep(2000);

        String txtOrigenPaquete = "Santiago de Chile, Santiago, Chile";
        String txtDestinoPaquete = "Rio de Janeiro, Rio de Janeiro, Brasil";
        String txtSegundoDestino = "San Pablo, San Pablo, Brasil";

        WebElement origenPaquete = driver.findElement(By.xpath("//html/body/app-root/div/header-wrapper/div/div/sbox/div/searchbox/div/div/div/div/div[3]/div[2]/div[2]/div/div/div/div/input"));
        origenPaquete.sendKeys(txtOrigenPaquete);
        Thread.sleep(500);
        origenPaquete.sendKeys(Keys.DOWN);
        origenPaquete.sendKeys(Keys.ENTER);



        WebElement destinoPaquete = driver.findElement(By.xpath("//html/body/app-root/div/header-wrapper/div/div/sbox/div/searchbox/div/div/div/div/div[3]/div[2]/div[2]/div[2]/div/div/div/div/input"));
        destinoPaquete.sendKeys(txtDestinoPaquete);
        Thread.sleep(500);
        destinoPaquete.sendKeys(Keys.DOWN);
        destinoPaquete.sendKeys(Keys.ENTER);


        // clic caja fecha ida //*[contains(@class, 'sbox-checkin-date')]
        driver.findElement(By.xpath("//*[contains(@class, 'sbox-checkin-date')]")).click();

        // fecha ida 7 enero  //html/body/div[5]/div/div[5]/div/div[4]/span[7]/span
        driver.findElement(By.xpath("//html/body/div[5]/div/div[5]/div/div[4]/span[7]/span")).click();

        // clic caja fecha retorno //*[contains(@class, 'sbox-checkout-date')]
        driver.findElement(By.xpath("//*[contains(@class, 'sbox-checkout-date')]")).click();

        // fecha retorno 13 enero //html/body/div[5]/div/div[5]/div/div[4]/span[13]/span
        driver.findElement(By.xpath("//html/body/div[5]/div/div[5]/div/div[4]/span[13]/span")).click();

        // boton aplicar fechas  //html/body/div[7]/div/div[6]/div[2]/button[2]/em
        driver.findElement(By.xpath("//html/body/div[7]/div/div[6]/div[2]/button[2]/em")).click();

        // fecha hasta primer destino //*[contains(@class, 'sbox-hotel-first-checkout-date')]
        driver.findElement(By.xpath("//*[contains(@class, 'sbox-hotel-first-checkout-date')]")).click();

        // setear fecha 11 de enero //*[contains(@class, '_dpmg2--show')]//*[contains(@class, '_dpmg2--has-start-range')]/div[4]/span[11]/span
        driver.findElement(By.xpath("//*[contains(@class, '_dpmg2--show')]//*[contains(@class, '_dpmg2--has-start-range')]/div[4]/span[11]/span")).click();

        //boton aplicar segunda fecha //*[contains(@class, '_dpmg2--show')]//em[text()='Aplicar']
        driver.findElement(By.xpath("//*[contains(@class, '_dpmg2--show')]//em[text()='Aplicar']")).click();

        //segundo destino  //html/body/app-root/div/header-wrapper/div/div/sbox/div/searchbox/div/div/div/div/div[3]/div[2]/div[7]/div[2]/div[2]/div/div/div/div/div/input
        WebElement segundoDestinoPaquete = driver.findElement(By.xpath("//html/body/app-root/div/header-wrapper/div/div/sbox/div/searchbox/div/div/div/div/div[3]/div[2]/div[7]/div[2]/div[2]/div/div/div/div/div/input"));
        segundoDestinoPaquete.sendKeys(txtSegundoDestino);
        Thread.sleep(500);
        segundoDestinoPaquete.sendKeys(Keys.DOWN);
        segundoDestinoPaquete.sendKeys(Keys.ENTER);


        // boton buscar
        driver.findElement(By.xpath("//*[contains(@class, 'sbox-search')]")).click();


        String textValidate = driverWithMoreWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='trips-cluster-selected-position']//*[contains(@class, 'itineraries-container')]/span/route-choice/div/span[2]/span[2]/route-info-item[2]/span/span/span"))).getText();
        //Validacion
        Assert.assertEquals("Rio de Janeiro", textValidate);




    }

    @After
    public void close () {
        if (driver != null) {
            System.out.println("Close");
            driver.close();
        }

    }

    @AfterClass
    public static void closeAll() {
        System.out.println("closeAll :: Cerrar otras conexiones que fueron utilizadas en el test");
    }
}

