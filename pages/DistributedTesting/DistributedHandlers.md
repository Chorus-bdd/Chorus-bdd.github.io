---
layout: page
title: Distributed Handlers
section: Distributed Testing
sectionIndex: 10
---

Usually when you write BDD tests, you need to provide an implementation for each step locally. 

If you want to interact with any components which are running remotely, it is up to you to find a mechanism to connect to them over the network

Chorus changes this, by providing client libraries which allow you to implement test steps within the test code 



Chorus provides a mechanism to export step definitions from a remote component - the class `ChorusHandlerJmxExporter`.
This class can export steps from one or more Handler classes.

You then use the `Remoting` Handler in your Chorus features to allow the interpreter to connect, so that it can discover and run the remote steps.

See also:

[Remoting Handler Quick Start](/pages/BuiltInHandlers/Remoting/RemotingHandlerQuickStart)  
[Remoting Handler Example](/pages/BuiltInHandlers/Remoting/RemotingHandlerExample)

### What network protocol is used?

The ChorusHandlerJmxExporter uses the standard Java platform JMX container.

A Chorus exporter bean is registered in the container, and this can be located by the Chorus interpreter using the RMI/IIOP protocol.
The exporter bean handles remote procedure calls which allow the Chorus interpreter to discover and execute the remote steps.

**ChorusContext**

Another feature which makes Chorus remoting powerful is the `ChorusContext`. 

[Chorus Context](/pages/BuiltInHandlers/ChorusContext/ChorusContext) is a Map containing variables which is propagated to your handler classes (both local and remote) when steps are executed. This means that your `@Step` methods can set variables in the context which are then available in subsequent steps, no matter whether those steps are executed locally or remotely.

