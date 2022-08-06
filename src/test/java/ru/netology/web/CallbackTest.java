package ru.netology.web;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.Text;

import java.util.List;

public class CallbackTest {
    private WebDriver driver;

    @BeforeAll
    // делаем метод в котором устанавливаем свойства для драйвера (chrome driver из директории driver/win
    static void setUpAll() {
    System.setProperty("webdriver.chrome.driver", "driver/win/chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();  // подключаем браузер
    }

    @AfterEach
    public void close() {
        driver.quit();  // закрываем браузер
        driver = null;  // обнуляем драйвер после работы
    }

    @Test
    public void test() {
        driver.get("http://localhost:9999"); // открываем браузер
        List<WebElement> elements = driver.findElements(By.className("input__control")); // создадим List внутри которого будут храниться 2 элемента с classname input__control
        elements.get(0).sendKeys("Александр");
        elements.get(1).sendKeys("+79807133080");

//        // набираем костяк теста без описания функции findElement
//        driver.findElement(By.className("input__control")).sendKeys("Александр");     // находим первый элемент - поле ввода имени, и вводим в него имя Андрей (командой sendkeys)
//        driver.findElement().sendKeys("+79807133080"); // заполняем следующее поле заявки - телефон

        driver.findElement(By.className("checkbox__box")).click(); // следующее поле - чекбокс, командой click ставим в нем галочку
        driver.findElement(By.tagName("button")).click(); // следующее поле - кнопка ОТПРАВИТЬ - нажимаем ее той же командой click

        // теперь необходимо проверить что открылась страница с сообщением об успешной отправке заполненной формы

        String text = driver.findElement(By.className("paragraph")).getText(); // ищем по части выражения

//        Assertions.assertEquals("  Ваша заявка успешно отправлена!", text);
        Assertions.assertEquals("Ваша заявка успешно отправлена!", text.trim());  // или убираем пробелы методом .trim
    }

}
