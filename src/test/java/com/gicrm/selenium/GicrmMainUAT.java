package com.gicrm.selenium;

import static org.testng.Assert.fail;

import java.awt.RenderingHints.Key;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GicrmMainUAT {

	WebDriver driver;
	private static Properties gicrmInputProp;
	private static Properties gicrmXpathProp;

	@BeforeTest
	@Parameters({ "browser", "url", "inputFile" })
	public void init(String browser, String url, String inputFile) throws Exception {
		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\Software\\Selenium\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();

		} else if (browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.chrome.driver", "D:\\Software\\Selenium\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.get(url);
		gicrmInputProp = new Properties();
		gicrmXpathProp = new Properties();
		String gicrmInputs = inputFile;
		String xpath = "gicrmXpath.properties";
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try (InputStream gicrmStream = loader.getResourceAsStream(gicrmInputs); InputStream xpathStream = loader.getResourceAsStream(xpath)) {
			gicrmInputProp.load(gicrmStream);
			gicrmXpathProp.load(xpathStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}

	@Test(priority = 0)
	public void login() {

		driver.findElement(By.xpath(gicrmXpathProp.getProperty("user_txt"))).sendKeys(gicrmInputProp.getProperty("user"));
		driver.findElement(By.xpath(gicrmXpathProp.getProperty("pwd_txt"))).sendKeys(gicrmInputProp.getProperty("password"));
		driver.findElement(By.xpath(gicrmXpathProp.getProperty("submit"))).click();
	}

	/*
	 * @Test(priority = 1) public void leadModule() {
	 * 
	 * WebElement element; WebDriverWait wait = new WebDriverWait(driver, 100);
	 * element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
	 * "//*[@id='leads']/a/span"))); element.click();
	 * 
	 * 
	 * driver.findElement(By.xpath(gicrmXpathProp.getProperty("leadmodule"))).
	 * click();
	 * driver.findElement(By.xpath(gicrmXpathProp.getProperty("leadcreate"))).
	 * click();
	 * driver.findElement(By.xpath(gicrmXpathProp.getProperty("companyname")))
	 * .sendKeys(gicrmInputProp.getProperty("cmyname")); new
	 * Select(driver.findElement(By.xpath(gicrmXpathProp.getProperty("title"))))
	 * .selectByVisibleText("Mr.");
	 * driver.findElement(By.xpath(gicrmXpathProp.getProperty("title"))).click()
	 * ; driver.findElement(By.xpath(gicrmXpathProp.getProperty("fstname")))
	 * .sendKeys(gicrmInputProp.getProperty("name1"));
	 * driver.findElement(By.xpath(gicrmXpathProp.getProperty("lstname")))
	 * .sendKeys(gicrmInputProp.getProperty("name2"));
	 * driver.findElement(By.xpath(gicrmXpathProp.getProperty("offphone")))
	 * .sendKeys(gicrmInputProp.getProperty("offnumber"));
	 * driver.findElement(By.xpath(gicrmXpathProp.getProperty("mobile")))
	 * .sendKeys(gicrmInputProp.getProperty("mobilenumbr")); new
	 * Select(driver.findElement(By.xpath(gicrmXpathProp.getProperty(
	 * "leadstatus")))) .selectByVisibleText("Contacted");
	 * 
	 * driver.findElement(By.xpath(gicrmXpathProp.getProperty("emailid")))
	 * .sendKeys(gicrmInputProp.getProperty("email"));
	 * 
	 * new Select(driver.findElement(By.xpath(gicrmXpathProp.getProperty(
	 * "leadsource")))) .selectByVisibleText("Self-Generated");
	 * driver.findElement(By.xpath(gicrmXpathProp.getProperty("leadsource"))).
	 * click();
	 * 
	 * driver.findElement(By.xpath(gicrmXpathProp.getProperty("leadcmt"))).
	 * sendKeys(gicrmInputProp.getProperty("leadcomment"));
	 * 
	 * new
	 * Select(driver.findElement(By.xpath(gicrmXpathProp.getProperty("referral")
	 * ))).selectByVisibleText("Existing Client");
	 * driver.findElement(By.xpath(gicrmXpathProp.getProperty("referral"))).
	 * click();
	 * driver.findElement(By.cssSelector("option[value=\"Hot Lead\"]")).click();
	 * driver.findElement(By.xpath(gicrmXpathProp.getProperty("savebtn"))).click
	 * ();
	 * 
	 * }
	 */

	@Test(priority = 3)
	public void oppt_type() throws InterruptedException {
		WebElement element;
		WebDriverWait wait = new WebDriverWait(driver, 100);
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='opportunities']/a/span")));
		element.click();

		driver.findElement(By.xpath(gicrmXpathProp.getProperty("create"))).click();

		Select oSelect = new Select(driver.findElement(By.id("Opportunity_RecordType_value")));
		List<WebElement> elementCount = oSelect.getOptions();
		int iSize = elementCount.size();

		for (int i = 0; i < iSize; i++) {

			String sValue = oSelect.getOptions().get(i).getText();

			System.out.println(sValue);

			switch (sValue) {
			case "Project Final":
				
				Select dropdown = new Select(driver.findElement(By.id("Opportunity_RecordType_value")));
				dropdown.selectByVisibleText("Project Final");
				driver.findElement(By.xpath(gicrmXpathProp.getProperty("next"))).click();
				opptCreation();
				
				break;
				
			case "Recurring Final":
				
				
				Select dropdown1 = new Select(driver.findElement(By.id("Opportunity_RecordType_value")));
				dropdown1.selectByVisibleText("Recurring Final");
				driver.findElement(By.xpath(gicrmXpathProp.getProperty("next"))).click();
				opptCreation();
				break;
				
				default:
					System.out.println("Nasama Pochu");
			}

			/*
			 * if (sValue.equals("Project Final")) {
			 * 
			 * Select dropdown = new
			 * Select(driver.findElement(By.id("Opportunity_RecordType_value")))
			 * ; dropdown.selectByVisibleText("Project Final");
			 * driver.findElement(By.xpath(gicrmXpathProp.getProperty("next"))).
			 * click(); opptCreation();
			 * 
			 * } else{ Select dropdown = new
			 * Select(driver.findElement(By.id("Opportunity_RecordType_value")))
			 * ; dropdown.selectByVisibleText("Recurring Final");
			 * driver.findElement(By.xpath(gicrmXpathProp.getProperty("next"))).
			 * click(); opptCreation(); }
			 */
		}

	}

	@Test(dependsOnMethods = { "oppt_type" })
	public void opptCreation() throws InterruptedException {

		driver.findElement(By.xpath(gicrmXpathProp.getProperty("opptname"))).sendKeys(gicrmInputProp.getProperty("opp_name"));
		driver.findElement(By.xpath(gicrmXpathProp.getProperty("Acc"))).clear();
		driver.findElement(By.xpath(gicrmXpathProp.getProperty("Acc"))).sendKeys(gicrmInputProp.getProperty("acc_name"));
		Thread.sleep(1000);
		driver.findElement(By.xpath(gicrmXpathProp.getProperty("Acc"))).sendKeys(Keys.DOWN);
		driver.findElement(By.xpath(gicrmXpathProp.getProperty("Acc"))).sendKeys(Keys.ENTER);

		driver.findElement(By.xpath(gicrmXpathProp.getProperty("oppt_stage"))).click();
		new Select(driver.findElement(By.xpath(gicrmXpathProp.getProperty("oppt_stage")))).selectByVisibleText("Estimate");
		driver.findElement(By.xpath(gicrmXpathProp.getProperty("oppt_stage"))).click();
		driver.findElement(By.xpath(gicrmXpathProp.getProperty("oppt_cdate"))).click();
		driver.findElement(By.linkText("30")).click();

		driver.findElement(By.xpath(gicrmXpathProp.getProperty("eestimator"))).sendKeys(gicrmInputProp.getProperty("ename"));
		Thread.sleep(1000);
		driver.findElement(By.xpath(gicrmXpathProp.getProperty("eestimator"))).sendKeys(Keys.DOWN);
		driver.findElement(By.xpath(gicrmXpathProp.getProperty("eestimator"))).sendKeys(Keys.ENTER);

		driver.findElement(By.xpath(gicrmXpathProp.getProperty("manager"))).sendKeys(gicrmInputProp.getProperty("mname"));
		Thread.sleep(1000);
		driver.findElement(By.xpath(gicrmXpathProp.getProperty("manager"))).sendKeys(Keys.DOWN);
		driver.findElement(By.xpath(gicrmXpathProp.getProperty("manager"))).sendKeys(Keys.ENTER);

		/*
		 * JavascriptExecutor js = (JavascriptExecutor) driver; js.
		 * executeScript("$('input[name='Opportunity[estimatorApproval]']').each(function(){ $(this).trigger('click')});"
		 * );
		 */
		driver.findElement(By.xpath(gicrmXpathProp.getProperty("estimator_approval"))).click();

		driver.findElement(By.xpath(gicrmXpathProp.getProperty("save_oppt"))).click();
		
		//addproduct();

	}
	/*@Test(dependsOnMethods = { "opptCreation" })
	public void addproduct(){
		
		driver.findElement(By.xpath(gicrmXpathProp.getProperty("addprod"))).click();
		driver.findElement(By.xpath(gicrmXpathProp.getProperty(key)))
	}*/
	
	/*
	 * @Test(priority = 4) public void agmtCreation() throws
	 * InterruptedException {
	 * driver.findElement(By.linkText("Agreements")).click();
	 * driver.findElement(By.xpath(
	 * "//div[@id='CreateMenuActionElement--yt1']/a/span")).click();
	 * driver.findElement(By.id("Agreement_name")).click();
	 * driver.findElement(By.id("Agreement_name")).clear();
	 * driver.findElement(By.id("Agreement_name")).sendKeys("ps-agmt-1");
	 * Thread.sleep(2000);
	 * driver.findElement(By.id("Agreement_account_name")).clear();
	 * driver.findElement(By.id("Agreement_account_name")).sendKeys(
	 * "TEST19052016");
	 * driver.findElement(By.id("Agreement_account_name")).sendKeys(Keys.DOWN);
	 * driver.findElement(By.id("Agreement_account_name")).sendKeys(Keys.ENTER);
	 * Thread.sleep(2000);
	 * driver.findElement(By.id("Agreement_closeDate")).click();
	 * driver.findElement(By.linkText("31")).click(); Thread.sleep(2000);
	 * driver.findElement(By.xpath("//a[@id='saveyt3']/span[3]")).click();
	 * 
	 * }
	 * 
	 * @Test(priority = 5) public void routeCreation() {
	 * 
	 * driver.findElement(By.linkText("Routes")).click();
	 * driver.findElement(By.xpath(
	 * "//div[@id='CreateMenuActionElement--yt1']/a/span")).click();
	 * driver.findElement(By.id("Route_routename")).click();
	 * driver.findElement(By.id("Route_routename")).clear();
	 * driver.findElement(By.id("Route_routename")).sendKeys("route-1");
	 * driver.findElement(By.id("Route_crewname")).click(); new
	 * Select(driver.findElement(By.id("Route_crewname"))).selectByVisibleText(
	 * "Test1"); driver.findElement(By.id("Route_crewname")).click();
	 * JavascriptExecutor js = (JavascriptExecutor) driver;
	 * js.executeScript(" $('input[type=checkbox]').prop('checked', true);");
	 * 
	 * driver.findElement(By.xpath("//a[@id='nextyt3']/span[3]")).click();
	 * driver.findElement(By.id("agreement_name_value")).click();
	 * driver.findElement(By.id("agreement_name_value")).clear();
	 * driver.findElement(By.id("agreement_name_value")).sendKeys("test");
	 * driver.findElement(By.xpath("//a[@id='search']/span[3]")).click();
	 * driver.findElement(By.xpath("//tr[@id='0']/td/label")).click();
	 * driver.findElement(By.xpath("(//a[@id='saveyt2']/span[3])[2]")).click();
	 * driver.findElement(By.xpath("//a[@id='0']/span[3]")).click();
	 * driver.findElement(By.xpath("(//input[@type='checkbox'])[18]")).click();
	 * driver.findElement(By.xpath("(//a[@id='saveyt2']/span[3])[2]")).click();
	 * }
	 * 
	 * @Test(priority = 6) public void crewCreation() {
	 * driver.findElement(By.xpath("//li[@id='crews']/a/span")).click();
	 * driver.findElement(By.xpath(
	 * "//div[@id='CreateMenuActionElement--yt1']/a/span")).click();
	 * driver.findElement(By.id("Crew_crewname")).click();
	 * driver.findElement(By.id("Crew_crewname")).clear();
	 * driver.findElement(By.id("Crew_crewname")).sendKeys("crew 1");
	 * driver.findElement(By.id("Crew_crewmanager")).click(); new
	 * Select(driver.findElement(By.id("Crew_crewmanager"))).
	 * selectByVisibleText("sathish m");
	 * driver.findElement(By.id("Crew_crewmanager")).click();
	 * driver.findElement(By.xpath("//a[@id='nextyt3']/span[3]")).click();
	 * driver.findElement(By.id("check_employee_id_11")).click();
	 * driver.findElement(By.xpath("(//a[@id='nextyt3']/span[3])[2]")).click();
	 * 
	 * }
	 */

	/*
	 * @AfterClass public void Close() { driver.close(); }
	 */
}
