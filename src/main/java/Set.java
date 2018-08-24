import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import  org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Iterator;


public class Set {

    public String proxy(String ip, String hostI) throws IOException {

            Boolean yesB = true;
            boolean alertB = true;
            int host = Integer.parseInt(hostI);

            WebDriver driver;

            System.setProperty("webdriver.gecko.driver", "C:\\Users\\admin\\IdeaProjects\\geckodriver.exe");

            FirefoxOptions profile = new FirefoxOptions();
            profile.addPreference("permissions.default.image", 2);
//            profile.addPreference("browser.startup.homepage","http://campaign.consumer.huawei.com/sk/view/11167");
            profile.addPreference("network.proxy.type", 1);

            profile.addPreference("network.proxy.http", ip);

            profile.addPreference("network.proxy.http_port", host);

            profile.addPreference("network.proxy.ssl", ip);

            profile.addPreference("network.proxy.ssl_port", host);


            driver = new FirefoxDriver(profile);
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            try{
            driver.get("http://campaign.consumer.huawei.com/sk/view/11167");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            while (!driver.getWindowHandles().isEmpty()&(yesB&alertB) ) {
                String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
                String subWindowHandler = null;

                java.util.Set<String> handles = driver.getWindowHandles();// get all window handles

                Iterator<String> iterator = handles.iterator();
                while (iterator.hasNext() ) {
                    subWindowHandler = iterator.next();

                    driver.switchTo().window(subWindowHandler); // switch to popup window
                    try {
                        WebElement yes = driver.findElement(By.className("cookies-yes"));
                        if (yes.isEnabled() & yes.isDisplayed()) {
                            System.out.println(yes.isSelected());
                            System.out.println(yes.isDisplayed());
                            System.out.println(yes.getText());
                            System.out.println("Find -cookies-yes");
                            yes.sendKeys(Keys.RETURN);
                            System.out.println("click yes");
                            yesB = yes.isDisplayed();
                        }
                    } catch (NoSuchElementException b) {
                        System.out.println("***************  not find  cookies-yes");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("********* YES");

                    }
                    try {
                        WebElement alert1 = driver.findElement(By.xpath("/html/body/footer/div[2]/div/div[4]/div/div[1]"));

                        if (alert1.isEnabled() & alert1.isDisplayed()) {
                            System.out.println(alert1.isSelected());
                            System.out.println(alert1.isDisplayed());
                            System.out.println(alert1.getLocation());
                            System.out.println("Find alert");
                            JavascriptExecutor executor = (JavascriptExecutor) driver;
                            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

                            executor.executeScript("arguments[0].click();", alert1);
//                    new Actions(driver).moveToElement(alert).click();
                            System.out.println("click alert");

                        }
                        alertB = alert1.isDisplayed();
                    } catch (NoSuchElementException b) {
                        System.out.println("*******************not find modal-alert");

                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("********* alert");
                    }
                }
                // Now you are in the popup window, perform necessary actions here
                driver.switchTo().window(parentWindowHandler);// switch back to parent window
                System.out.println("switch back to parent window");
            }
//            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            try {
                WebDriverWait wait = new WebDriverWait(driver, 5);

                WebElement num = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/main/section[2]/div[2]/div[2]/div[1]/img[1]")));
                System.out.println(num.getText());
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

                executor.executeScript("arguments[0].click();", num);
                System.out.println("naslo");
                System.out.println(num.isEnabled());

            } catch (Exception a) {
                System.out.println("***********  not find like");
                a.printStackTrace();
            }
//            driver.quit();

        } catch (Exception e) {
            System.out.println("error firefox.driver");
            e.printStackTrace();
        }
        finally {

            driver.quit();
        }
        return "click";
    }

}