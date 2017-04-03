# Steps on Creating a Delivery Pipeline:
#### Create a Build Stage
1. Click the **ADD STAGE** button. Change the stage name **MyStage** to **Build Stage**.
2. On the **INPUT** tab, set the following values:
    - **Input Type:**	SCM Repository	
    -  **Git URL:** https://github.com/<username>/blmx_mysql.git	
    - **Branch:**	master	
    - **Stage Trigger:**	Run jobs whenever a change is pushed to Git	
3. On the **JOBS** tab, click the **ADD JOB** link and select **Build**. Change the job name **Build to Gradle Assemble**. Set the following values:
    - **Builder Type**	Gradle	
    - **Build Shell Command:** *#!/bin/bash*
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*gradle assemble*
    - **Stop running this stage if this job fails:**	checked	
4. Click the SAVE button.

### Create a Test Stage
1. Click the **ADD STAGE** button. Change the stage name **MyStage** to **Test Stage**.
2. On the **INPUT** tab, set the following values:
    - **Input Type:**	Build Artifacts	
    - **Stage:**	Build Stage	
    - **Job:**	Gradle Assemble	
    - **Stage Trigger:**	Run jobs when the previous stage is completed	
3. On the **JOBS** tab, click the **ADD JOB** link and select **Test**. Change the job name **Test** to **JUnit Test through Gradle**. Set the following values:
    - **Tester Type:**	Simple	
    - **Test Command:** *#!/bin/bash*
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*gradle test*	
    - **Stop running this stage if this job fails:**	checked	
4. Click the SAVE button.

### Create a Deploy Stage
1. Click the **ADD STAGE** button. Change the stage name **MyStage** to **Dev Deploy Stage**.

>Unlike the build and test stages which are named Build Stage and Test Stage, respectively, the name of the deploy stage you are about to create is Dev Deploy Stage to denote that that the application will be deployed in the dev space of your Bluemix account.
2. On the **INPUT** tab, set the following values:
    - **Input Type:**	Build Artifacts	
    - **Stage:**	Build Stage	
    - **Job:**	Gradle Assemble	
    - **Stage Trigger:**	Run jobs when the previous stage is completed	
3. On the **JOBS** tab, click the **ADD JOB** link and select **Deploy**. Change the job name from **Deploy** to **Cloud Foundry Push to Dev Space**. Set the following values:
    - **Deployer Type:**	Cloud Foundry	
    - **Target:**	IBM Bluemix US South - https://api.ng.bluemix.net	
    - **Organization:**	you may leave the default selection	
    - **Space:**	dev	
    - **Application Name:**	blank	
    - **Deploy Script:**	*#!/bin/bash*
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;*cf push <your desired name> -m 256M -p build/libs/FinalProject.war*	
    - **Stop running this stage:** if this job fails	checked	
>IMPORTANT: In the cf push command, make sure to change <your desired name> to the name that you may desire for the app.
4. Click the **SAVE** button.

You have successfully created a delivery pipeline.
