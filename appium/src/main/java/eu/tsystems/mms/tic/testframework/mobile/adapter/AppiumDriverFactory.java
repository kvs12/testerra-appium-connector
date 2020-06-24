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

package eu.tsystems.mms.tic.testframework.mobile.adapter;

import eu.tsystems.mms.tic.testframework.common.PropertyManager;
import eu.tsystems.mms.tic.testframework.exceptions.TesterraRuntimeException;
import eu.tsystems.mms.tic.testframework.exceptions.TesterraSystemException;
import eu.tsystems.mms.tic.testframework.mobile.driver.AppiumDriverRequest;
import eu.tsystems.mms.tic.testframework.webdrivermanager.UnspecificWebDriverRequest;
import eu.tsystems.mms.tic.testframework.webdrivermanager.WebDriverFactory;
import eu.tsystems.mms.tic.testframework.webdrivermanager.WebDriverRequest;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileBrowserType;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Creates {@link WebDriver} sessions for {@link io.appium.java_client.AppiumDriver} based on {@link AppiumDriverRequest}
 * Date: 24.06.2020
 * Time: 13:16
 *
 * @author Eric Kubenka
 */
public class AppiumDriverFactory extends WebDriverFactory<AppiumDriverRequest> {

    private static final String ACCESS_KEY = PropertyManager.getProperty("tt.mobile.grid.access.key");

    @Override
    protected AppiumDriverRequest buildRequest(WebDriverRequest webDriverRequest) {

        AppiumDriverRequest r;

        if (webDriverRequest instanceof AppiumDriverRequest) {
            r = (AppiumDriverRequest) webDriverRequest;
        } else if (webDriverRequest instanceof UnspecificWebDriverRequest) {
            r = new AppiumDriverRequest();
            r.copyFrom(webDriverRequest);
        } else {
            throw new TesterraSystemException(webDriverRequest.getClass().getSimpleName() + " is not allowed here");
        }

        return r;
    }

    @Override
    protected DesiredCapabilities buildCapabilities(DesiredCapabilities preSetCaps, AppiumDriverRequest request) {

        return preSetCaps;
    }

    @Override
    protected WebDriver getRawWebDriver(AppiumDriverRequest webDriverRequest, DesiredCapabilities desiredCapabilities) {

        if (webDriverRequest.browser != null && webDriverRequest.browser.equals("mobile_safari")) {

            desiredCapabilities.setCapability("testName", "Demo Tests");
            desiredCapabilities.setCapability("accessKey", ACCESS_KEY);
            desiredCapabilities.setCapability("deviceQuery", "@os='ios' and @category='PHONE'");
            desiredCapabilities.setBrowserName(MobileBrowserType.SAFARI);

            try {
                return new IOSDriver<>(new URL("https://mobiledevicecloud.t-systems-mms.eu/wd/hub"), desiredCapabilities);
            } catch (MalformedURLException e) {
                throw new SessionNotCreatedException("Could not create session, because URL invalid");
            }
        }

        throw new TesterraRuntimeException("Mobile Browser not supported.");
    }

    @Override
    protected void setupSession(EventFiringWebDriver eventFiringWebDriver, AppiumDriverRequest request) {

    }
}
