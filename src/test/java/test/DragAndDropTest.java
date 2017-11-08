package test;

import form.LoginForm;
import form.RegistrationForm;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.DroppableElementPage;
import page.ElementsPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class DragAndDropTest {

    private WebDriver driver;
    private String login = "molly";
    private String password = "firstPass";
    private String url = "http://way2automation.com/way2auto_jquery/index.php";
    private RegistrationForm registrationForm;
    private LoginForm loginForm;
    private ElementsPage elementsPage;
    private DroppableElementPage droppableElementPage;
    private DesiredCapabilities capabilities;

    @BeforeTest
    public void beforeTest() {
        capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get(url);
            registrationForm = new RegistrationForm(driver);
            registrationForm.clickSignin();
            loginForm = new LoginForm(driver);
            loginForm.authorizeOnSite(login, password);
            elementsPage = new ElementsPage(driver);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void subMenuIsVisibleTest() {
        elementsPage.menuElementClick();
        droppableElementPage = new DroppableElementPage(driver);
        droppableElementPage.dragAndDropDraggableToDroppable(driver);
        assertEquals(droppableElementPage.getDroppableElementText(), "Dropped!");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
