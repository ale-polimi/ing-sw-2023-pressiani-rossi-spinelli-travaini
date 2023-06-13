# <par>[ITA] Prova Finale di Ingegneria del Software - A.A 2022-2023</par>
- Complete Ruleset: [ITA](https://github.com/ale-polimi/ing-sw-2023-pressiani-rossi-spinelli-travaini/blob/master/src/resources/MyShelfie_Rulebook_ITA.pdf),
# <par>[ENG] Software Engineering A.Y. 2022-2023</par>

Software Engineering project for the 2022-2023 academic year. 

## Group Components
- [Giuditta Pressiani](https://github.com/GiudittaPressiani)
- [Gabriele Rossi](https://github.com/GABB013)
- [Davide Spinelli](https://github.com/Spdware)
- [Alessandro Travaini](https://github.com/ale-polimi)

## Project objective
![alt text](src/resources/My_Shelfie_box_ITA-ENG.png)
Recreate the board game [MyShelfie](https://craniointernational.com/products/my-shelfie/)<sup>(<i>opens external site</i>)</sup> published by Cranio Creations following the software engineering principles.<br>
The project aims to create a virtual copy of the game as a distributed, online Java application. As taught in Software Engineering courses, a complex software such as this has to follow some rules in order to be <b>maintainable</b>, <b>reusable</b> and <b>updatable</b> by other developers.<br>
In order to accomplish this, the project uses many [design patterns](https://en.wikipedia.org/wiki/Software_design_pattern)<sup>(<i>opens external site</i>)</sup> such as the Model-View-Controller pattern for the core division of jobs in the application.<br> 
The project's requirements are available here: [requirements](https://github.com/ale-polimi/ing-sw-2023-pressiani-rossi-spinelli-travaini/blob/master/src/resources/requirements.pdf) (available only in Italian).
## Documentation
### UML
- Initial UML
- Completed UML
### JavaDoc
The vast majority of the classes and methods of the application are commented and described following the Java standards with JavaDoc. The docs are available here:
### Junit testing
The components of the game have been individually tested with Junit and the code coverage is available here:
### Libraries and plugins
| Library/Plugin | Description                                                     |
|----------------|-----------------------------------------------------------------|
| __Maven__      | Build automation tool used primarily for Java projects.         |
| __JUnit__      | Unit testing framework for the Java programming language.       |
| __JavaFX__     | Software platform for creating and delivering GUI applications. |
## Implemented Functions
### Core
- Complete rule-set available here: [MyShelfie rules](https://github.com/ale-polimi/ing-sw-2023-pressiani-rossi-spinelli-travaini/blob/master/src/resources/MyShelfie_Rulebook_ENG.pdf);
- Socket connection;
- RMI connection;
- Command Line Interface (CLI);
### Advanced

| Function                 | Implemented     |
|--------------------------|-----------------|
| Chat                     | :yellow_circle: |
| Multiple Games           | :x:             |
| Persistence              | :x:             |
| Disconnection Resilience | :x:             |
## Starting a server
To start a server run the following command in a terminal:
```
java -jar myshelfie-server.jar [socket port](Optional) [RMI port](Optional)
```
If <b>both</b> are not set the server will start using the default ports:
- 12345 for socket;
- 1099 for RMI;

Please be sure that the ports are not used by another application and are allowed through your firewall and router.
## Starting a client
To start a client run the following command in a terminal:
```
java -jar myshelfie-client.jar <UI> <Connection>
```
Where UI and Connection are both mandatory.
UI:
- ```-cli``` or ```-c``` will start the CLI game;
- ```-gui``` or ```-g``` will start the GUI game;

Connection:
- ```-socket``` or ```-s``` will use the socket connection;
- ```-rmi``` or ```-r``` will use the RMI connection;

Please make sure the ports are open on your firewall and router.
## System Requirements
Generally a system that runs Java 19+ applications.

For CLI game:
 - A terminal that supports [8-bit (256) colours](https://en.wikipedia.org/wiki/ANSI_escape_code#8-bit)<sup>(<i>opens external site</i>)</sup>;

## Application Compatibility
The game and server have been tested working on the following operating systems:
- Windows 10 1909;
- Windows 10;
- Fedora Linux 38;
- Windows 11;

Compatibility with other OSes has not been tested, your experience may vary.