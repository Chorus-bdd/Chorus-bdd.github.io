---
layout: page
title: Distributed Testing
section: Remoting
sectionIndex: 10
---

Chorus has some unique capabilities for distributed testing:

To test distributed systems, you can export step implementations from remote components, and have the interpreter call
those components over the network to run test steps.

Usually when you run a test with Chorus, the interpreter will look for `@Handler` classes with step definitions in its local classpath.
When you do distributed testing, these Handler classes can be in remote components instead

Chorus provides a mechanism to export step definitions from a remote component - the class `ChorusHandlerJmxExporter`.
This class can export steps from one or more Handler classes.

You then use the `Remoting` Handler in your Chorus features to allow the interpreter to connect, so that it can discover and run the remote steps.

To do this, all you need to do is add `Uses: Remoting` to your feature file, and add config properties tell Chorus the network addresses of your components.

### What network protocol is used?

The ChorusHandlerJmxExporter uses the standard Java platform JMX container.

A Chorus exporter bean is registered in the container, and this can be located by the Chorus interpreter using the RMI/IIOP protocol.
The exporter bean provides remote procedure calls for the Chorus interpreter to discover and execute the remote steps.

See  
[Remoting Handler Quick Start](/pages/BuiltInHandlers/Remoting/RemotingHandlerQuickStart)  
[Remoting Handler Example](/pages/BuiltInHandlers/Remoting/RemotingHandlerExample)

**ChorusContext**

Another feature which makes Chorus remoting powerful is the `ChorusContext`. 

[Chorus Context](/pages/BuiltInHandlers/ChorusContext/ChorusContext) is a Map containing variables which is propagated to your handler classes (both local and remote) when steps are executed. This means that your `@Step` methods can set variables in the context which are then available in subsequent steps, no matter whether those steps are executed locally or remotely.

