package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import page.Collegepage;

public class Collegetest {


WebDriver driver;
	
	@BeforeTest
	public void open() 
	{
		driver=new ChromeDriver();
		driver.get("https://newmancollege.ac.in");
	}
	
	@Test
	public void test()throws Exception
	{
		
		Collegepage ob=new Collegepage(driver);
		ob.home();
		ob.academics("honey@gmail.com","honey");
		ob.form();
		ob.home1();
		ob.staff();
		ob.adminstartion();

		
	}

}
