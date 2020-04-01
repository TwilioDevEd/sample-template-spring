<a  href="https://www.twilio.com">
<img  src="https://static0.twilio.com/marketing/bundles/marketing/img/logos/wordmark-red.svg"  alt="Twilio"  width="250"  />
</a>
 
# Twilio Sample App Template

[![Actions Status](https://github.com/TwilioDevEd/sample-template-spring/workflows/Spring%20CI/badge.svg)](https://github.com/TwilioDevEd/sample-template-spring/actions)

## About

This is a GitHub template for creating other [Twilio] sample/template apps. It contains a variety of features that should ideally be included in every Twilio sample app. You can use [GitHub's repository template](https://help.github.com/en/github/creating-cloning-and-archiving-repositories/creating-a-repository-from-a-template) functionality to create a copy of this.

Implementations in other languages:

| .NET | Ruby | Python | PHP | NodeJS |
| :--- | :--- | :----- | :-- | :----- |
| TBD  | TBD  | TBD    | TBD | [Done](https://github.com/twilio-labs/sample-template-nodejs)   |

### How it works

This is only a barebones Java webapp with Spring. Whenever, possible we should be using this. However, if you are using another framework that comes with their own standardized application structure, you should try to merge these by using the same `README` structure and test coverage, configuration etc. as this project.

<!--
**TODO: UML Diagram**

We can render UML diagrams using [Mermaid](https://mermaidjs.github.io/).


**TODO: Describe how it works**
-->

## Features

- [Spring Framework](https://spring.io/) version 5.2.5
- [Spring Boot](https://spring.io/projects/spring-boot) version 2.2.5 to easily create and configure production-grade Spring based Applications.
- Build and dependency management using [Gradle](https://gradle.org/)
- User interface to send SMS with bootstrap and [Thymeleaf](https://www.thymeleaf.org/).
- Unit tests using [Junit5](https://junit.org/junit5/) and [Hamcrest](http://hamcrest.org/JavaHamcrest/)
- Mocking framework using [Mockito](https://site.mockito.org/)
- End to End UI testing using [Selenium](https://www.selenium.dev/)
- [Automated CI testing using GitHub Actions](/.github/workflows/spring.yml)
- Project specific environment variables using `application-local.properties`.
- One click deploy button for Heroku.

## How to use it

1. Create a copy using [GitHub's repository template](https://help.github.com/en/github/creating-cloning-and-archiving-repositories/creating-a-repository-from-a-template) functionality
2. Update the [`README.md`](README.md), [`settings.gradle`](settings.gradle), [`build.gradle`](build.gradle) and [`app.json`](app.json) with the respective values.
3. Build your app as necessary while making sure the tests pass.
4. Publish your app to GitHub

## Set up

### Requirements

- [Java Development Kit](https://adoptopenjdk.net/) version 8 or later.
- A Twilio account - [sign up](https://www.twilio.com/try-twilio)

### Twilio Account Settings

This application should give you a ready-made starting point for writing your
own appointment reminder application. Before we begin, we need to collect
all the config values we need to run the application:

| Config&nbsp;Value | Description                                                                                                                                                  |
| :---------------- | :----------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Account&nbsp;Sid  | Your primary Twilio account identifier - find this [in the Console](https://www.twilio.com/console).                                                         |
| Auth&nbsp;Token   | Used to authenticate - [just like the above, you'll find this here](https://www.twilio.com/console).                                                         |
| Phone&nbsp;number | A Twilio phone number in [E.164 format](https://en.wikipedia.org/wiki/E.164) - you can [get one here](https://www.twilio.com/console/phone-numbers/incoming) |

### Local development

After the above requirements have been met:

1. Clone this repository and `cd` into it

    ```bash
    git clone git@github.com:twilio-labs/sample-template-spring.git
    cd sample-template-spring
    ```

1. Set your environment variables

    ```bash
    cp src/main/resources/application.properties src/main/resources/application-local.properties
    ```
    See [Twilio Account Settings](#twilio-account-settings) to locate the necessary environment variables.

1. Build the project

    ```bash
    ./gradlew build
    ```
    **NOTE:** Running the build task will also run the tests

1. Run the application

    ```bash
    ./gradlew bootRun
    ```
    **NOTE:** If you are using a dedicated Java IDE like Eclipse or IntelliJ, you can start the application within the IDE and it will start in development mode, which means any changes on a source file will be automatically reloaded.

1. Navigate to [http://localhost:8080](http://localhost:8080)

That's it!

### Tests

You can run the tests locally by typing:

```bash
./gradlew test
```

### Cloud deployment

Additionally to trying out this application locally, you can deploy it to a variety of host services. Here is a small selection of them.

Please be aware that some of these might charge you for the usage or might make the source code for this application visible to the public. When in doubt research the respective hosting service first.

| Service                           |                                                                                                                                                                                                                           |
| :-------------------------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| [Heroku](https://www.heroku.com/) | [![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy?template=https://github.com/TwilioDevEd/sample-template-spring/tree/master)                                                                                                                                       |

**Some notes:** 
- For Heroku, please [check this](https://devcenter.heroku.com/articles/deploying-gradle-apps-on-heroku) to properly configure the project for deployment.
- You can also follow [this guide](https://vaadin.com/blog/how-to-deploy-your-java-app-to-the-cloud) to deploy the application to several other cloud services including Google Cloud, Oracle Cloud, etc.

## Resources

- [GitHub's repository template](https://help.github.com/en/github/creating-cloning-and-archiving-repositories/creating-a-repository-from-a-template) functionality

## Contributing

This template is open source and welcomes contributions. All contributions are subject to our [Code of Conduct](https://github.com/twilio-labs/.github/blob/master/CODE_OF_CONDUCT.md).

[Visit the project on GitHub](https://github.com/twilio-labs/sample-template-spring)

## License

[MIT](http://www.opensource.org/licenses/mit-license.html)

## Disclaimer

No warranty expressed or implied. Software is as is.

[twilio]: https://www.twilio.com
