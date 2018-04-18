---
layout: page
title: Chorus JS
section: Distributed Testing
sectionIndex: 20
---

## chorus-js

`chorus-js` is a javascript client library for Chorus which allows a Javscript component (e.g. a single page app in a browser) to publish step definitions to the interpreter.

To achieve this:

1. The interpreter starts a web socket server at the beginning of a test feature or scenario
2. The interpreter opens the browser (using Selenium) and triggers test step publication using `chorus-js`
3. The interpreter waits for test steps to be published
4. .. the feature continues

The above handshaking is often carried out in a [Feature Start](/GherkinExtensions/FeatureStartAndEnd) section


### Advantages over a pure Selenium solution

Chorus uses Selenium to start the browser and inject scripts, but after that messages are sent over a direct web socket connection to invoke test steps  
This approach has several advantages over pure Selenium testing:

* The Javascript code implementing the test steps runs directly within the browser
* Test steps can access the DOM (and other APIs such as querySelector) directly.
* Test steps can check state from a state store (e.g. Redux) directly
* The speed of execution is much faster than a pure-Selenium approach, because of the direct web socket connection
* [Context variables](/BuiltInHandlers/ChorusContext/ChorusContext) can be shared with the interpreter and other connected components during the test feature
* Chorus' [Step Retry](/DistributedTesting/StepRetry) capabiities make it much easier to handle assertion failures due to latency / asynchronous behaviour.

