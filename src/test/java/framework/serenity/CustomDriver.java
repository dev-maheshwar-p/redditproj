package framework.serenity;

import net.thucydides.core.webdriver.DriverSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

/*
	This class is used to customize/override the default behaviour of the Chrome Driver instance instantiated
	by Serenity.
 */
public class CustomDriver implements DriverSource {

	Logger logger = LogManager.getLogger(CustomDriver.class);

	private static final String DRIVER_PATH_ON_JENKINS_SLAVE = "/windriver/chromedriver.exe";
	private static final String DRIVER_PATH_ON_LOCALHOST = "/chromedriver";

	@Override
	public WebDriver newDriver() {

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions chromeOptions = new ChromeOptions();
		Map<String, Object> chromePreferences = new HashMap<>();

		String downloadDir=System.getProperty("user.dir") + "/target";
		logger.info("Download Location" + downloadDir);

		String running_environment = System.getProperty("RUN_ENV");

		boolean isRunningEnvNull = running_environment==null;
		boolean isRunOnPipeline = (!isRunningEnvNull)?running_environment.equalsIgnoreCase("Pipeline"):false;

		String driverPath = (!isRunningEnvNull && isRunOnPipeline) ? DRIVER_PATH_ON_JENKINS_SLAVE : DRIVER_PATH_ON_LOCALHOST;
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + driverPath);
		capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);

		chromePreferences.put("profile.default_content_settings.popups", 0);
		chromePreferences.put("download.prompt_for_download", false);
		chromePreferences.put("download.default_directory", downloadDir);
		chromePreferences.put("pdfjs.disabled", true);
		chromePreferences.put("plugins.always_open_pdf_externally", true);

//		chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("--disable-notifications");
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--disable-dev-shm-usage");
		chromeOptions.addArguments("--window-size=1920,1080");

		chromeOptions.addArguments("--start-maximized");
		chromeOptions.setExperimentalOption("prefs", chromePreferences);
		chromeOptions.addArguments("--disable-web-security");

		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

		return new ChromeDriver(chromeOptions);

	}

	@Override
	public boolean takesScreenshots() {
		return true;
	}

}
