# auto-pilot #

A light weighted BDD framework built using java spring to perform automation.

**Libraries used:**
1. TestNG
2. Cucumber
3. Spring
4. Selenium

**Build Tool:** Maven

### Getting Started ###

### Prerequisites

What you need to install before importing the project.
```
1. Java 11
2. Maven
```
### Plugins to be installed in Intellij
```
1. Cucumber for Java
2. Lombok
```
### To run your project
1. Through maven `mvn clean install`
2. Alternatively, you can also run by executing the `testNG.xml`


### Connect your repo to upstream repo:

1. `git remote -v` this will give the current origin.
2. `git remote add upstream https://github.com/like-thrill/auto-pilot`, this will add the upstream branch.
3. `git remote -v` to check if the upstream is added.

### To pull from the upstream repo:

##### Make sure you connect to the upstream repo
```
git checkout main
git fetch upstream
git merge upstream/main 
```

Resolve the merge conflicts if any, commit and push.

### References
* Cucumber: https://cucumber.io/docs/guides/overview/
* Spring-test: https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html
* Lombok: https://www.baeldung.com/intro-to-project-lombok
* Web-driver-manager: https://github.com/bonigarcia/webdrivermanager
