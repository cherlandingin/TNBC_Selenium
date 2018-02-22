package com.acn.scenarios;

import java.util.concurrent.TimeUnit;

import com.acn.testbase.TestBase;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class GlobalStepDef extends TestBase {
	/****************************************************************************************
	 * Step Definition: Given browser <browsertype> is open and nvigates to <URL>
	 * 
	 * @param sBrowserType
	 *            - the type of browser to be used e.g. IE, Chrome, Mozilla
	 * @param sUrl
	 *            - the url to be used
	 * @throws Exception	
	 ****************************************************************************************/
	@Given("^for TC Name \"([^\"]*)\", browser \"([^\"]*)\" is open and navigates to \"([^\"]*)\"$")
	public void openBrowserUrl(String sTCName, String sBrowserType, String sUrl) throws Exception {
		// System.out.println("Login");
		TCName = sTCName;

		openBrowser(sBrowserType);

		// check if it has semicolon/delimiter
		if (sUrl.contains(";")) {
			String[] parts = sUrl.split(";");
			sUrl = parts[0];
		}

		driver.get(sUrl);

		TimeUnit.SECONDS.sleep(5);

		ReportResults("PASS", "Succesfully Navigates and Opens the Browser", true);
	}

	/****************************************************************************************
	 * Step Definition: And enter <param>
	 * 
	 * @param sValObjId
	 *            - the value to be entered in the text and the xpath delimited by
	 *            semicolon
	 * @throws Exception
	 ****************************************************************************************/
	@And("^enter \"([^\"]*)\"$")
	public void enterValue(String sValObjId) throws Exception {

		if (sValObjId.trim().equals("")) {
			System.out.println("Enter Text Value: No Parameters defined");
		} else {

			// RegistrationPage oRegistration =
			// PageFactory.initElements(this.driver, RegistrationPage.class);
			this.enterText(sValObjId);
		}
	}

	/****************************************************************************************
	 * Step Definition: And select <param>
	 * 
	 * @param sValObjId
	 *            - the value to be selected in the Radio Button and the xpath
	 *            delimited by semicolon
	 * @throws Exception
	 ****************************************************************************************/
	@And("^select \"([^\"]*)\"$")
	public void selectRadioButton(String sValObjId) throws Exception {
		if (sValObjId.trim().equals("")) {
			System.out.println("Select Radio Button: No Parameters defined");
		} else {

			this.selectRadioBtn(sValObjId);
		}
	}

	/****************************************************************************************
	 * Step Definition: And check <param>
	 * 
	 * @param sValObjId
	 *            - the value to be checked in the option and the xpath delimited by
	 *            semicolon
	 * @throws Exception
	 ****************************************************************************************/
	@And("^check \"([^\"]*)\"$")
	public void checkBox(String sValObjId) throws Exception {
		if (sValObjId.trim().equals("")) {
			System.out.println("Check box: No Parameters defined");
		} else {

			this.checkBox(sValObjId);

		}
	}

	/****************************************************************************************
	 * Step Definition: And choose <param>
	 * 
	 * @param sValObjId
	 *            - the value to be checked in the option and the xpath delimited by
	 *            semicolon
	 * @throws Exception
	 ****************************************************************************************/
	@And("^choose \"([^\"]*)\"$")
	public void chooseDrop_Down(String sObj) throws Exception {
		if (sObj.trim().equals("")) {
			System.out.println("No Parameters defined");
		} else {
			this.chooseDropDown(sObj);
		}
	}

	/****************************************************************************************
	 * Step Definition: Then click <param>
	 * 
	 * @param sValObjId
	 *            - the object xpath to be clicked
	 * @throws Exception
	 ****************************************************************************************/
	@Then("click \"([^\"]*)\"$")
	public void clickBtn(String sObjId) throws Exception {
		System.out.println("Click");

		if (this.clickButton(sObjId)) {
			ReportResults("PASS", "Succesfully clicked the object: " + sObjId, true);
		} else {
			ReportResults("FAIL", "Did not succesfully clicked the object: " + sObjId, true);
		}

	}

	/****************************************************************************************
	 * Step Definition: Then validateURL <param>
	 * 
	 * @param sObjId
	 *            - the url expected to be displayed in the applications address bar
	 * @throws Exception
	 ****************************************************************************************/
	@Then("validateURL \"([^\"]*)\"$")
	public void validateLinkURL(String sObjId) throws Exception {
		if (sObjId.trim().equals("")) {
			System.out.println("No Parameters defined for Validate Url Link");
		} else {
			boolean bOutcome = this.validateUrl(sObjId);

			if (bOutcome) {
				ReportResults("PASS", "Succesfully navigated to the expected URL", true);
			} else {
				ReportResults("FAIL", "Did not succesfully navigated to the expected URL.", true);
			}

		}

	}

	/****************************************************************************************
	 * Step Definition: Then verify visibility <param>
	 * 
	 * @param sObjId
	 *            - boolean: true if object is expected to be visible, false if not
	 *            and the xpath of the object.
	 * @throws Exception
	 ****************************************************************************************/
	@Then("^verify visibility \"([^\"]*)\"$")
	public void verifyifObjectisVisible(String sobjId) throws Exception {
		isElementVisible(sobjId);
	}

	/****************************************************************************************
	 * Step Definition: Then verify visibility <param>
	 * 
	 * @param sObjId
	 *            - boolean: true if object is expected to be visible, false if not
	 *            and the xpath of the object.
	 * @throws Exception
	 ****************************************************************************************/
	@Then("^verify text \"([^\"]*)\"$")
	public void verifyTextofanObject(String sobjId) throws Exception {
		if (verifyText(sobjId)) {
			ReportResults("PASS", "Message is displayed as expected: " + sobjId, true);
		} else {
			ReportResults("FAIL", "Message is not displayed. " + sobjId, true);
		}

	}

	/****************************************************************************************
	 * Step Definition: And navigate <Menu>>><Submenu>
	 * 
	 * @param sObjId
	 *            - the xpath of the Menu separated by double Greater than sign
	 * @throws Exception
	 ****************************************************************************************/
	@And("^navigate \"([^\"]*)\"$")
	public void navigateToMenu(String sObj) throws Exception {
		if (sObj.trim().equals("")) {
			System.out.println("Navigate Menu: No Parameters defined");
		} else {

			boolean bOutcome = this.navigateMenu(sObj);
			if (bOutcome) {
				System.out.println("Successfully navigated");
				ReportResults("PASS", "Succesfully navigated to object: " + sObj, true);
			} else {
				System.out.println("Unsuccessfully navigated");
				ReportResults("FAIL", "Unsuccessfully navigated to object: " + sObj, true);
			}

		}
	}

	/****************************************************************************************
	 * Step Definition: And verify dropdown contents <param>
	 * 
	 * @param sObjId
	 *            - the xpath of the Menu separated by double Greater than sign
	 * @throws Exception
	 ****************************************************************************************/
	@Then("^verify dropdown contents \"([^\"]*)\"$")
	public void verifyDrowdownContents(String sobjId) throws Exception {
		// bool strResult = this.verifyDrowdownContents(sobjId);
		if (this.verifyDrowdownContents1(sobjId)) {
			System.out.println("The value is in the dropdown");
			ReportResults("PASS", "The value is in the dropdown: " + sobjId, true);
		}
	}

	/****************************************************************************************
	 * Step Definition: And go to URL <Address URL>
	 * 
	 * @param sObjId
	 *            - the address URL you want to navigate to
	 * @throws Exception
	 ****************************************************************************************/
	@And("^go to URL \"([^\"]*)\"$")
	public void gotoURL(String sUrl) throws Exception {
		// check if it has semicolon/delimiter
		if (sUrl.contains(";")) {
			String[] parts = sUrl.split(";");
			sUrl = parts[0];
		}

		driver.get(sUrl);
		TimeUnit.SECONDS.sleep(5);
	}

	/****************************************************************************************
	 * Step Definition: Then double click <param>
	 * 
	 * @param sValObjId
	 *            - the object xpath to be clicked
	 * @throws Exception
	 ****************************************************************************************/
	@Then("double click \"([^\"]*)\"$")
	public void dblclickBtn(String sObjId) throws Exception {
		System.out.println("Double Click");

		if (this.doubleclickButton(sObjId)) {
			ReportResults("PASS", "Succesfully double clicked the object: " + sObjId, true);
		} else {
			ReportResults("FAIL", "Did not succesfully double clicked the object: " + sObjId, true);
		}

	}

	/****************************************************************************************
	 * Step Definition: Then validate URL <param>
	 * 
	 * @param sValObjId
	 *            - the url will be validated
	 * @throws Exception
	 ****************************************************************************************/
	@And("validate URL \"([^\"]*)\"$")
	public void validate_Url(String sObjId) throws Exception {
		this.validateUrl(sObjId);
	}

	/****************************************************************************************
	 * Step Definition: Then custom java click <param>
	 * 
	 * @param sValObjId
	 *            - the object xpath to be clicked
	 * @throws Exception
	 ****************************************************************************************/
	// RJDG 061517
	@Then("custom java click \"([^\"]*)\"$")
	public void customClickBtn(String sObjId) throws Exception {
		System.out.println("Click");

		if (this.customClickButton(sObjId)) {
			ReportResults("PASS", "Succesfully clicked the object: " + sObjId, true);
		} else {
			ReportResults("FAIL", "Did not succesfully clicked the object: " + sObjId, true);
		}

	}

	/****************************************************************************************
	 * Step Definition: Then manual <param>
	 * 
	 * @param sValObjId
	 *            - the parameter contains the detail
	 * @throws Exception
	 ****************************************************************************************/
	@Then("manual \"([^\"]*)\"$")
	public void manuals(String sObjId) throws Exception {
		System.out.println("Manual Intervention");
		this.manual(sObjId);
	}

	/****************************************************************************************
	 * Step Definition: Then click and pop up <param>
	 * 
	 * @param sValObjId
	 *            - the object xpath to be clicked and accept the pop up alert
	 * @throws Exception
	 ****************************************************************************************/
	@Then("click and pop up accept \"([^\"]*)\"$")
	public void clickAndAccept(String sObjId) throws Exception {
		System.out.println("Click and Accept Pop Up");

		if (this.acceptAlert(sObjId)) {
			ReportResults("PASS", "Succesfully clicked the object: " + sObjId, true);
		} else {
			ReportResults("FAIL", "Did not succesfully clicked the object: " + sObjId, true);
		}
	}

	/****************************************************************************************
	 * Step Definition: Then click and pop up dismiss <param>
	 * 
	 * @param sValObjId
	 *            - the object xpath to be clicked and dismiss the pop up alert
	 * @throws Exception
	 ****************************************************************************************/
	@Then("click and pop up dismiss \"([^\"]*)\"$")
	public void clickAndDismiss(String sObjId) throws Exception {
		System.out.println("Click and Dismiss Pop up");

		if (this.dismissAlert(sObjId)) {
			ReportResults("PASS", "Succesfully clicked the object: " + sObjId, true);
		} else {
			ReportResults("FAIL", "Did not succesfully clicked the object: " + sObjId, true);
		}

	}

	/****************************************************************************************
	 * Step Definition: Then evaluate if enabled <param>
	 * 
	 * @param sValObjId
	 *            - the object will validate if it is enable/disabled
	 * @throws Exception
	 ****************************************************************************************/
	// RJDG 06/19/17
	@Then("evaluate if enabled \"([^\"]*)\"$")
	public void isEnabled(String sObjId) throws Exception {
		this.isEnable(sObjId);
	}

	/****************************************************************************************
	 * Step Definition: Then evaluate if selected <param>
	 * 
	 * @param sValObjId
	 *            - the object will validate if it is selected or not
	 * @throws Exception
	 ****************************************************************************************/
	@Then("evaluate if selected \"([^\"]*)\"$")
	public void isSelected(String sObjId) throws Exception {
		this.isSelect(sObjId);
	}

	/****************************************************************************************
	 * Step Definition: Then evaluate if the attribute is matched <param>
	 * 
	 * @param sValObjId
	 *            - the object will check the attribute and check if the value is
	 *            matched
	 * @throws Exception
	 ****************************************************************************************/
	@Then("evaluate if the attribute is matched \"([^\"]*)\"$")
	public void getAttributes(String sObjId) throws Exception {
		this.getAttribute(sObjId);
	}

	/****************************************************************************************
	 * Step Definition: Then evaluate if it is auto select <param>
	 * 
	 * @param sValObjId
	 *            - if the object do have already a selected value
	 * @throws Exception
	 ****************************************************************************************/
	@Then("evaluate if it is auto select \"([^\"]*)\"$")
	public void ifDDownAutoSelect(String sObjId) throws Exception {
		this.ifDropDownAutoSelect(sObjId);
	}

	/****************************************************************************************
	 * Step Definition: Then verify all dropdown contents <param>
	 * 
	 * @param sValObjId
	 *            - the object will be evaluated if the expected data are in the
	 *            listbox
	 * @throws Exception
	 ****************************************************************************************/
	@Then("^verify all dropdown contents \"([^\"]*)\"$")
	public void verifyAllDrowdownContents1(String sobjId) throws Exception {
		this.verifyAllDrowdownContents(sobjId);
	}

	/****************************************************************************************
	 * Step Definition: Then verify dropdown not contains <param>
	 * 
	 * @param sValObjId
	 *            - the object will be evaluated if the data are not in the listbox
	 * @throws Exception
	 ****************************************************************************************/
	@Then("^verify dropdown not contains \"([^\"]*)\"$")
	public void verifyDrowdownNotContain(String sobjId) throws Exception {
		if (this.verifyDrowdownNotContains(sobjId)) {
			System.out.println("The value is not in the dropdown");
			ReportResults("PASS", "The value is not in the dropdown: " + sobjId, true);
		}
	}

	/****************************************************************************************
	 * Step Definition: Then wait <param>
	 * 
	 * @param sValObjId
	 *            - wait for the specific seconds
	 * @throws Exception
	 ****************************************************************************************/
	@Then("^wait \"([^\"]*)\"$")
	public void wait(String sobjId) throws Exception {
		this.waitTime(sobjId);
	}

	/****************************************************************************************
	 * Step Definition: Then compare text <param>
	 * 
	 * @param sValObjId
	 *            - the object will be evaluated if the expected data is matched
	 *            with the expected value
	 * @throws Exception
	 ****************************************************************************************/
	@Then("^compare text \"([^\"]*)\"$")
	public void compareText1(String sobjId) throws Exception {
		this.compareText(sobjId);
	}

	/****************************************************************************************
	 * Step Definition: Then close browser <param>
	 * 
	 * @param sValObjId
	 *            - the object will be evaluated if the expected data is matched
	 *            with the expected value
	 * @throws Exception
	 ****************************************************************************************/
	@Then("^close browser")
	public void closeBrowser1() throws Exception {
		closeBrowser();
	}

	/****************************************************************************************
	 * Step Definition: And clear <param>
	 * 
	 * @param sValObjId
	 *            - the text to be cleared and the xpath delimited by semicolon
	 * @throws Exception
	 ****************************************************************************************/
	@And("^clear \"([^\"]*)\"$")
	public void clear(String sValObjId) throws Exception {

		if (sValObjId.trim().equals("")) {
			System.out.println("Clear Text Value: No Parameters defined");
		} else {

			// RegistrationPage oRegistration =
			// PageFactory.initElements(this.driver, RegistrationPage.class);
			clearText(sValObjId);
		}
	}

	/****************************************************************************************
	 * SFDC Step Definition: And enter dropdown <param>
	 * 
	 * @param sValObjId
	 *            - the value to be entered in the text and the xpath delimited by
	 *            semicolon
	 * @throws Exception
	 ****************************************************************************************/
	@And("^enter dropdown \"([^\"]*)\"$")
	public void enterdropdown(String sValObjId) throws Exception {

		if (sValObjId.trim().equals("")) {
			System.out.println("Enter Dropdown Value: No Parameters defined");
		} else {

			// RegistrationPage oRegistration =
			// PageFactory.initElements(this.driver, RegistrationPage.class);
			enterDropDown(sValObjId);
		}
	}

	/****************************************************************************************
	 * FOR MERALCO Step Definition: And dropdown none <param>
	 * 
	 * @param sValObjId
	 *            - the value to be entered in the text and the xpath delimited by
	 *            semicolon
	 * @throws Exception
	 ****************************************************************************************/
	@And("^dropdown none \"([^\"]*)\"$")
	public void ddNone(String sValObjId) throws Exception {

		if (sValObjId.trim().equals(""))
			System.out.println("Enter Dropdown Value: No Parameters defined");
		else
			dropdownNone(sValObjId);
	}

	/****************************************************************************************
	 * Step Definition: And file upload <param>
	 * 
	 * @param sValObjId
	 *            - the value to be entered in the text and the xpath delimited by
	 *            semicolon
	 * @throws Exception
	 ****************************************************************************************/
	@And("^file upload \"([^\"]*)\"$")
	public void fileUpload(String sValObjId) throws Exception {

		if (sValObjId.trim().equals(""))
			System.out.println("File Upload: No Parameters defined");
		else
			fileupload(sValObjId);
	}

	/****************************************************************************************
	 * FOR MERALCO Step Definition: And enter <param>
	 * 
	 * @param sValObjId
	 *            - the value to be entered in the text and the xpath delimited by
	 *            semicolon
	 * @throws Exception
	 ****************************************************************************************/
	@And("^verify format \"([^\"]*)\"$")
	public void verifyFormat(String sValObjId) throws Exception {

		if (sValObjId.trim().equals(""))
			System.out.println("verify format: No Parameters defined");
		else
			verifyformat(sValObjId);
	}

	/****************************************************************************************
	 * FOR MERALCO Step Definition: Then check if field is populated <param>
	 * 
	 * @param sobjId
	 *            - the object will be evaluated if the field is not empty
	 * @throws Exception
	 ****************************************************************************************/
	@Then("^check text \"([^\"]*)\"$")
	public void check(String sobjId) throws Exception {
		this.checkText(sobjId);
	}

	/****************************************************************************************
	 * FOR MERALCO Step Definition: And enter <param>
	 * 
	 * @param sValObjId
	 *            - the key label and value to be stored together with the xpath
	 *            delimited by semicolon
	 * @throws Exception
	 ****************************************************************************************/
	@And("^store value \"([^\"]*)\"$")
	public void storeValue(String sValObjId) throws Exception {

		if (sValObjId.trim().equals(""))
			System.out.println("store value: No Parameters defined");
		else
			storevalue(sValObjId);
	}

	/****************************************************************************************
	 * FOR MERALCO Step Definition: And enter <param>
	 * 
	 * @param sValObjId
	 *            - the number of times the action will be executed the xpath
	 *            delimited by semicolon
	 * @throws Exception
	 ****************************************************************************************/
	@And("^scroll up \"([^\"]*)\"$")
	public void scrollUp(String sValObjId) throws Exception {

		if (sValObjId.trim().equals(""))
			System.out.println("scroll up: No Parameters defined");
		else
			scrollup(sValObjId);
	}

	/****************************************************************************************
	 * FOR MERALCO Step Definition: And enter <param>
	 * 
	 * @param sValObjId
	 *            - the number of times the action will be executed and the xpath
	 *            delimited by semicolon
	 * @throws Exception
	 ****************************************************************************************/
	@And("^scroll down \"([^\"]*)\"$")
	public void scrollDown(String sValObjId) throws Exception {

		if (sValObjId.trim().equals(""))
			System.out.println("scroll down: No Parameters defined");
		else
			scrolldown(sValObjId);
	}

	/****************************************************************************************
	 * FOR MERALCO Step Definition: And enter <param>
	 * 
	 * @param sValObjId
	 *            - the value to be entered in the text and the xpath delimited by
	 *            semicolon
	 * @throws Exception
	 ****************************************************************************************/
	@And("^custom enter \"([^\"]*)\"$")
	public void customEnterText(String sValObjId) throws Exception {

		if (sValObjId.trim().equals(""))
			System.out.println("custom enter: No Parameters defined");
		else
			customenterText(sValObjId);
	}

	/****************************************************************************************
	 * Step Definition: And hit Enter key
	 * 
	 * @param
	 * @throws Exception
	 ****************************************************************************************/
	@And("^enter key")
	public void enter() throws Exception {
		hitSelect();
	}

	/****************************************************************************************
	 * FOR MERALCO Step Definition: And enter <param>
	 * 
	 * @param sValObjId
	 *            - the key to be used and the action to be done together with the xpath delimited by
	 *            semicolon
	 * @throws Exception
	 ****************************************************************************************/
	@And("^use stored value \"([^\"]*)\"$")
	public void useStoredValue(String sValObjId) throws Exception {

		if (sValObjId.trim().equals(""))
			System.out.println("use stored value: No Parameters defined");
		else
			usestoredvalue(sValObjId);
	}
	
	/****************************************************************************************
	 * FOR MERALCO Step Definition: And enter <param>
	 * 
	 * @param sValObjId
	 *            - the key to be used and the action to be done 
	 * @throws Exception
	 ****************************************************************************************/
	@And("^refresh")
	public void Refresh() throws Exception{
		refresh();
	}
	
	/****************************************************************************************
	 * FOR MERALCO Step Definition: And enter <param>
	 * 
	 * @param sValObjId
	 *            - the number of times the action will be executed and the xpath
	 *            delimited by semicolon
	 * @throws Exception
	 ****************************************************************************************/
	@And("^arrow down \"([^\"]*)\"$")
	public void arrowkeyDown(String sValObjID) throws Exception 
	{
		if (sValObjID.trim().equals(""))
			System.out.println("arrow down: No Parameters defined");
		else
			arrowKeyDown(sValObjID);

	}

	@And("^arrow up \"([^\"]*)\"$")
	public void arrowkeyUp(String sValObjID) throws Exception 
	{
		if (sValObjID.trim().equals(""))
			System.out.println("arrow up: No Parameters defined");
		else
			arrowKeyDown(sValObjID);

	}

	/****************************************************************************************
	 * FOR MERALCO Step Definition: And enter <param>
	 * 
	 * @param sValObjId
	 *            - the number of times the action will be executed and the xpath
	 *            delimited by semicolon
	 * @throws Exception 
	 ***********************************
	 ******************************************************/
	@And("^down \"([^\"]*)\"$")
	public void arrowDown(String sValObjID) throws Exception 
	{
		if (sValObjID.trim().equals(""))
			System.out.println("arrow down: No Parameters defined");
		else
			arrowKeyDownDropDown(sValObjID);

	}
	/****************************************************************************************
	 * Step Definition: And hit tab key
	 * 
	 * @param
	 * @throws Exception
	 ****************************************************************************************/
	@And("^tab")
	public void tab() throws Exception {
		hitTab();
	} 
	
	/****************************************************************************************
	 * Step Definition: And hit tab key
	 * 
	 * @param
	 * @throws Exception
	 ****************************************************************************************/
	@And("^verify customize dropdown \"([^\"]*)\"$")
	public void verifyCustomizeDD(String sValObjID) throws Exception {
		verifyCustomizeDropdown(sValObjID);
	} 
	
	/****************************************************************************************
	 * Step Definition: Then wait <param>
	 * 
	 * @param sValObjId
	 *            - wait for the specific seconds
	 * @throws Exception
	 ****************************************************************************************/
	@Then("^switch tab")
	public void switchTab() throws Exception {
		this.altTab();
	}
}
