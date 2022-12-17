package com.example.businessdiscovery.domain.scraping.bean;

import com.example.businessdiscovery.shared.InstagramProperties;
import com.example.businessdiscovery.shared.SeleniumProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URLEncoder;
import java.time.Duration;

public class InstagramLogin {

    public void main(String keyword) {
        try {
            InstagramProperties instagramProperties = InstagramProperties.createInstance();
            SeleniumProperties seleniumProperties = SeleniumProperties.createInstance();

            WebDriverManager.chromedriver().setup();

            System.setProperty("webdriver.chrome.driver", seleniumProperties.getWebDriverPath());
            ChromeDriver driver = new ChromeDriver();

            // ログイン
            driver.get("https://www.instagram.com/");
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

            WebElement usernameTextBox = driver.findElement(By.name("username"));
            WebElement passwordTextBox = driver.findElement(By.name("password"));
            WebElement submitButton = driver.findElement(By.className("_acan"));
            usernameTextBox.sendKeys(instagramProperties.getEmailAddress());
            passwordTextBox.sendKeys(instagramProperties.getPassword());
            submitButton.click();

            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
            try {
                WebElement messageOfIsSaveLoginInfo = driver.findElement(By.className("_aa58"));
                if ("ログイン情報を保存しますか？".equals(messageOfIsSaveLoginInfo.getText())) {
                    WebElement submitButtonOfIsSaveLoginInfo = driver.findElement(By.className("_acan"));
                    submitButtonOfIsSaveLoginInfo.click();
                }
            } catch (Exception e) {
                // ログイン情報を保存のダイアログが出なければ無視して次の処理にすすむ
                System.out.println("「ログイン情報を保存しますか？」：無し");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            }

            while (!(driver.findElements(By.cssSelector("button[class='_a9-- _a9_0']")).size() > 0)) {
                continue;
            }
            WebElement submitButtonOfIsONInfoMessage = driver.findElement(By.cssSelector("button[class='_a9-- _a9_0']"));
            submitButtonOfIsONInfoMessage.click();

            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));

            String encodedKeyword = URLEncoder.encode(keyword, "UTF-8");
            driver.get("https://www.instagram.com/explore/tags/" + encodedKeyword);

            // セッションを終了します
//            driver.quit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}
