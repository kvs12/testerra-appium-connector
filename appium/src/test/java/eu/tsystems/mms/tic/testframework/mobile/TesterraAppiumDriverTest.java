/*
 * Testerra
 *
 * (C) 2020, Eric Kubenka, T-Systems Multimedia Solutions GmbH, Deutsche Telekom AG
 *
 * Deutsche Telekom AG and all other contributors /
 * copyright owners license this file to you under the Apache
 * License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

package eu.tsystems.mms.tic.testframework.mobile;

import eu.tsystems.mms.tic.testframework.mobile.driver.AppiumDriverManager;
import eu.tsystems.mms.tic.testframework.pageobjects.GuiElement;
import eu.tsystems.mms.tic.testframework.testing.TesterraTest;
import eu.tsystems.mms.tic.testframework.utils.UITestUtils;
import eu.tsystems.mms.tic.testframework.webdrivermanager.WebDriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 * Date: 24.06.2020
 * Time: 12:26
 *
 * @author Eric Kubenka
 */
public class TesterraAppiumDriverTest extends TesterraTest {

    private AppiumDriverManager appiumDriverManager = new AppiumDriverManager();

    @Test
    public void testT01_DoGoogleSearch() {

        final WebDriver driver = WebDriverManager.getWebDriver();
        final AppiumDriver<MobileElement> appiumDriver = appiumDriverManager.fromWebDriver(driver);

        appiumDriver.rotate(ScreenOrientation.LANDSCAPE);
        driver.get("https://www.google.com");

        UITestUtils.takeScreenshot(driver, true);
        appiumDriver.rotate(ScreenOrientation.PORTRAIT);
        UITestUtils.takeScreenshot(driver, true);

        final GuiElement guiElement = new GuiElement(driver, By.xpath("//input[@name='q']"));
        guiElement.asserts().assertIsPresent();
        guiElement.asserts().assertIsDisplayed();
        guiElement.type("Experitest");
    }
}
