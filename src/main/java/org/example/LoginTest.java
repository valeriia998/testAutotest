package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.activeCount;
import static java.lang.Thread.sleep;
public class LoginTest {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/main/webdriver/chromedriver.exe");
        // Открыть браузер
        ChromeDriver driver = new ChromeDriver();
        // Развернуть браузер на весь экран
        driver.manage().window().maximize();
        // Открыть сайт Яндекс Маркет
        driver.get("https://market.yandex.ru/");
        sleep(2000);
        // Нажать на кнопку "Каталог"
        driver.findElement(By.xpath("//span[text() = 'Каталог']")).click();
        sleep(3000);
        // Нажать на кнопку "Смартфоны"
        driver.findElement(By.xpath("//a[text() = 'Смартфоны']")).click();
        sleep(2000);
        // Нажать на кнопку "Все фильтры"
        driver.findElement(By.xpath("//span[text() = 'Все фильтры']")).click();
        sleep(2000);
        // Задать параметр поиска до 20000 рублей
        driver.findElement(By.xpath("(//div[@data-prefix='до']//input)[1]")).sendKeys("20000");
        sleep(2000);
        // Скролл вниз, до элемента "Диагональ экрана (точно)"
        Object o = driver.executeScript("scrollBy(0,1500);");
        sleep(2000);
        // Нажать на элемент "Диагональ экрана (точно)"
        driver.findElement(By.xpath("//h4[contains(text(),'Диагональ экрана (точно)')]")).click();
        // Задать параметр поиска от 3 дюймов
        driver.findElement(By.xpath("(//div[@data-prefix = 'от']//input)[2]")).sendKeys("3");
        sleep(2000);
        // Выбор 5 популярных производителей
        driver.findElement(By.xpath("//div//label[@id='153043']")).click();
        driver.findElement(By.xpath("//div//label[@id='15292504']")).click();
        driver.findElement(By.xpath("//div//label[@id='459710']")).click();
        driver.findElement(By.xpath("//div//label[@id='22402130']")).click();
        driver.findElement(By.xpath("//div//label[@id='6278641']")).click();
        sleep(2000);
        // Нажать на кнопку Показать предложения
        driver.findElement(By.xpath("//a[@data-autotest-id='result-filters-link']")).click();
        // Запомнить первый телефон в списке
        String phone = driver.findElement(By.xpath("(//h3[@data-zone-name = 'title'])[1]")).getText();
        // Создание X-path для поиска телефона по названию
        String newPhone = "//span[text() = '" + phone + "']";
        sleep(3000);
        // Изменить сортировку на "По цене"
        driver.findElement(By.xpath("//button[@data-zone-name='sort'][2]")).click();
        sleep(3000);
        WebElement showMore = driver.findElement(By.xpath("//button//span[text() = 'Показать ещё']"));
        boolean condition = false;
        // Создание цикла для поиска телефона
        while (condition == false) {
            try {
                // Попытка кликнуть на запомненный телефон
                driver.findElement(By.xpath(newPhone)).click();
                condition = true;
            } catch (Exception e) { // Действие выполняется в случае, если запомненный телефон не был найден
                // Скролл до элемента "Показать еще"
                driver.executeScript("arguments[0].scrollIntoView(true);", showMore);
                // Нажать на кнопку "Показать еще"
                driver.findElement(By.xpath("//button//span[text() = 'Показать ещё']")).click();
                sleep(5000);
            }
        }
        sleep(5000);
        // получениее массива вкладок для переключения на нужную
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        // Переключение на нужную вкладку с запомненным телефоном
        driver.switchTo().window(tabs.get(1));
        Duration s = Duration.ofSeconds(20);
        // Ожидание, пока элемент оценки не появится на странице
        WebElement dynamicElement = (new WebDriverWait(driver,s))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='ybvaC']")));
        // Вывести цифровое значение оценки телефона
        System.out.println(driver.findElement(By.xpath("//span[@class='ybvaC']")).getText());
        // Закрыть браузер
        driver.quit();
    }

}


