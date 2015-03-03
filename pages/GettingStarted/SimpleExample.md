---
layout: page
title: Simple Example
---

####Chorus Demo Project####

There is a simple project with some Chorus examples, including the Calculator example below, at:

https://github.com/Chorus-bdd/Chorus-demo

####Let's look at a very simple example from Chorus-demo, which tests a Calculator class.####

To make this work you'll just need to create and compile two classes, and write a .feature file:

 1. A feature file, **Calculator.feature** - this contains the plain English test definition.
 2. A java handler class **CalculatorHandler.java** which will execute the steps in the feature.
 3. The class **Calculator.java** which we are going to test  

First here is the feature file:

{% highlight gherkin %}
{% github_sample Chorus-bdd/Chorus-demo/blob/master/src/demo/calculator/calculator.feature %}
{% endhighlight %}


Then the java handler class:

   {% highlight java %}
   {% github_sample Chorus-bdd/Chorus-demo/blob/master/src/demo/calculator/CalculatorHandler.java %}
   {% endhighlight %}


Look here for some [notes on writing handler classes](/pages/Handlers/HandlerClasses)

Here's the Calculator class itself:


   {% highlight java %}
   {% github_sample Chorus-bdd/Chorus-demo/blob/master/src/demo/calculator/Calculator.java %}
   {% endhighlight %}

## Running the test ##

See [Running Chorus](/pages/RunningChorus/RunningChorus)  

First you need to compile the Handler and Calculator class
Make sure they are in your java classpath, along with chorus-{version}.jar  
Then, if your feature file was saved in ./features, and your handler package was org.chorus.example:

`java -cp ${classpath} org.chorusbdd.chorus.Chorus -f ./features -h org.chorus.example`

You can use an absolute or relative path for the features directory.

The output should be:

    Feature: Calculator

      Scenario: Add two numbers
        Given I have entered 50 into the calculator                          PASSED
        And I have entered 70 into the calculator                            PASSED
        When I press add                                                     PASSED
        Then the result should be 120 on the screen                          PASSED


