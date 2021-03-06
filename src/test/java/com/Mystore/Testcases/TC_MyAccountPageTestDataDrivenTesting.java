package com.Mystore.Testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Mystore.pageobject.IndexPage;
import com.Mystore.pageobject.MyAccount;
import com.Mystore.pageobject.accountCreationDetails;
import com.Mystore.pageobject.registeredUserAccount;
import com.Mystore.utility.ReadExcelFile;

public class TC_MyAccountPageTestDataDrivenTesting extends BaseClass {
	@Test(enabled=false)
	public void verifyRegistrationAndLogin()
	{

		logger.info("***************TestCase Verify Registration and Login starts*****************"); 


		IndexPage pg = new IndexPage(driver);

		pg.clickOnSignIn();
		logger.info("Clicked on sign in link");

		MyAccount myAcpg = new MyAccount(driver);
		myAcpg.enterCreateEmailAddress("am91@gmail.com");
		logger.info("Email address entered in create account section.");

		myAcpg.clickSubmitCreate();

		logger.info("clicked on create an account button");

		accountCreationDetails accCreationPg = new accountCreationDetails(driver);

		accCreationPg.selectTitleMrs();
		accCreationPg.enterCustomerFirstName("sam");
		accCreationPg.enterCustomerLastName("mane");
		accCreationPg.enterPassword("am9191");
		accCreationPg.enterAddressFirstName("sam");
		accCreationPg.enterAddressLastName("mane");
		accCreationPg.enterAddress("18/8 worli road");

		accCreationPg.enterCity("pune");
		accCreationPg.selectState("Alabama");

		accCreationPg.enterPostcode("00000");
		accCreationPg.selectCountry("United States");
		accCreationPg.enterMobilePhone("9999999999");
		accCreationPg.enterAlias("Home");

		logger.info("entered user details on account creation page.");

		accCreationPg.clickOnRegister();
		logger.info("clicked on Register button");

		registeredUserAccount regUser = new registeredUserAccount(driver);
		String userName = regUser.getUserName();

		Assert.assertEquals("Prachi Gupta", userName);

		logger.info("***************TestCase Verify Registration and Login ends*****************"); 

	}
	
	@Test(dataProvider = "LoginDataProvider")
	
	public void VerifyLogin(String userEmail, String userPwd, String expectedUsername) throws IOException 
	{

		logger.info("***************TestCase VerifyLogin starts*****************"); 


		IndexPage pg = new IndexPage(driver);

		pg.clickOnSignIn();
		logger.info("Clicked on sign in link");

		MyAccount myAcpg = new MyAccount(driver);

		myAcpg.enterEmailAddress(userEmail);
		logger.info("Entered email address");
		
		myAcpg.enterPassword(userPwd);
		logger.info("Entered password");

		myAcpg.clickSignIn();
		logger.info("Clicked on sign in link..");

		registeredUserAccount regUser = new registeredUserAccount(driver);
		String userName = regUser.getUserName();
		
		
		if(userName.equals(expectedUsername))
		{
			logger.info("VerifyLogin - Passed");
			Assert.assertTrue(true);
			
			regUser.clickOnSignOut();
			
		}
		else
		{
			logger.info("VerifyLogin - Failed");
			captureScreenShot(driver,"VerifyLogin");
			Assert.assertTrue(false);

		} 
		
		 
		logger.info("***************TestCase Verify login ends*****************"); 


	}

	@DataProvider(name = "LoginDataProvider")
	public String[][] LoginDataProvider()
	{
		//System.out.println(System.getProperty("user.dir"));
		String fileName = System.getProperty("user.dir") + "\\TestData\\MyStoreTestData.xlsx";
															 
		int ttlRows = ReadExcelFile.getRowCount(fileName, "LoginTestData");
		int ttlColumns = ReadExcelFile.getColCount(fileName, "LoginTestData");
	

		String data[][]=new String[ttlRows-1][ttlColumns];

		for(int i=1;i<ttlRows;i++)//rows =1,2
		{
			for(int j=0;j<ttlColumns;j++)//col=0, 1,2
			{

				data[i-1][j]=ReadExcelFile.getCellValue(fileName,"LoginTestData", i,j);
			}

		}
		return data;
	}

}
