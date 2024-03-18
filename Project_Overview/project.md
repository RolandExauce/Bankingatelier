# Final Project for JAVA BASICS and ADVANCED COURSE at Atelier04

# Project/application name: @BankingAtelier

project description: users can log in with a username and a password and will be forwarded
to a dashboard where they can view their money balance. They can withdraw and deposit money.

# Requirements that must be implemented:
* Integration of a SQL database for the data 
* authentication  and authorization (partial integration)
   => authentication: registration and login process
   => authorization: retrieval and withdrawal of the user's own balance


# Requirements that could be implemented but will likely not be due to a lack of time and high difficulty:
* manage user session and logout (expert: refresh session)
* Password reset
* Lock the user for one hour after providing false credentials 5 times in a row, 
  after 1h lock, 3 more chances => on fail again, password can only be restored by an Admin user
* route to Admin dashboard with CRUD Operations if logged in as an Admin
* transactions with other users 

# ##################################################################################################################

# Course of project:

1. Introduction to Spring, Spring bring project created with Alex, authentication implemented with MySQL
2. Redirect issues fixed, render html view issues fixed and other bugs

3. Independent work at home:
    * issues that occurred: Failing to start MySQL with XAMPP => solution: simply switching to local MySQL database 
    * Issues finding modules in Intellij, recreated project, fixed some dependency issues with a friend as well

4. Building login page, register page, dashboard page
    * fixes/issues: applying css not working => due to security config ?! (low priority => moved to next steps) 

5. Create methods for Endpoints to use for withdrawing/adding user balance


6. Reviewing step 4 bugs, post requests with thymeleaf => issue with displaying logged in user,
    fixed with Ajax, issue remaining: css not applying, altough previously applied !? GUI not updating balance
    after it's set, refresh is needed :( 

# ###############################################################################################################

* Execution date of Step 1 & 2: Mo 11.03 - Tue 12.03, time needed: approx. 6 hours

* Execution date of Step 3: Wed 13.03 - Thu 14.03, time needed: approx. 3 hours

* Execution date of Step 4: Wed 13.03 - Thu 16.03, time needed: approx. 6 hours, CSS applied, remark: sometimes not
  loading, css may be causing unnecessary overhead ? loading with javascript not working as well

* Execution date of Step 5: Thu 14.03, time needed: approx. 1.5 hours

* Execution date of Step 6: Sa 16.03 - Su 17.03, time needed: approx: 5 hours 
