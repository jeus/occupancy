# Getting Started
These instructions will get you a copy of the *`occupancy`* project up and running on your local machine for development and testing purposes.\
In the first step, you should change current path to the project directory
```bash
~# cd PROJECT_DIRECTORY 
```
Some tips:
annotations used from libraries like `Lombok` and `Mapstruct`, IDEs maybe couldn't detect their boilerplates. don't worry 
the project would compile by no bother from terminal. 

## How To Build
```
~# gradle clean build 
```

## How Run
you can run project directly by `java` command or handle it by container both of them run a same port `5001` 
#####Run jar file
```
~# java -jar build/lib/occupancy-1.0.0.jar
```

#####Run Docker container

```bash
~# docker-compose up 
```

Open [localhost:5001/swagger-ui.html](http://localhost:5001/swagger-ui.html)



## Add new test
you could add new test to `Examples` table at `hotel_occupancy.feature` the default test is 
```
 | potentials            | cEconomy | cPremium | cFreeEconomy | cFreePremium | economyIncome | premiumIncome |
 |                       | 1        | 1        | 1            | 1            | 0             | 0             |
 | 99                    | 1        | 0        | 0            | 0            | 99            | 0             |
 | 99                    | 0        | 1        | 0            | 0            | 0             | 99            |
 | 99                    | 1        | 1        | 0            | 1            | 99            | 0             |
 | 100                   | 1        | 0        | 1            | 0            | 0             | 0             |
 | 100                   | 0        | 1        | 0            | 0            | 0             | 100           |
 | 100                   | 1        | 1        | 1            | 0            | 0             | 100           |
 | 23, 45, 155, 374, ... | 3        | 3        | 0            | 0            | 167           | 738           |
 | 23, 45, 155, 374, ... | 5        | 7        | 1            | 1            | 189           | 1054          |
 | 23, 45, 155, 374, ... | 7        | 2        | 3            | 0            | 189           | 583           |
 | 23, 45, 155, 374, ... | 1        | 7        | 0            | 0            | 45            | 1153          |
 | NEW TEST...           |          |          |              |              |               |               |
```