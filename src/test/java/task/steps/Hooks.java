package task.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import task.utils.Driver;
import task.utils.WebUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static task.utils.Driver.driver;

public class Hooks {

    @BeforeAll
    public static void clearOldReportResults() {
        File folder = new File("allure-results");
        if (folder.exists()) {
            if (Objects.nonNull(folder.listFiles())) {
                Arrays.stream(folder.listFiles()).forEach(File::delete);
            }
            folder.delete();
            WebUtils.waitFor(1);
        }
    }

    @Before
    public void setup() {
        driver().manage().window().maximize();
        driver().manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
    }

    @After
    public void teardown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "image");

            String screenshotName = scenario.getName().replaceAll(" ", "_");
            Allure.addAttachment(screenshotName, new ByteArrayInputStream(((TakesScreenshot) driver()).getScreenshotAs(OutputType.BYTES)));
        }
        System.out.println("Run the command below in command line to see the report");
        System.out.println("allure serve " + System.getProperty("user.dir") + "\\allure-results");
        Driver.closeDriver();


    }


}
