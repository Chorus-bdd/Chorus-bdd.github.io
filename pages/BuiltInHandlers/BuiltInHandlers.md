---
layout: page
title: Built In Handlers
section: Built In Handlers
sectionIndex: 10
---

The Chorus interpreter includes some built in [Handler Classes](/pages/Handlers/HandlerClasses)

Each of these provides a library of generic step definitions which can be used to accomplish various tasks.  
These handler classes provide definitions for steps in your feature files, just like your own handler classes.

### Built In Handlers

* [Connecting to run steps on distributed components](/pages/BuiltInHandlers/Remoting/DistributedTesting) - The `Remoting` Handler
* [Starting and stopping processes](/pages/BuiltInHandlers/Processes/StartingProcesses) - The `Processes` Handler
* [Manipulate a map of variables within each Scenario](/pages/BuiltInHandlers/ChorusContext/ChorusContextHandler) - The `ChorusContext` handler
* [Timing and sleeping](/pages/BuiltInHandlers/Timers/TimersHandler) - The `Timers` Handler


### Optional Extension Handlers

In addition, some extra Handler Classes are provided as Chrous extension libraries.  
These are included automatically when you install the interpreter as a command line tool, or via the Docker image
  
When you use Chorus within a Java project, you will need to declare an additional dependency on these extension libraries.
These libraries may bring in some additional transitive dependencies

* [Web Sockets support for Browser clients](/pages/BuiltInHandlers/WebSockets/WebSockets) - The `Web Sockets` Handler
* [Using Selenium to interact with Browsers](/pages/BuiltInHandlers/Selenium/Selenium) - The `Selenium` Handler
* [Running SQL Scripts on Databases](/pages/BuiltInHandlers/SQL/SQL) - The `SQL` Handler


### Using Chorus' built in Handler Classes


You need to use the Chorus keyword `Uses:` to indicate you want to use the steps in a built in handler, just as you 
would with your own handler classes:

    Uses: Processes  
    Uses: Remoting 
    
    Feature: My feature using both processes and remoting
    
    Scenario: Scenario one
    
    
