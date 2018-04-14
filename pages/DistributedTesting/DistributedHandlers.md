---
layout: page
title: Distributed Handlers
section: Distributed Testing
sectionIndex: 10
---

Usually when you write BDD tests, you need to provide an implementation for each step locally. If you want to interact with any components which are running remotely, it is up to you to find a mechanism to connect to them.

Chorus reverses this, by providing client libraries which allow you to publish test steps from the components you wish to test. 

Chorus enables step publication from Java (JVM) and Javascript components


### Java / JVM

To publish steps to the Chorus interpreter from a Java/JVM component, you can use the Chorus utility class `ChorusHandlerJmxExporter` within the component.
This utility uses Java's JMX platform MBean server to make steps discoverable  

Typically steps publication is only enabled in UAT/Integration testing environments, and not in a Production environment

The `Remoting` handler provides built in steps to use in your feature files which allow Chorus to connect to a remote component and discover the step definitions.

See:

[Remoting Handler Quick Start](/pages/BuiltInHandlers/Remoting/RemotingHandlerQuickStart)  
[Remoting Handler Example](/pages/BuiltInHandlers/Remoting/RemotingHandlerExample)



### Javascript / Browser

You can use the `chorus-js` library to publish steps to Chorus from a Javascript component.
This library can publish step definitions to the Chorus interpreter over a web socket connection.

The `Web Sockets` handler provides built in steps which allow the Chorus interpreter to initiate the web socket and wait for step publication to complete.
The `Selenium` handler provides steps to open a browser and interact with it.

When testing browser-based apps, the Javascript which publishes the step defintions can either be built into the app directly, or can be injected via Selenium

