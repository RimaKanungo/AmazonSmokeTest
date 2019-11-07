AmazonSmokeTest- I have automated the testcases by using selenium webdriver and java programming language and implemented hybrid framework (combination of both data driven and keyword framework). 
I have used a Maven for dependency management.

All the testcases and testscripts are under : AmazonSmokeTest/src/test/java/

Followings are the details of each packages:

config : This package contains all the configuration details, keyword details, testdata, logs.

a. application.log : This log file will record each and every step implemented in details and shows the log.

b. config.properties : This file stores test sites urls, enviornment, version, testbrowser, ip address, remote machine information.

c. controller.xls : This file contains the TestSuite Name, TestCases, Keywords, RunMode. (Keyword driven)

d. testData.xls : This file stores all the testdata to be implemented in the test automation. (Data Driven)

datatable :

a. XlsReader.java : XlsReader is a java class which is responsible for all the communication with the .xls file.

reports :

a. ReportUtil.java : ReportUtil is a java class which is responsible for Report generation.

testcases:

a. AmazonTest.java : AmazonTest is a java class in which contains all the loactors, functions that is impelmented in test automation.

testscripts:

a. DriverScript.java : This is the main/fundamental file, that controls the execution of the test automation. The execution starts from this file.

b. FunctionLibrary.java : FunctionLibrary is a java class which contains basic webdriver methods that is implemented in test automation.

c. Keyword.java : Keyword is a java class that contains all the keywords hat is implemented in test automation.

d. AmazonLibrary.java : AmazonLibrary is a java class that contains all the application based functions.

6.util:

   a. SendMail.java : Contains functions used for zipping the reports and sending mail to the recipients.
   
   b. TestUtil.java : Capturing screenshot, storing screenshot in the mentioned path, making zip of the reports,current date/time
       generation functions are defined here.


