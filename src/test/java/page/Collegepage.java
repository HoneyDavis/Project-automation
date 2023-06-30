package page;

import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Collegepage {
	WebDriver driver;
	String baseurl="https://newmancollege.ac.in";
	By admission=By.xpath("//*[@id=\"menu-item-4649\"]/a");
	By form=By.xpath("/html/body/section[4]/div/div/div[1]/div/p[3]/span/strong/a");
	By emailid=By.xpath("//*[@id=\"app\"]/div/div/div/div/div/div[1]/div/div/div[2]/div/form/div[1]/input");
	By password=By.xpath("//*[@id=\"app\"]/div/div/div/div/div/div[1]/div/div/div[2]/div/form/div[2]/input");
	By signin=By.xpath("//*[@id=\"app\"]/div/div/div/div/div/div[1]/div/div/div[2]/div/form/div[3]/div/button");
	By register=By.xpath("//*[@id=\"app\"]/div/div/div/div/div/div[1]/div/div/div[2]/div/div[2]/div[1]/div/button/span");
	By name=By.xpath("//*[@id=\"app\"]/div/div/div/div/div/div[1]/div/div[2]/div[1]/div[3]/input[@placeholder='Enter Name']");
	By gender=By.xpath("//*[@id=\"app\"]/div/div/div/div/div/div[1]/div/div[2]/div[2]/div[3]/select");
	By emailid2=By.xpath("//*[@id=\"app\"]/div/div/div/div/div/div[1]/div/div[2]/div[3]/div[3]/input");
	By mobile=By.xpath("//*[@id=\"app\"]/div/div/div/div/div/div[1]/div/div[2]/div[4]/div[3]/input");
	By back=By.xpath("//*[@id=\"pills-home-tab\"]");
	By staff=By.xpath("/html/body/section[1]/div/div/div[2]/div[2]/a[3]");
	By admin=By.xpath("//*[@id=\"menu-item-163\"]/span");
	By arrow=By.xpath("//*[@id=\"menu-item-164\"]/span");
	By management=By.xpath("//*[@id=\"menu-item-165\"]/a");
	By search=By.xpath("//*[@id=\"inputEmail3\"]");
	
	
	public Collegepage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void home()
	{

		System.out.println("Title of the page is "+driver.getTitle());
        String a=driver.getPageSource();
		
		if(a.contains(" Alumni Login"))
			System.out.println("Text is present");
		
		else
			System.out.println("Text is not present");	
		
		driver.findElement(search).click();
		driver.findElement(search).sendKeys("NAAC",Keys.ENTER);
	}
	
	public void academics(String id,String pswd)
	{
		driver.findElement(admission).click();
		driver.findElement(form).click();
		String actual=driver.getCurrentUrl();
		String expect="https://newmancollegev4.linways.com/v4/adm-applicant/login";
		Assert.assertEquals(expect,actual);
	    driver.findElement(emailid).sendKeys(id);
		driver.findElement(password).sendKeys(pswd);
		driver.findElement(signin).click();
		
	}
	
	
	public void form() throws Exception
	{
		driver.findElement(register).click();
		FileInputStream f=new FileInputStream("C:\\Users\\aneesh\\Desktop\\software Testing\\ExcelTesting\\Book2.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(f);
		XSSFSheet sh=wb.getSheet("Sheet1");
		
			String n=sh.getRow(1).getCell(0).getStringCellValue();
			String g=sh.getRow(1).getCell(1).getStringCellValue();
			String id=sh.getRow(1).getCell(2).getStringCellValue();
			int mob=(int)sh.getRow(1).getCell(3).getNumericCellValue();
			String number=String.valueOf(mob);
			driver.findElement(name).sendKeys(n);
			
			WebElement ge=driver.findElement(gender);
			Select a=new Select(ge);                                                                                                 
			a.selectByValue(g);
			driver.findElement(emailid2).sendKeys(id);
			driver.findElement(mobile).sendKeys(number);
			driver.findElement(back).click();
			
	}
	
	public void home1()
	{
		
		String actual=driver.getCurrentUrl();
		String expect="https://newmancollegev4.linways.com/v4/adm-applicant/login";
		Assert.assertEquals(expect,actual);
		driver.navigate().to(baseurl);
		
	}
	
	public void staff() throws Exception
	{
		

		String parentwindow=driver.getWindowHandle();
		driver.findElement(staff).click();
		
		Set<String>allwindow=driver.getWindowHandles();
		
		for(String a:allwindow)
		{
			if(!a.equals(parentwindow))
				driver.switchTo().window(a);
			
		}
		
		String actual=driver.getCurrentUrl();
		String expect="https://newmancollege.linways.com/staff/";
		Assert.assertEquals(expect,actual);
		File a=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		FileHandler.copy(a,new File("./src/test/resources/Screenshot/page.png"));
		driver.switchTo().window(parentwindow);
	}
	
	public void adminstartion()
	{
		
		driver.findElement(admin).click();
		driver.findElement(arrow).click();
		driver.findElement(management).click();
		
        List<WebElement>links=driver.findElements(By.tagName("a"));
		
		System.out.println("Total no. of links = "+links.size());
	
		for(WebElement a:links)
		{
			String link=a.getAttribute("href");
			responsecode(link);
		}
		
	}
		
		private void responsecode(String link)
		{
			try
			{
			
				URL n=new URL(link);
				HttpURLConnection c=(HttpURLConnection)n.openConnection();
				c.connect();
				System.out.println("Respose code of "+link+"---"+c.getResponseCode());
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		
	
}
		
		
		

