# [ITA] Prova Finale di Ingegneria del Software - A.A 2022-2023 (English follows)

Prova finale di ingegneria del software per l'anno accedemico 2022-2023.<br>
Scaglione Cugola, gruppo GC-019.

## Componenti del gruppo

- [Giuditta Pressiani](https://github.com/GiudittaPressiani)
- [Gabriele Rossi](https://github.com/GABB013)
- [Davide Spinelli](https://github.com/Spdware)
- [Alessandro Travaini](https://github.com/ale-polimi)

## Obiettivo del progetto
![alt text](other-documents/My_Shelfie_box_ITA-ENG.png)<br>

Ricreare il gioco da tavolo [MyShelfie](https://craniointernational.com/products/my-shelfie/)<sup>(<i>apre sito esterno</i>)</sup> pubblicato da Cranio Creations, seguendo i principi di buona ingegneria del software.<br>
Il progetta punta a creare una copia virtuale del gioco come un'applicazione Java distribuita e in rete. Come viene insegnato nei corsi d'ingegneria del software, un programma così complesso deve seguire delle regole in modo tale da essere <b>mantenibile</b>, <b>riutilizzabile</b> e <b>aggiornabile</b> da altri sviluppatori.<br>
Per seguire tali linee guida, nella realizzazione del progetto si sono utilizzati diversi <i>[design pattern](https://it.wikipedia.org/wiki/Design_pattern)</i><sup>(<i>apre sito esterno</i>)</sup>, come il pattern <i>Model-View-Controller</i> per una corretta divisione degli strati dell'applicazione.<br>
I requisiti del progetto sono disponibili qui: [requisiti](https://github.com/ale-polimi/ing-sw-2023-pressiani-rossi-spinelli-travaini/blob/master/other-documents/requirements.pdf).

## Documentazione
### UML
- [UML iniziali]();
- [UML finali]();

### JavaDoc

La maggior parte delle classi e dei metodi sono stati commentati e descritti seguendo lo standard Java con JavaDoc. La documentazione è disponibile qui:

### JUnit testing

I componenti del gioco sono stati testati singolarmente con JUnit e la copertura del codice è disponibile qui:

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

### Avanzate

| Funzionalità                   | Implementata    |
|--------------------------------|-----------------|
| Chat                           | :yellow_circle: |
| Partite multiple               | :x:             |
| Persistenza                    | :x:             |
| Resilienza alle disconnessioni | :x:             |

## Esecuzione
### Avvio del server

Per avviare il server bisogna digitare in un terminale:
```
java -jar myshelfie-server.jar [socket port](Opzionale) [RMI port](Opzionale)
```
Se <b>entrambi</b> i valori non sono impostati, il server partirà con i valori di default:
- 12345 per socket;
- 1099 per RMI;

Si consiglia di non utilizzare porte già usate da altre applicazioni e di consentire le connessioni su tali porte nel vostro firewall e router.

### Avvio del client

Per avviare il client bisogna digitare in un terminale:
```
java -jar myshelfie-client.jar <UI> <Connessione>
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
- Un terminale che supporta [256 colori (8-bit)](https://it.wikipedia.org/wiki/Codici_di_escape_ANSI)<sup>(<i>apre sito esterno</i>)</sup>;

### Compatibilità dell'applicazione

Il client e il server sono stati provati su macchine con i seguenti sistemi operativi:
- Windows 10 1909;
- Windows 10;
- Fedora Linux 38;
- Windows 11;

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

![alt text](other-documents/My_Shelfie_box_ITA-ENG.png)<br>
Recreate the board game [MyShelfie](https://craniointernational.com/products/my-shelfie/)<sup>(<i>opens external site</i>)</sup> published by Cranio Creations following the software engineering principles.<br>
The project aims to create a virtual copy of the game as a distributed, online Java application. As taught in Software Engineering courses, a complex software such as this has to follow some rules in order to be <b>maintainable</b>, <b>reusable</b> and <b>updatable</b> by other developers.<br>
In order to accomplish this, the project uses many [design patterns](https://en.wikipedia.org/wiki/Software_design_pattern)<sup>(<i>opens external site</i>)</sup> such as the <i>Model-View-Controller</i> pattern for the core division of information layers in the application.<br> 
The project's requirements are available here: [requirements](https://github.com/ale-polimi/ing-sw-2023-pressiani-rossi-spinelli-travaini/blob/master/other-documents/requirements.pdf) (available only in Italian).

## Documentation
### UML

- [Initial UML]();
- [Completed UML]();

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

- Complete rule-set available here: [MyShelfie rules](https://github.com/ale-polimi/ing-sw-2023-pressiani-rossi-spinelli-travaini/blob/master/other-documents/MyShelfie_Rulebook_ENG.pdf);
- Socket connection;
- RMI connection;
- Command Line Interface (CLI);

### Advanced

| Function                 | Implemented     |
|--------------------------|-----------------|
| Chat                     | :yellow_circle: |
| Multiple games           | :x:             |
| Persistence              | :x:             |
| Disconnection resilience | :x:             |


## Execution
### Starting a server

To start a server run the following command in a terminal:
```
java -jar myshelfie-server.jar [socket port](Optional) [RMI port](Optional)
```
If <b>both</b> are not set the server will start using the default ports:
- 12345 for socket;
- 1099 for RMI;

Please be sure that the ports are not used by another application and are allowed through your firewall and router.

### Starting a client

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

### Application Compatibility

The client and server have been tested working on the following operating systems:
- Windows 10 1909;
- Windows 10;
- Fedora Linux 38;
- Windows 11;

Compatibility with other OSes has not been tested, your experience may vary.