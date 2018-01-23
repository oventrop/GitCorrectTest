package tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageobjects.GitLoginPage;
import pageobjects.GitMainPage;
import pageobjects.GitRepositoryPage;

public class LinuxChromeTest {

	private final static String LOGIN_EMAIL = "d.galievsky@gmail.com";
	private final static String LOGIN_PASSWORD = "master1";

	private static WebDriver wd;

	@BeforeClass(description = "Start remote execution")
	public void startRemoteTest() throws MalformedURLException {
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setPlatform(Platform.LINUX);
		URL HUB_URL = new URL("http://epbyminw2700:4444/wd/hub");
		wd = new RemoteWebDriver(HUB_URL, cap);
		}

	@Test
	public void openGitHub() {
		wd.get("https://github.com/");
		wd.manage().window().maximize();
		Assert.assertTrue(isElementPresent(By.xpath("//h1[text()='Built for developers']")),
				"You opened not a GitHub page!");
	}

	@Test(dependsOnMethods = "openGitHub")
	public void openloginPage() {
		GitMainPage mainPage = new GitMainPage(wd);
		mainPage.pressSignInButton();
		Assert.assertTrue(wd.getTitle().equals("Sign in to GitHub · GitHub"));

	}

	@Test(dependsOnMethods = "openloginPage")
	public void loginToAccount() {
		GitLoginPage loginPage = new GitLoginPage(wd);
		loginPage.enterLoginPassword(LOGIN_EMAIL, LOGIN_PASSWORD);
		Assert.assertTrue(isElementPresent(By.xpath("//*[text()='New repository']")));
	}

	@Test(dependsOnMethods = "loginToAccount")
	public void logoutFromAccount() {
		GitRepositoryPage repoPage = new GitRepositoryPage(wd);
		repoPage.openDropdownMenu();
		repoPage.logoutFromRepository();
		Assert.assertTrue(isElementPresent(By.xpath("//h1[text()='Built for developers']")));
	}

	public boolean isElementPresent(By by) {
		return wd.findElement(by).isDisplayed();
	}

	@AfterClass(description = "Close browser")
	public void closeBrowser() {
		wd.quit();
	}

}
