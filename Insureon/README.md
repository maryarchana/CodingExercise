# project

The project is configured using Cucumber, Junit and Built using Maven.

## Getting started

The project is preconfigured to execute Cucumber tests with JUnit. You can run
the tests from the command line with:

```
$ mvn clean test
$ mvn clean test -Dtag=@home
```

Or from Eclipse with `Run As` -> `Maven test`:

## Writing features

Feature files are stored in [src/test/resources/example](src/test/resources/example),

## Implementing step definitions

When you run `mvn test`, Cucumber will output method stubs of step definitions
to implement the tests like this:

```
@When("^I visit home page of insureon	$")
public void visit_home_page() throws Throwable {
    throw new PendingException();
}
```

Step definitions can be defined with the `@Given`, `@When`, and `@Then` method
annotations in any class you like, though the `src/test/java/example/Stepdefs.java`


