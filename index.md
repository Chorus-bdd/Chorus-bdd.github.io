---
layout: page
title: What is Chorus?
---

Chorus is a BDD testing tool which makes it easier to write tests for complex systems

Chorus tests can perform actions across components in a distributed architecture.

## Why is Chorus needed?

It can be very difficult to write full-system integration tests using conventional BDD techniques.

Systems with several user interfaces can be especially hard to test, as are reactive systems in which components need to respond to external events.

At some point while testing a system like this, you're bound to need to do some of the following:

* Connect to a remote component to perform an action
* Trigger a mock message to be published from one component to another
* Start a process and check it's output or interact with it while it is running
* Bootstrap or tear down the environment used for integration testing
* Run a feature more than once in different configurations
* Run your tests using profiles for different testing environments

Chorus can help with these things

## How does Chorus work

Chorus works as a test interpreter for standard Cucumber-style BDD tests written in [Gherkin](https://cukes.info/gherkin.html)

On top of this, it provides several [language extensions](/pages/LanguageExtensions/LanguageExtensions) to Gherkin

One of the most important language extensions is the ability to [embed 'Directives' in your feature files](/pages/LanguageExtensions/Directives)
Directives can provide technical instructions which indicate how a test should run, but keep these separate from the business language.

Chorus provides some built in directives, but if these are not sufficient you can easily supply [handler classes](/pages/Handlers/HandlerClasses) to implement your own













