package eu.tsystems.mms.tic.testframework.mobile.playground;

import eu.tsystems.mms.tic.testframework.common.PropertyManager;
import eu.tsystems.mms.tic.testframework.mobile.MobileProperties;
import eu.tsystems.mms.tic.testframework.mobile.driver.Direction;
import eu.tsystems.mms.tic.testframework.mobile.driver.MobileDriver;
import eu.tsystems.mms.tic.testframework.mobile.driver.MobileDriverManager;
import eu.tsystems.mms.tic.testframework.report.TesterraListener;
import eu.tsystems.mms.tic.testframework.testing.TesterraTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Created by rnhb on 12.07.2017.
 */
@Listeners({TesterraListener.class})
public class GridTest extends TesterraTest {

    //    protected static final Logger LOGGER = LoggerFactory.getLogger(GridTest.class);

    @Test
    public void testName() throws Exception {
        //        PropertyManager.getThreadLocalProperties().setProperty(MobileProperties.MOBILE_GRID_USER, "fnu-ci-soliver");
        //        PropertyManager.getThreadLocalProperties().setProperty(MobileProperties.MOBILE_GRID_PASSWORD, "Ah89fh3jtklnwtk3tmsgskg");
        //        PropertyManager.getThreadLocalProperties().setProperty(MobileProperties.MOBILE_GRID_PROJECT, "sOliver");
        PropertyManager.getThreadLocalProperties().setProperty(MobileProperties.MOBILE_DEVICE_FILTER, "os.version=*");
        PropertyManager.getThreadLocalProperties().setProperty(MobileProperties.MOBILE_SERVER_HOST, "localhost");
        PropertyManager.getThreadLocalProperties().setProperty(MobileProperties.MOBILE_MONITORING_ACTIVE, "true");

        //        try {
        MobileDriver mobileDriver = MobileDriverManager.getMobileDriver();
        mobileDriver.reserveDeviceByFilter();
        mobileDriver.launchApplication("http://www.wikipedia.de");
        mobileDriver.swipe(Direction.DOWN, 0.2f, 100);
        //        } catch (Throwable a) {
        //            System.out.println(a);
        //            a.printStackTrace();
        //        }
    }
}