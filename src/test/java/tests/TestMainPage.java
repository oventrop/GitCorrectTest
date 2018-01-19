package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageobjects.GitLoginPage;
import pageobjects.GitMainPage;
import pageobjects.GitRepositoryPage;

public class TestMainPage {

	private final String LOGIN_EMAIL = "d.galievsky@gmail.com";
	private final String LOGIN_PASSWORD = "master1";
	private WebDriver wd;

	@BeforeClass(description = "Start browser")
	public void startBrowser() {
		System.setProperty("webdriver.gecko.driver", "C:/_DATA/geckodriver.exe");
		wd = new FirefoxDriver();

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
		wd.close();
	}

}
