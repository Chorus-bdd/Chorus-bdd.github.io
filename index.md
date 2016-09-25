---
layout: page
title: What is Chorus?
---

Chorus is a BDD (Behaviour Driven Development) testing framework for microservices and distributed systems

It can be used for integration testing on systems with microservice-based architectures.

![Chorus Overview](/public/ChorusOverview.png)


While running a test scenario, Chorus can connect to each of the components in a testing environment to run test steps, and share test state.


## How does Chorus work

Chorus works as a test interpreter for standard Cucumber-style BDD tests written in [Gherkin](https://cukes.info/gherkin.html)
On top of this, it provides several [language extensions](/pages/LanguageExtensions/LanguageExtensions) to Gherkin

Chorus also provides some [Built in Test Steps](/pages/BuiltInHandlers/BuiltInHandlers)
The can be used in your test features to accomplish useful things, such as starting and stopping local processes, and connecting to remote processes to discover and run test steps

Chorus is a java-based framework and at present it can connect to java (or JVM) components only.
We are planning to extend it so that it can connect to components written in other languages.
If you'd like to help with this, get in touch!











