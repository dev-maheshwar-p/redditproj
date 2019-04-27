
# Reddit Sanity Suite

This project serves as a sanity suite intended to demonstrate a test framework built using Java + Selenium + Junit + Cucumber + Serenity + Spring


####Things that could have been done better, but de-priortized based on showcasing intent and available time.

1. Logging -- More log line with the appropriate log levels.
2. Only the basic minimum test cases on a 1:1 cardinality has been automated -- More test cases against the scenarios.
3. Hard coding a expected sub-reddit -- Rather than choosing a ransom channel, register and ensure that it show up in the list of registered channels.
4. Comments - More Java doc comments, code inined comments on logic.
5. Noticed during execution that some channels black list preventing comments -- In this case could remove the channel add another and then add a comment.
6. Also in about 5-10% of the cases, the click on the sub-reddit opens a link to an external site - I could I written login to select another sub-reddit at random or click else where on the same sub-reddit.

####Things that are implemented and intended to be show cased as part of this project:

1. The selection of the automation stack namely Java + Selenium + Junit + Maven + Cucumber + Serenity + Spring, each of which provides well defined layers of functionality.
2. Most other popular automation frameworks, are built as sub sets of the above or combinations of the above, for example: Test-Ng-Spring, Junit-Spring, TestNg-Cucumber-Spring, Junit-Serenity, etc.  
3. Provided 80% more code in the form of Utilities classes across concerns to show the core role/integration of Spring, more than using it as a DI framework.  
4. Areas addressed in the design include:
    * Profiles - This allows maintaining the same automation code base to be used for different project/environment combinations, all with static configuration in the spring configuration file and run time parameters.
    * Properties - Uses Spring's solution to access properties across all the property files loaded base on project/env through a single Environment object that is autowired.  
    * JMS - Addresses multiple provider types and projects. Refer: 'SpringJmsUtils.java'. This is intended to demonstrate more than one project's JMS availability [here indicated as reddit and it's upstream both of which may be needed for testing/validation]
    * API/REST - Rest Assured is supported innately by Serenity and captures payloads and response. There is also a set of utilities that have been written using Spring's "RestTemplate" Refer: 'SpringRestClient.java'
    * JDBC - Uses Spring "JdbcTemplate" as a simple alternative to JDBC's painful syntax.
5. CustomDriver.java - This class is used to customize/override the default behaviour of the Chrome Driver instance instantiated by Serenity. Example: Execution in headless mode. 
6. CustomSerenityRunner.java - This is a custom serenity runner that extends CucumberWithSerenity that is used to take advantage of some of the life-cycle methods of Serenity.
7. Project Structure: In alignment with project/env. Example: Only properties files locations are ${env} [environment] specific as these provide configuration input like hitting a mock vs actual EP based on env.
8. Rolling & archived logs. 

####Things that were not implemented yet but were intended to be show cased as part of this project, in its current state.

1. Written helpers/custom predicate, function methods for selenium that dont require verification of element visiblity/presence for every element.
2. Retry implementations using failsafe [not maven failsafe] to cover for flaky tests as we all know that fluent waits and expected conditions ain't going to cut it.
3. Automated JIRA creation on test failures, with prevention of duplicate tickets and failure categorization based on component and reason of failure.
 

## Getting Started


### Prerequisites

You must have Maven 3.xx+ and Java 1.8 JDK isntalled. 

```

To verify pre-requisites, run the below commands in the terminal/command prompt.


mvn --version

Sample Output [May vary based on version and OS]

Apache Maven 3.6.1 (d66c9c0b3152b2e69ee9bac180bb8fcc8e6af555; 2019-04-05T00:30:29+05:30)
Maven home: /usr/local/Cellar/maven/3.6.1/libexec
Java version: 1.8.0_211, vendor: Oracle Corporation, runtime: /Library/Java/JavaVirtualMachines/jdk1.8.0_211.jdk/Contents/Home/jre
Default locale: en_GB, platform encoding: UTF-8
OS name: "mac os x", version: "10.14.4", arch: "x86_64", family: "mac"




java -version

Sample Output [May vary based on version and OS]

java version "1.8.0_211"
Java(TM) SE Runtime Environment (build 1.8.0_211-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.211-b12, mixed mode)


```

### Installing

Just import the project into an IDE like IntelliJ or Eclipse

```
Example : 

File -> Open on IntelliJ and import it as a maven project.
```

## Running the tests
```
1. Navigate to the path of the project. 

    cd ~/Documents/xxx/redditproj


2. Run the maven verify phase on the project.

    mvn clean verify -Dproject=reddit -Denv=test
```



## Built With


* [Java](https://www.java.com/en/) - The programming/scripting language used to develop the automation framework.
* [Selenium](https://www.seleniumhq.org/) - UI automation framework used with its Java bindings and Web Driver 2/3 API internally.
* [RestAssured](http://rest-assured.io/) - The primary java library used for API testing. 
* [Maven](https://maven.apache.org/) - Used primarily in this project for Dependency Management.
* [JUnit](https://junit.org/junit5/) - Unit testing framework, that actually provides the runner to execute tests.
* [Cucumber](https://cucumber.io/) - BDD framework, that provides Gherkin language support + mapping to tests in Java using cucumber-java8
* [Serenity](https://maven.apache.org/) - An cool wrapper framework on top of Cucumber, that supports API + UI + any testing.
* [Spring](http://www.dropwizard.io/1.0.2/docs/) - The most popular java enterprise development framework. The ultimate glue/core of this test framework, which I have used extensively for Profiles, Properties, JMS, API, JDBC, Mongo, REST, etc.    



## Authors

* **Dev Maheshwar Prabhakaran**


## License

Go Figure. 

