package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GitLoginPage extends AbstractPage {

	public GitLoginPage(WebDriver driver) {
		super(driver);
	}

	private static final String LOGIN_FIELD = "login_field";
	private static final String PASSWORD_FIELD = "password";

	@FindBy(id = LOGIN_FIELD)
	private WebElement loginField;

	@FindBy(id = PASSWORD_FIELD)
	private WebElement passwordField;

	@FindBy(xpath = "//*[@name = 'commit']")
	private WebElement submitButton;

	//*[@id="login"]/form/div[4]/input[3]
	
	
	public GitRepositoryPage enterLoginPassword(String login, String password) {
	
		wait1.until(ExpectedConditions.visibilityOf(loginField));
		loginField.click();
		loginField.clear();
		loginField.sendKeys(login);

		passwordField.click();
		passwordField.clear();
		passwordField.sendKeys(password);
		
		submitButton.click();

		return new GitRepositoryPage(driver);

	}

}
