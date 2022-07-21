package com.Mystore.Testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Mystore.pageobject.IndexPage;
import com.Mystore.pageobject.MyAccount;
import com.Mystore.pageobject.accountCreationDetails;
import com.Mystore.pageobject.registeredUserAccount;

public class TC_MyAccountPageTest extends BaseClass{
	
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
		accCreationPg.enterAddress("pune");

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

		Assert.assertEquals("sam mane", userName);

		logger.info("***************TestCase Verify Registration and Login ends*****************"); 

	}

	@Test(enabled = true)
	public void VerifyLogin() throws IOException 
	{

		logger.info("***************TestCase Verify Login starts*****************"); 

		IndexPage pg = new IndexPage(driver);

		pg.clickOnSignIn();
		logger.info("Clicked on sign in link");

		MyAccount myAcpg = new MyAccount(driver);

		myAcpg.enterEmailAddress("am91@gmail.com");
		logger.info("Entered email address");

		myAcpg.enterPassword("am9191");
		logger.info("Entered password");

		myAcpg.clickSignIn();
		logger.info("Clicked on sign in link..");


		registeredUserAccount regUser = new registeredUserAccount(driver);
		String userName = regUser.getUserName();


		if(userName.equals("sam mane"))
		{
			logger.info("VerifyLogin - Passed");
			//regUser.clickOnSignOut();
			Assert.assertTrue(true);
		}
		else
		{
			logger.info("VerifyLogin - Failed");
			captureScreenShot(driver,"VerifyLogin");
			Assert.assertTrue(false);

		}

		logger.info("***************TestCase Verify Login ends*****************"); 


	}


	@Test(enabled = true)
	public void VerifySignOut() throws IOException 
	{

		logger.info("***************TestCase Verify Sign out starts*****************"); 

		IndexPage pg = new IndexPage(driver);

		pg.clickOnSignIn();
		logger.info("Clicked on sign in link");

		MyAccount myAcpg = new MyAccount(driver);

		myAcpg.enterEmailAddress("am91@gmail.com");
		logger.info("Entered email address");

		myAcpg.enterPassword("am9191");
		logger.info("Entered password");

		myAcpg.clickSignIn();
		logger.info("Clicked on sign in link..");


		registeredUserAccount regUser = new registeredUserAccount(driver);
		regUser.clickOnSignOut();

		if(pg.getPageTitle().equals("Login - My Store"))
		{
			logger.info("VerifySignOut - Passed");
			Assert.assertTrue(true);
		}

		else
		{
			logger.info("VerifySignOut - Failed");
			captureScreenShot(driver,"VerifySignOut");
			Assert.assertTrue(false);
		}

	
		logger.info("***************TestCase Verify Sign out ends*****************"); 

	}


}
