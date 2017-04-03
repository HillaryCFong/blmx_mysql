# LBYCLDS Final Project: Translator Service
### Overview
This repository contains a java web application that lets a user add feedback to corresponding translations to a certain English word or phrase.

  - Select a translation from English to Arabic, French, Portuguese, or Spanish.
  - Input the English word or phrase and see the translation
  - Give feedback to the translation (thumbs up or thumbs down)


### Tech

The technologies used in building this application are:
* [ClearDB MySQL Database] - Highly available MySQL for Apps
* [IBM Watson Language Translator] - Translate text from one language to another for specific domains.

### Installation

This application uses the [IBM Bluemix Devops Services Delivery Pipeline](https://hub.jazz.net)  to run. To deploy the application, the following steps need to be done:

1. Open a web browser tab and login to [GitHub](https://github.com/)
2. Using the same tab, go to [this GitHub repository](https://github.com/HillaryCFong/blmx_mysql).
3. Fork the repository by clicking the **Fork** button.
![alt text](https://sammyk.s3.amazonaws.com/blog/images/2014-05-28/fork.png)
4. After forking the repository, open a command prompt and clone the repository to your desired location in the local computer by and entering the command
*git clone https://github.com/<username>/blmx_mysql*
5. In the command prompt, change to the home directory by entering the command *cd blmx_mysql*
>Make sure that Gradle is correctly installed in your computer. If not, follow the instructions on [this page](https://www.javacodegeeks.com/2013/04/how-to-install-gradle-2.html) to install Gradle in your local computer.
7. In the same command prompt, enter the command *gradle build*.
8. Open another web browser tab and login to [Bluemix DevOps](https://hub.jazz.net).
9. Click **CREATE PROJECT**.
10. The name of the project may depend on your liking. For now, this project will be named **blmx_mysql**.
11. Click **Link to an existing GitHub repository**.
12. Select the repository *<username>/blmx_mysql*.
13. Ensure the following options are chosen:
**Private Project:**                    checked	
**Add features for Scrum development:**	checked	
**Make this a Bluemix Project:**	    checked	
**Region:**	                            IBM Bluemix US South	
**Organization:**	                    you may leave the default selection	
**Space:**	                            dev
14. Click the **CREATE** button and wait for your project to be created.
15. Click the **EDIT CODE** button to be redirected to Bluemix DevOps' editor.
16. Click (open in another browser tab) the Git Repository icon found on the left side of the screen
17. On the new tab that was opened, On the Working Directory section (right side of the page) Set the following values:
**Select All:**	checked	
**Commit message:**	files created when Bluemix DevOps project was created	
18. Click the **Commit** button.
19. After the **Commit** is done,  Click the **Push** button.
20. Go back to the GitHub page where the repository was forked and verify that .cfignore and launchConfigurations are added.
21. On the tab that was opened in number 16,  Click (open in another browser tab) the BUILD & DEPLOY button to create the delivery pipeline
22. Follow the instructions listed on [this page](https://pong-pantola.github.io/devops-delivery-pipeline/) and execute the instructions listed on the tab that was opened in the previous step.



[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)
   [IBM Watson Language Translator]: <https://console.ng.bluemix.net/catalog/services/language-translator?taxonomyNavigation=cf-apps>
   [ClearDB MySQL Database]: <https://console.ng.bluemix.net/catalog/services/cleardb-mysql-database?taxonomyNavigation=cf-apps>