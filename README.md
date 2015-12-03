# CIT Project - Flink-visual-programming

[![Build Status](http://asok16.cit.tu-berlin.de:8080/buildStatus/icon?job=flink-visual)](http://asok16.cit.tu-berlin.de:8080/job/flink-visual/) (Only available from eduroam or VPN)

The Master Project from the departmend Complex and Distributed IT Systems @TU Berlin -
[More information](https://www.cit.tu-berlin.de/menue/teaching/wintersemester_1516/master_projekt_verteilte_systeme/)

The task is to create a visual programming environment for [Apache Flink](https://flink.apache.org/).

## Run the project

Simply type
```mvn jetty:run```
and the webservice will start at `localhost:8080`.

## Jenkins

The most recent master branch build is available at http://asok16.cit.tu-berlin.de:8081.

As Jenkins is not available from the public internet, it can not be notified by Github about new commits. Instead, Jenkins queries Github every 5 minutes for changes and triggers a new build, if necessary. The dashboard is available at http://asok16.cit.tu-berlin.de:8080/, where one can manually trigger a new build.
