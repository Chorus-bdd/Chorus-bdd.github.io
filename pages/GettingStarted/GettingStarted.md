---
layout: page
title: Getting Started
section: Getting Started
sectionIndex: 10
---

To use Chorus, you must download and run the Chorus interpreter.   
This will parse the feature files containing your BDD tests, and execute them.

There are several ways to get and run the Chorus interpreter, depending on the needs of your project
  
* **Integrated with a Java project as a JUnit test suite:**   
  [Add Chorus as a maven/gradle dependency, or download it as a jar dependency](/pages/RunningChorus/RunningAsJUnitSuite)

* **As a Docker container in a Docker-enabled environment:**  
  [Use a Docker image downloadable from Docker hub](/pages/RunningChorus/RunningWithDocker) 
  
* **From the command line:**  
  [Download Chorus and install it as a standalone installable package](/pages/RunningChorus/RunningAsAStandaloneInstallable)



### How to write Chorus tests:

1. Write tests in plain English (as .feature files [following the standard Gherkin syntax](https://github.com/cucumber/cucumber/wiki/Gherkin).
2. Run the Chorus interpreter, providing it with a path to find your feature files.

At this point your tests will run, but they will fail because you have not yet provided an implementation for your test steps  

There are several ways to provide implementations of test steps to Chorus:

1. **Supply java classes** on the interpreter's classpath which implement the test steps in your feature files. 
These classes are called ['Handler' classes](/pages/Handlers/HandlerClasses)  

2. **Make use of pre-packaged step definitions** from Chorus' [Built In Handlers](/pages/BuiltInHandlers/BuiltInHandlers).
These provide a library of generic test steps to solve various common problems, 
such as starting and stopping processes, running SQL scripts on databases or connecting Chorus to remote services in an 
integration testing environment.  

3. **Use Chorus' client libaries to publish step definitions** to Chorus from the various microservice or front end components 
running in your integration test environment. The built in handlers **Remoting** and **Web Sockets** provide steps which allow the
Chorus interpreter to connect and find these remote step definitions as part of a test feature. This allows your services under test to 
provide their own step implementations, which are discoverable at runtime by the interpreter.