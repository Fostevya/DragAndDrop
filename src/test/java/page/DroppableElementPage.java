package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DroppableElementPage {


    private WebElement draggable;
    private WebElement droppable;
    private WebDriverWait wait;
    private Actions builder;

    public DroppableElementPage(WebDriver driver) {
        builder = new Actions(driver);
        wait = new WebDriverWait(driver, 25);
        driver.switchTo().frame(0);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("draggable"))));
        draggable = driver.findElement(By.id("draggable"));
        droppable = driver.findElement(By.id("droppable"));
    }

    public void dragAndDropDraggableToDroppable(WebDriver driver) {
        builder.dragAndDrop(draggable, droppable).build().perform();
    }

    public String getDroppableElementText() {
        return droppable.getText();
    }
}
