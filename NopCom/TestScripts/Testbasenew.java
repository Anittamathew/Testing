package com.testscripts;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.pages.Checkoutpage;
import com.pages.LoginPage;
import com.pages.Logoutpage;
import com.pages.Productview;
import com.pages.RegisterPage;
import com.pages.Search;

import io.github.bonigarcia.wdm.WebDriverManager;

public class testbasenew {

	public WebDriver driver;
	RegisterPage registerPage;
	LoginPage loginPage;
	Logoutpage logoutpage;
	Productview productview;
	Checkoutpage checkoutpage;
	Search search;
	
	@org.testng.annotations.Parameters({"Browser","Url"})
	@BeforeClass
	public void setup(String Browser, String Url) throws IOException {
		
		
		if(Browser.equalsIgnoreCase("Chrome"))
		{ 
//			WebDriverManager.chromedriver().setup();
//			System.setProperty("webdriver.chrome.driver","C:\\Intel\\chromedriver_win32_\\chromedriver.exe");
//			driver = new ChromeDriver();
			
			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver","C:\\Intel\\chromedriver_win32_\\chromedriver.exe");
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(co);
		}
		
		else if(Browser.equalsIgnoreCase("Edge")) 
		{
			WebDriverManager.edgedriver().setup();
			System.setProperty("webdriver.edge.driver","C:\\Intel\\edgedriver_win64_\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.get(Url);
//		driver.findElement(By.linkText("Forgot password?")).click();
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.titleContains("driver.getTitle()"));
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
		registerPage = new RegisterPage(driver);
		loginPage = new LoginPage(driver);
		logoutpage = new Logoutpage(driver);
		productview = new Productview(driver);	
		search = new Search(driver);
		checkoutpage = new Checkoutpage(driver);
				
	}
	@AfterClass
	public void tearDown() {
//		driver.close();
	}
}
