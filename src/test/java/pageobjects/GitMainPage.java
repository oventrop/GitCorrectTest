package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GitMainPage extends AbstractPage {

	public GitMainPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//*[text()='Sign in']")
	private WebElement signInButton;

	public GitLoginPage pressSignInButton() {
		signInButton.click();
		return new GitLoginPage(driver);

	}

}
