package com.gicrm.selenium;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LeadCreation {

	private WebDriver driver;
	private static Properties leadXPathProp;
	private static Properties leadInputsProp;
	
	
	
	

	@Test
	public void leadCreation() {
		
		
		/*WebElement element;
		WebDriverWait wait = new WebDriverWait(driver, 100);
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='leads']/a/span")));
		element.click();

		WebElement element1;
		WebDriverWait wait1 = new WebDriverWait(driver, 100);
		element1 = wait1
				.until(ExpectedConditions.elementToBeClickable(By.xpath(leadModule)));
		element1.click();

		driver.findElement(By.xpath("//*[@id='Contact_companyName']")).sendKeys("Companyname");

		new Select(driver.findElement(By.xpath("//*[@id='Contact_title_value']"))).selectByVisibleText("Mr.");
		driver.findElement(By.xpath("//*[@id='Contact_title_value']")).click();
		driver.findElement(By.xpath("//*[@id='Contact_firstName']")).sendKeys("Test");
		driver.findElement(By.xpath("//*[@id='Contact_lastName']")).sendKeys("User");
		driver.findElement(By.xpath("//*[@id='Contact_officePhone']")).sendKeys("1234567890");
		driver.findElement(By.xpath("//*[@id='Contact_primaryEmail_emailAddress']")).sendKeys("test@gmail.com");
		new Select(driver.findElement(By.xpath("//*[@id='Contact_statusCstm_value']")))
				.selectByVisibleText("Contacted");

		driver.findElement(By.xpath("//*[@id='Contact_statusCstm_value']")).click();

		new Select(driver.findElement(By.xpath("//*[@id='Contact_source_value']")))
				.selectByVisibleText("Self-Generated");
		driver.findElement(By.xpath("//*[@id='Contact_source_value']")).click();

		driver.findElement(By.xpath("//*[@id='Contact_leadCommentCstm']")).sendKeys("This is test comment for lead");

		new Select(driver.findElement(By.xpath("//*[@id='Contact_referalTypeCstm_value']")))
				.selectByVisibleText("Existing Client");
		driver.findElement(By.xpath("//*[@id='Contact_referalTypeCstm_value']")).click();

		driver.findElement(By.xpath("//*[@id='saveyt2']/span[3]")).click();

	}*/

}}
