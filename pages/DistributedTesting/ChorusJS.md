---
layout: page
title: Chorus JS
section: Distributed Testing
sectionIndex: 20
---

`chorus-js` is a javascript client library for Chorus which allows a Javscript component (e.g. a single page app in a browser) to publish step definitions to the interpreter.

To achieve this, at the start of a test feature:

1. The interpreter starts a web socket server, to listen for step publication
2. The interpreter opens the browser (using Selenium) and the browser loads the app.
3. The app opens a web socket connection and publishes test steps using `chorus-js`
4. The interpreter waits for test step publication to complete
5. .. the feature continues, maching steps against both local and published step definitions

The above handshaking is often carried out in a [Feature Start](/Pages/GherkinExtensions/FeatureStartAndEnd) section, which contains the steps to make the connection, leaving the business functionality to the scenarios

#### The feature file may start like this:

    Uses: Web Sockets
    Uses: Selenium

    Feature-Start:
        First I start a web socket server                   
        And I open the Chrome browser                     
        And I navigate to http://my-big-single-page-app
        And I wait for the web socket client BigSinglePageApp
       
    Scenario: Scenario One
        ...scenario steps here




### Advantages over a pure Selenium solution

Chorus uses Selenium to start the browser and inject scripts, but after that messages are sent over a direct web socket connection to invoke test steps  
This approach has several advantages:

* The Javascript code implementing the test steps runs within the browser, and can access the DOM (and other APIs such as querySelector) directly.
* Execution is much faster than a pure-Selenium approach, because of the direct web socket connection
* Handling latency-related errors is much easier, due to Chorus' [Step Retry](/Pages/DistributedTesting/StepRetry) capabilities


### Integrating test steps with the app

There are several approaches to integrating test steps with the app, there are advantages and tradeoffs of each approach

##### 1. Build in test steps alongside the app code

This is the simplest way to get started. You need to add chorus-js as a dependency and use it in your app to publish test steps.
Typically test step publication is triggered by setting an http parameter when the app is loaded.

##### 2. Load test step scripts from a separate back end app

In this approach you maintain a separate app which builds and serves test scripts separately from the production app.
Chorus' Selenium handler can be used to run a script after loading the app in the browser, which triggers the load of 
the test scripts after the main app has been loaded. This keeps the test code entirely separate from the production app code, 
at the cost of some extra complexity
