---
layout: page
title: What is Chorus?
---

Chorus is a versatile BDD (Behaviour Driven Development) testing interpreter

Conventional BDD tools require you to provide step definitions locally. 
This is possible with Chorus too. 
 
However, the Chorus interpreter also allows you to publish step definitions over the network from your components or microservices under test.
When the interpreter runs it can connect to your components, find the step definitons and execute them.  

Chorus supports both Java (or JVM-based) and Javascript components (enabling a direct connection to test Web apps in the browser)

Chorus is ideal for integration testing systems with microservice-based architectures. 
 

![Chorus Overview](/public/ChorusOverview.png)


## How does Chorus work

Chorus works as a test interpreter for standard Cucumber-style BDD tests written in [Gherkin](https://cukes.info/gherkin.html).
In addition, it provides several [language extensions](/pages/LanguageExtensions/LanguageExtensions) to Gherkin

To access Chorus' distributed testing functionality, you need to use the [Built in Test Steps](/pages/BuiltInHandlers/BuiltInHandlers) 
which are provided with the chorus interpreter. These are generally used in a special `'Feature-Start:'` section, which runs before your Scenarios.  
They are used to set up the test environment by starting processes, connecting to remote componets or executing SQL scripts, for example







