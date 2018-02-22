Feature:Test Demo
Scenario Outline:Test Demo
Given for TC Name "<TCName>", browser "<BrowserType>" is open and navigates to "<URL>" 
Then enter "<txtUserName>"
Then enter "<txtPassword>"
Then click "<btnLogin>"
Then wait "<waitTime>"

Then choose "<drpPassengers>"
Then choose "<drpDepartFrom>"
Then choose "<drpOnMonth>"
Then choose "<drpOnDay>"
Then choose "<drpArrivingIn>"
Then choose "<drpReturningMonth>"
Then choose "<drpReturningDay>"
Then click "<rdoBussiness>"
Then click "<btnContinue>"
Then wait "<waitTime1>"
Then click "<btnContinue2>"

Then enter "<txtFName>"
Then enter "<txtLName>"
Then enter "<txtNum>"
Then click "<btnSecPurchase>"
Then wait "<waitTime>"
Then verify visibility "<SuccessMsg>"

Examples:
|BrowserType|TCName|URL|txtUserName|txtPassword|btnLogin|waitTime|drpPassengers|drpDepartFrom|drpOnMonth|drpOnDay|drpArrivingIn|drpReturningMonth|drpReturningDay|rdoBussiness|btnContinue|waitTime1|btnContinue2|txtFName|txtLName|txtNum|btnSecPurchase|waitTime|SuccessMsg|
|Chrome|Demo|http://newtours.demoaut.com/;null;URL|mercury;xpath>>//input[@name='userName'];txtUserName|mercury;xpath>>//input[@name='password'];txtPassword|click;xpath>>//input[@name='login'];btnLogin|5;null;waitTime|1;xpath>>/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[3]/td[2]/b/select;drpPassengers|London;xpath>>/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[4]/td[2]/select;drpDepartFrom|March;xpath>>/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[5]/td[2]/select[1];drpOnMonth|1;xpath>>/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[5]/td[2]/select[2];drpOnDay|New York;xpath>>/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[6]/td[2]/select;drpArrivingIn|April;xpath>>/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[7]/td[2]/select[1];drpReturningMonth|10;xpath>>/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[7]/td[2]/select[2];drpReturningDay|click;xpath>>/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[9]/td[2]/font/font/input[1];rdoBussiness|click;xpath>>/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[14]/td/input;btnContinue|5;null;waitTime1|click;xpath>>/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/p/input;btnContinue2|John;xpath>>/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[4]/td/table/tbody/tr[2]/td[1]/input;txtFName|Doe;xpath>>/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[4]/td/table/tbody/tr[2]/td[2]/input;txtLName|123456789;xpath>>/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[6]/td/table/tbody/tr[2]/td[2]/input;txtNum|click;xpath>>/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[23]/td/input;btnSecPurchase|5;null;waitTime|true;xpath>>/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p/font/b/font[2];SuccessMsg|