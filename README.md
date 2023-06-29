# [ITA] Prova Finale di Ingegneria del Software - A.A 2022-2023 (English follows)

Prova finale di ingegneria del software per l'anno accademico 2022-2023.<br>
Scaglione Cugola, gruppo GC-019.

## Componenti del gruppo

- [Giuditta Pressiani](https://github.com/GiudittaPressiani)
- [Gabriele Rossi](https://github.com/GABB013)
- [Davide Spinelli](https://github.com/Spdware)
- [Alessandro Travaini](https://github.com/ale-polimi)

## Obiettivo del progetto
[<img src="other-documents/My_Shelfie_box_ITA-ENG.png" width="400"/>](other-documents/My_Shelfie_box_ITA-ENG.png)<br>

Ricreare il gioco da tavolo [MyShelfie](https://craniointernational.com/products/my-shelfie/)<sup>(<i>apre sito esterno</i>)</sup> pubblicato da Cranio Creations, seguendo i principi di buona ingegneria del software.<br>
Il progetta punta a creare una copia virtuale del gioco come un'applicazione Java distribuita e in rete. Come viene insegnato nei corsi d'ingegneria del software, un programma così complesso deve seguire delle regole in modo tale da essere <b>mantenibile</b>, <b>riutilizzabile</b> e <b>aggiornabile</b> da altri sviluppatori.<br>
Per seguire tali linee guida, nella realizzazione del progetto si sono utilizzati diversi <i>[design pattern](https://it.wikipedia.org/wiki/Design_pattern)</i><sup>(<i>apre sito esterno</i>)</sup>, come il pattern <i>Model-View-Controller</i> per una corretta divisione degli strati dell'applicazione.<br>
I requisiti del progetto sono disponibili qui: [requisiti](https://github.com/ale-polimi/ing-sw-2023-pressiani-rossi-spinelli-travaini/blob/master/other-documents/requirements.pdf).

## Documentazione
### UML
- [UML iniziali](https://github.com/ale-polimi/ing-sw-2023-pressiani-rossi-spinelli-travaini/blob/43b7121047d1c5d788177db73249ba1ed56e0abc/deliverables/uml/UML-iniziali.png);
- [UML finali](https://github.com/ale-polimi/ing-sw-2023-pressiani-rossi-spinelli-travaini/blob/0bb7b8cf9f675e1f5172879bb5560ff64466555e/deliverables/uml/final);

### JavaDoc

La maggior parte delle classi e dei metodi sono stati commentati e descritti seguendo lo standard Java con JavaDoc. La documentazione è disponibile qui: [report JavaDoc](https://github.com/ale-polimi/ing-sw-2023-pressiani-rossi-spinelli-travaini/blob/43b7121047d1c5d788177db73249ba1ed56e0abc/deliverables/javadoc).

### JUnit testing

I componenti del gioco sono stati testati singolarmente con JUnit e la copertura del codice è disponibile qui: [immagine di copertura da IntelliJ](https://github.com/ale-polimi/ing-sw-2023-pressiani-rossi-spinelli-travaini/blob/master/deliverables/junit-testing/JUnit_report.PNG), [report HTML](https://github.com/ale-polimi/ing-sw-2023-pressiani-rossi-spinelli-travaini/blob/master/deliverables/junit-testing/htmlReport).<br>
<i>Nota: al fine di testare il controller senza la necessità della rete, si è deciso di creare la classe ```OfflineControllerForTest.java```. Il codice per lo svolgimento del gioco è chiaramente il medesimo.</i>

### Librerie e plugin

| Libreria/Plugin | Descrizione                                                                     |
|-----------------|---------------------------------------------------------------------------------|
| __Maven__       | Strumento di gestione di progetti software basati su Java e build automation.   |
| __JUnit__       | Framework di unit testing per il linguaggio di programmazione Java.             |
| __JavaFX__      | Piattaforma software per la realizzazione di applicazioni grafiche (GUI).       |

## Funzionalità implementate
### Core
- Regole complete disponibili qui: [Regole MyShelfie](https://github.com/ale-polimi/ing-sw-2023-pressiani-rossi-spinelli-travaini/blob/master/other-documents/MyShelfie_Rulebook_ITA.pdf);
- Connessione socket;
- Connessione RMI;
- Interfaccia da Linea di Comando (CLI);
- Interfaccia grafica (GUI);

### Avanzate

| Funzionalità                   | Implementata       |
|--------------------------------|--------------------|
| Chat                           | :white_check_mark: |
| Partite multiple               | :x:                |
| Persistenza                    | :x:                |
| Resilienza alle disconnessioni | :x:                |

Legenda:
- :white_check_mark: : implementata;
- :yellow_circle: : in sviluppo;
- :x: : non implementata;

## Compilazione file sorgente
### Utilizzando i jar precompilati (consigliato)

Si possono utilizzare i jar precompilati che possono essere trovati qui: [jars](https://github.com/ale-polimi/ing-sw-2023-pressiani-rossi-spinelli-travaini/blob/0bb7b8cf9f675e1f5172879bb5560ff64466555e/deliverables/jars).

### Compilazione manuale

Si possono compilare i file sorgente direttamente sulla propria macchina, usando [Maven](https://maven.apache.org/)<sup>(<i>apre sito esterno</i>)</sup> (deve essere installato sulla propria macchina).<br>
Per compilare autonomamente, posizionarsi nella cartella radice del progetto e lanciare il seguente comando:
```
mvn clean package
```
I jar compilati saranno disponibili nella cartella ```target/``` con i seguenti nomi: ```MyShelfie-client.jar``` per il client e ```MyShelfie-server.jar``` per il server.

## Esecuzione
### Avvio del server

Per avviare il server bisogna digitare in un terminale:
```
java -jar MyShelfie-server.jar [socket port](Opzionale) [RMI port](Opzionale)
```
Se <b>entrambi</b> i valori non sono impostati, il server partirà con i valori di default:
- 12345 per socket;
- 1099 per RMI;

:warning: Si consiglia di disabilitare le schede di rete virtuali o inutilizzate, altrimenti il server RMI potrebbe non funzionare correttamente.<br>

Si consiglia di non utilizzare porte già usate da altre applicazioni e di consentire le connessioni su tali porte nel vostro firewall e router.

### Avvio del client

Per avviare il client bisogna digitare in un terminale:
```
java -jar MyShelfie-client.jar <UI> <Connessione>
```
Dove UI e Connessione sono parametri obbligatori.
UI:
- ```-cli``` o ```-c``` avvierà il gioco in modalità CLI;
- ```-gui``` o ```-g``` avvierà il gioco in modalità GUI;

Connessione:
- ```-socket``` o ```-s``` per utilizzare la connessione socket;
- ```-rmi``` o ```-r``` per utilizzare la connessione RMI;

Si consiglia di consentire le connessioni su tali porte nel vostro firewall e router.

## Requisiti di sistema

Questo programma richiede una versione Java 19 o superiore per eseguire correttamente.

Per l'interfaccia CLI:
- Un terminale che supporti [256 colori (8-bit)](https://it.wikipedia.org/wiki/Codici_di_escape_ANSI)<sup>(<i>apre sito esterno</i>)</sup>;
- :warning: Se si utilizza Windows, bisogna scaricare ed eseguire come amministratore [questo script batch](https://github.com/ale-polimi/ing-sw-2023-pressiani-rossi-spinelli-travaini/blob/master/other-documents/change_registry_for_cli.bat), altrimenti non verranno visualizzati i colori nel terminale;
- :warning: Per mantenere la formattazione degli elementi di gioco, si consiglia di utilizzare un tipo di carattere monospazio come Courier New (Windows), Monospace (Linux) o Liberation Mono (Linux).

Per l'interfaccia GUI:
- Uno schermo con risoluzione maggiore di 1366x768 px;

### Compatibilità dell'applicazione

Il client e il server sono stati provati su macchine con i seguenti sistemi operativi:
- Windows 10 1909;
- Windows 10 22H2;
- Fedora Linux 38;
- Windows 11 22H2;

La compatibilità con altri sistemi operativi non è stata collaudata, la vostra esperienza potrebbe variare.

<hr></hr>

# [ENG] Software Engineering A.Y. 2022-2023

Software Engineering project for the 2022-2023 academic year. 

## Group Components

- [Giuditta Pressiani](https://github.com/GiudittaPressiani)
- [Gabriele Rossi](https://github.com/GABB013)
- [Davide Spinelli](https://github.com/Spdware)
- [Alessandro Travaini](https://github.com/ale-polimi)

## Project objective

[<img src="other-documents/My_Shelfie_box_ITA-ENG.png" width="400"/>](other-documents/My_Shelfie_box_ITA-ENG.png)<br>

Recreate the board game [MyShelfie](https://craniointernational.com/products/my-shelfie/)<sup>(<i>opens external site</i>)</sup> published by Cranio Creations following the software engineering principles.<br>
The project aims to create a virtual copy of the game as a distributed, online Java application. As taught in Software Engineering courses, a complex software such as this has to follow some rules in order to be <b>maintainable</b>, <b>reusable</b> and <b>updatable</b> by other developers.<br>
In order to accomplish this, the project uses many [design patterns](https://en.wikipedia.org/wiki/Software_design_pattern)<sup>(<i>opens external site</i>)</sup> such as the <i>Model-View-Controller</i> pattern for the core division of information layers in the application.<br> 
The project's requirements are available here: [requirements](https://github.com/ale-polimi/ing-sw-2023-pressiani-rossi-spinelli-travaini/blob/master/other-documents/requirements.pdf) (available only in Italian).

## Documentation
### UML

- [Initial UML](https://github.com/ale-polimi/ing-sw-2023-pressiani-rossi-spinelli-travaini/blob/43b7121047d1c5d788177db73249ba1ed56e0abc/deliverables/uml/UML-iniziali.png);
- [Completed UML](https://github.com/ale-polimi/ing-sw-2023-pressiani-rossi-spinelli-travaini/blob/0bb7b8cf9f675e1f5172879bb5560ff64466555e/deliverables/uml/final);

### JavaDoc

The vast majority of the classes and methods of the application are commented and described following the Java standards with JavaDoc. The docs are available here: [JavaDoc report](https://github.com/ale-polimi/ing-sw-2023-pressiani-rossi-spinelli-travaini/blob/43b7121047d1c5d788177db73249ba1ed56e0abc/deliverables/javadoc).

### Junit testing

The components of the game have been individually tested with Junit and the code coverage is available here: [image of coverage within IntelliJ](https://github.com/ale-polimi/ing-sw-2023-pressiani-rossi-spinelli-travaini/blob/master/deliverables/junit-testing/JUnit_report.PNG), [HTML report](https://github.com/ale-polimi/ing-sw-2023-pressiani-rossi-spinelli-travaini/blob/master/deliverables/junit-testing/htmlReport).<br>
<i>Note: the class ```OfflineControllerForTest.java``` was created in order to test the controller without the need for a server and connection. The rest of the code for the logic of the game has not been modified.</i> 

### Libraries and plugins

| Library/Plugin | Description                                                     |
|----------------|-----------------------------------------------------------------|
| __Maven__      | Build automation tool used primarily for Java projects.         |
| __JUnit__      | Unit testing framework for the Java programming language.       |
| __JavaFX__     | Software platform for creating and delivering GUI applications. |

## Implemented Functions
### Core

- Complete rule-set available here: [MyShelfie rules](https://github.com/ale-polimi/ing-sw-2023-pressiani-rossi-spinelli-travaini/blob/master/other-documents/MyShelfie_Rulebook_ENG.pdf);
- Socket connection;
- RMI connection;
- Command Line Interface (CLI);
- Graphical Interface (GUI);

### Advanced

| Function                 | Implemented        |
|--------------------------|--------------------|
| Chat                     | :white_check_mark: |
| Multiple games           | :x:                |
| Persistence              | :x:                |
| Disconnection resilience | :x:                |

Legend:
- :white_check_mark: : implemented;
- :yellow_circle: : work in progress;
- :x: : not implemented;

## Compiling sources
### Using precompiled jars (recommended)

The already packaged jars can be found here: [jars](https://github.com/ale-polimi/ing-sw-2023-pressiani-rossi-spinelli-travaini/blob/0bb7b8cf9f675e1f5172879bb5560ff64466555e/deliverables/jars).

### Manual packaging
You can compile the sources on your machine using [Maven](https://maven.apache.org/)<sup>(<i>opens external site</i>)</sup> (must be installed on your computer).<br>
Go to the project's root directory and run this command:
```
mvn clean package
```
The packaged jars will be in the ```target/``` folder and are called ```MyShelfie-client.jar``` for the client and ```MyShelfie-server.jar``` for the server.

## Execution 
### Starting a server

To start a server run the following command in a terminal:
```
java -jar MyShelfie-server.jar [socket port](Optional) [RMI port](Optional)
```
If <b>both</b> are not set the server will start using the default ports:
- 12345 for socket;
- 1099 for RMI;

:warning: Disable all your virtual and unused NICs otherwise the RMI server may not work correctly.<br>

Please be sure that the ports are not used by another application and are allowed through your firewall and router.

### Starting a client

To start a client run the following command in a terminal:
```
java -jar MyShelfie-client.jar <UI> <Connection>
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
 - :warning: If your computer runs Windows, please download and run as administrator [this batch script](https://github.com/ale-polimi/ing-sw-2023-pressiani-rossi-spinelli-travaini/blob/master/other-documents/change_registry_for_cli.bat), otherwise the colours will not be displayed in the terminal;
 - :warning: In order to keep the correct proportions of the elements of the game, it's suggested to use a monospace font such as Courier New (Windows), Monospace (Linux) or Liberation Mono (Linux).

For GUI game:
 - A screen larger than 1366x768 px;

### Application Compatibility

The client and server have been tested working on the following operating systems:
- Windows 10 1909;
- Windows 10 22H2;
- Fedora Linux 38;
- Windows 11 22H2;

Compatibility with other OSes has not been tested, your experience may vary.