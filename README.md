# CIT Project - Flink-visual-programming

**Master branch:** [![Jenkins Build Status Master](http://asok16.cit.tu-berlin.de:8080/buildStatus/icon?job=flink-visual)](http://asok16.cit.tu-berlin.de:8080/job/flink-visual/)
**Dev branch:** [![Jenkins Build Status Dev](http://asok16.cit.tu-berlin.de:8080/buildStatus/icon?job=flink-visual-dev)](http://asok16.cit.tu-berlin.de:8080/job/flink-visual-dev/)

The Master Project from the departmend Complex and Distributed IT Systems @TU Berlin -
[More information](https://www.cit.tu-berlin.de/menue/teaching/wintersemester_1516/master_projekt_verteilte_systeme/)

The task is to create a visual programming environment for [Apache Flink](https://flink.apache.org/).

## Run the project

Simply type `mvn jetty:run` and the jetty server will start at `localhost:8080`.

For local frontent development it might be easier to use `grunt serve` for hosting only the website locally instead of the whole jetty environment.
This is possible via the maven `dev` profile. Simply run `mvn jetty:run -P dev`, which will handle everything for you.
**Caution** - This will only start the frontend, not the backend.

## Jenkins

We use Jenkins for continous deployment of the project.
You can find information about each job and, the build status and e.g. console output during the build.
The dashboard is available at http://asok16.cit.tu-berlin.de:8080/.

We use two main branches for development: **master** and **dev**.
* The most recent **master** branch build is available at http://asok16.cit.tu-berlin.de:8081.
* The most recent **dev** branch build is available at  http://asok16.cit.tu-berlin.de:8082.

## Tests
When you compile the project via maven (```mvn compile``` or ```mvn package```, ```mvn jetty:run``` will skip the tests) our unit tests will be automatically executed and the build will fail if a test fails.
If you don't want to run the tests you can add a ```-DskipTests``` argument at the end of the command.

## Tests for Pull request
