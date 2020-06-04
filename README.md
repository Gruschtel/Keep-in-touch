# Touch

Keep in touch

<!-- Markdown link & img dfn's -->
[unity-image]: https://img.shields.io/badge/Unity-2018.04.13f-ff8a1c.svg
[unity-url]: https://unity.com/de
[version-image]: https://img.shields.io/badge/Version-1.1-blue.svg
[vive-image]: https://img.shields.io/badge/VIVE%20Input%20Utility-1.10.4-ff8a1c.svg
[vive-url]: https://assetstore.unity.com/packages/tools/integration/vive-input-utility-64219
[vr-image]: https://img.shields.io/badge/Application-PC--VR-blue.svg
<!-- -->
![Application][vr-image]
![Version][version-image]
[![Unity Version][unity-image]][unity-url]
[![VIVE Input Utility][vive-image]][vive-url]
# Satellitenbewegung – Simulation und Animation
## Table of Contents
* [About the Project](#about-the-project)
* [Getting Started](#getting-started)
   * [Prerequisites](#prerequisites)
   * [Installing](#installing)
      * [VIVE Input Utility](#vive-input-utility)
* [Usage](#usage)
   * [Controls](#controls)
   * [Menü](#menü)
   * [StartRoom](#startroom)
   * [SpaceObject Settings](#spaceobject-settings)
   * [Add new SpaceObjects](#add-new-spaceobjects)
      * [New Satellite](#new-satellite)
      * [New Planet](#new-planet)
* [Materials](#materials)
* [Perspective](#perspective)
* [Versioning](#versioning)
* [Authors](#authors)

    

## About The Project
Diese Anwendung beinhaltet eine einfache Simulation zur Darstellung von Satelliten und deren Umlaufbahnen um die Erde. Dafür wurden folgende Features implementiert:
* Modellierung des Sonnensystems
* Darstellung von zwei unterschiedlichen Szenen (Solarsystem und Earth)
* Statische Umlaufbahnen - Satelliten um einen Planeten, Planeten um einen Stern
* Dynamische Umlaufbahnen - Nur in der Erden Szene vorhanden. Erzeugt für jedes einzelne Objekt dessen Umlaufbahn 
* Visualisierung einer Start Plattform für den Betrachter
* Darstellung der Umlaufbahnen
* Menü:
  * Aktivierung und Deaktivierung der Umlaufbahnen
  * Geschwindigkeitsmanipulation aller Objekte (Verlangsamen und Beschleunigen)
  * Zurück zum Startraum


<img src="https://user-images.githubusercontent.com/24352711/61186890-f5144000-a66a-11e9-878b-dc2d1a1f4981.png" alt="Szene 1" /><br>Abbildung 1 

<img src="https://user-images.githubusercontent.com/24352711/61186185-b37f9700-a662-11e9-8f89-933c72b13538.png" alt="Szene 2" /><br>Abbildung 2

Die Anwendung wurde im Rahmen der Vorlesung Augmented und Virtual Reality (AVR) des Masters Studiengangs Informatik an der Hochschule Kaiserslautern - University of Applied Sciences, Campus Zweibrücken erstellt.
<!--
<img src="https://user-images.githubusercontent.com/24352711/60571868-a1554d00-9d74-11e9-9756-7f3cd473cdfe.png" alt="hs logo" width="30%"/><br>
 -->
https://www.hs-kl.de/<br/><br/>

## Getting Started
Diese Anleitung erklärt, was bei der lokalen Einrichtung des Projekts beachtet werden muss, damit die Anwendung problemlos zum Laufen gebracht werden kann.

### Prerequisites
Software, die zum Starten der Anwendung benötigt werden:
- Unity 2018.3.14f1 - https://unity.com/
- SteamVR - https://www.steamvr.com/en/
- VIVE Input Utility (Unity Plugin) - https://assetstore.unity.com/packages/tools/integration/vive-input-utility-64219

### Installing

#### VIVE Input Utility
Damit das VR-Headsets sowie Gadgets erkannt und genutzt werden können, muss das VIVE Input Utility Plugin zunächst in den Einstellungen aktiviert werden:
1. Abb. 1: Öffnen Sie zunächst den Reiter **Edit**
2. Abb. 2: Wählen Sie den Menü-Punkt **Preferences..** aus
3. Abb. 3: Es öffnet sich ein neues Fenster. Wählen Sie zunächst in der linken Spalte den Menü-Punkt **VIU Settings** aus
4. Abb. 4: Aktivieren Sie unter Supporting Device den **Simulator** und wenn nötig **VIVE (OpenVR compatible device)**. Die Option **VIVE** ist jedoch nur nötig, wenn ein VR Headset und Controller zur Verfügung steht. Ansonsten kann die Option VIVE deaktiviert bleiben

<img src="https://user-images.githubusercontent.com/24352711/61176998-4240d500-a5cb-11e9-880a-35b4b479d5d5.png" alt="Step 1" width="60%"/><br>Abbildung 3

<img src="https://user-images.githubusercontent.com/24352711/61176996-4240d500-a5cb-11e9-9b2f-69930ebcf44d.png" alt="Step 2" width="60%"/><br>Abbildung 4

<img src="https://user-images.githubusercontent.com/24352711/61176997-4240d500-a5cb-11e9-83a2-804f1d56ceae.png" alt="Step 3" width="75%"/><br>Abbildung 5


## Usage
### Controls
Um die Anwendung vollständig nutzen zu können, werden zwei VIVE Controller benötigt: einen rechten und linken VIVE Controller. Im Folgenden werden die Tasten und die damit belegten Funktionen kurz erklärt. An dieser Stelle sei angemerkt, dass beide Controller äußerlich und von der Funktionalität identisch sind. Der erste VIVE Controller welcher registriert wird, wird als rechter, der zweite VIVE Controller als linker Ingame-Controller erkannt.

<img src="https://user-images.githubusercontent.com/24352711/61177433-be401a80-a5d5-11e9-9147-adc6a687b8ee.png" alt="Controls" width="50%"/><br>Abbildung 6


Nummer | Definition
--- | ---
1 | Menü Taste 
2 | Trackpad
3 | System Taste
4 | Statuslampe
5 | Micro-USB-Anschluss
6 | Verfolgungssensoren
7 | Trigger
8 | Griff Taste

https://www.vive.com/de/support/vive/category_howto/about-the-controllers.html

Da die Interaktionsmöglichkeiten zwischen dem Spieler und der Welt stark begrenzt sind, werden nicht alle Buttons der VIVE-Controller genutzt. Daher hier eine Auflistung der genutzten Buttons sowie deren Funktion für den jeweiligen Controller.

Rechter Controller | Funktion 
--- | ---
Trigger | Lässt den Spieler z.B. Objekte „Graben“ (greifen) oder einzelne Buttons und Slider benutzen
Trackpad - Up | Öffnet das Menü
Trackpad - Down | Schließt das Menü
Trackpad - Right/Left | Ermöglicht es dem Spieler sich innerhalb der Welt an eine von ihm ausgewählte Position zu teleportieren. Diese Funktion kann nur im StartRoom genutzt werden

Linker Controller | Funktion 
--- | ---
Trigger | Lässt den Spieler in Zeige-Richtung des VIVE-Controller fliegen (fly). Nur in den Szenen Earth und Solarsystem vorhanden
Trackpad - Up| Erhöht die Flug-Geschwindigkeit um fünf Einheiten
Trackpad - Down | Reduziert die Flug-Geschwindigkeit um fünf Einheiten

### Menü
Das Menü kann über das Trackpad des rechten Controllers aktiviert und wieder deaktiviert werden. Zum Aktivieren des Menüs muss das Trackpad nach oben, zum Schließen des Menüs nach unten betätigt werden. Das Menü kann nur in den Szenen „Earth“ und „Solarsystem“ aufgerufen werden. Im Menü (Abb. 7) hat der Nutzer die Möglichkeit, zurück zur Szene "StartRoom" zu gelangen, die statischen oder dynamischen Umlaufbahnen der Satelliten und Planeten zu aktivieren oder die Weltgeschwindigkeit der Simulation zu erhöhen beziehungsweise zu reduzieren. Um eine Eigenschaft zu aktivieren, deaktivieren oder zu manipulieren, muss die gewählte Eigenschaft mit dem rechten Controller fokussiert und anschließend mit dem Trigger des rechten Controllers ausgewählt werden. Die Toggles können dabei ein oder ausgeschaltet werden, wobei zu beachten ist, dass immer nur ein Toggle aktiviert sein kann. Sollte der Nutzer nach Aktivierung eines Toggle den anderen aktivieren, so wechselt der Zustand zu dem neu ausgewählten Toggle und der alte wird deaktiviert. Mit dem Slider kann der Nutzer die Geschwindigkeit aller Game-Objekte manipulieren. So ist es möglich, durch Verschieben des Reglers nach links, die Bewegung der Objekte bis auf null (Stillstand) zu reduzieren. Mit dem Bewegen des Reglers nach rechts kann die "Weltgeschwindigkeit" bis zum Faktor zwei erhöht werden.

<img src="https://user-images.githubusercontent.com/24352711/61189956-ee9abe00-a694-11e9-9ed5-86057c57f5ab.png" height="80%" alt="Menü"/> <br>Abbildung 7

### StartRoom
Beim Starten der Anwendung „spawnt“ der Nutzer zunächst in den „StartRoom“. Im „StartRoom“ hat der Nutzer die Möglichkeit entweder eine der zwei Szenen (Solarsystem oder Earth) zu starten (Abb. 8) oder die Anwendung (Abb. 9) zu beenden. Die Miniatur-Erde zur linken des Benutzers startet die Szene „Earth“, in welcher eine Nahaufnahme der Erde mit einigen Satelliten zu sehen ist. In der Szene „Solarsystem“, die sich auf der rechten Seite befindet, wird das Sonnensystem gezeigt. Um eine Szene auszuwählen oder das Spiel zu beenden, muss der Nutzer, die davor befindlichen Buttons betätigen. Hierzu muss der Controller auf den Button bewegt werden, bis dieser eine gelbliche Farbe annimmt (Abb. 10) und anschließend mit dem rechten Trigger bestätigen woraufhin sich der Button grün verfärbt (Abb. 11). Lässt man den Trigger los, ohne den Controller aus dem Button zu ziehen, wird die zugehörige Szene gestartet. Im Gegensatz zu den Szenen „Earth“ und „Solarystem“ kann sich der Nutzer im „StartRoom“ nicht mit dem linken Controller per „Flying“ bewegen. Um größere Strecken zurückzulegen, wird dem Nutzer die Möglichkeit geboten, sich per „Teleport“ an einen durch den Nutzer ausgewählten Standort zu teleportieren (Abb.12). Den Teleporter kann man über das Drücken des rechten Touchpads nach links oder rechts auslösen.

<img src="https://user-images.githubusercontent.com/24352711/61335542-2426df00-a82e-11e9-9bbc-89be091cf205.png" alt="Button 1 und 2" width="45%"/> &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; <img src="https://user-images.githubusercontent.com/24352711/61332970-e9b94400-a825-11e9-9e50-a696f077be73.png" alt="Button 3" width="45%"/><br>Abbildung 8 &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;Abbildung 9

<img src="https://user-images.githubusercontent.com/24352711/61335553-3012a100-a82e-11e9-861b-40deb7103474.png" alt="Button focus" width="45%"/> &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; <img src="https://user-images.githubusercontent.com/24352711/61335555-3143ce00-a82e-11e9-99cc-9233b1931493.png" alt="Button pressed" width="45%"/><br>Abbildung 10 &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;Abbildung 11

<img src="https://user-images.githubusercontent.com/24352711/61336245-eaa3a300-a830-11e9-9bdd-85fd600bc601.png" height="80%" alt="Teleport"/> <br>Abbildung 12

### SpaceObject Settings 

<img src="https://user-images.githubusercontent.com/24352711/61177780-1fb7b780-a5dd-11e9-8c48-bb968199e560.png" alt="Kreisbewegung Script" width="40%"/> &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; <img src="https://user-images.githubusercontent.com/24352711/61184499-b5d7f600-a64e-11e9-9cad-59d2cb4a41eb.png" alt="Latitude und Longitude" width="50%"/><br>Abbildung 13 &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;Abbildung 14

Input | Type | Funktion 
--- | --- | ---
Satelliten Toggle | Toggle | Statische Umlaufbahnen anzeigen/umschalten
Umlaufbahn Toggle | Toggle | Dynamische Umlaufbahnen anzeigen/umschalten
World Speed Slider | Slider | Erhöht bzw. reduziert die Gesamtgeschwindigkeit der Objekte <br/>**Min.**: 0 -> Stillstand<br/>**Max.:** 2 -> doppelte Geschwindigkeit
Center Object | GameObject | Mittelpunkt, um welchen das GameObject rotieren soll. Wenn kein CenterObject ausgewählt wird, rotiert das Objekt um sich selbst
Clockwise Direction | bool | Richtung der Rotation<br/>**true:** Mit dem Uhrzeigersinn<br/>**false:** Gegen den Uhrzeigersinn
Start Angle | float | Bestimmt auf welcher Latitude sich das GameObjekt um das CenterObject befindet
Start Bias | float | Bestimmt auf welcher Longitude sich das GameObjekt um das CenterObject befindet
Start Progress | float | Start Angle und Start Bias bestimmen die Rotationsbahn/Umlaufbahn des GamesObject um einen Mittelpunkt.<br/>Start Progress bestimmt wo sich das GameObject auf dieser Umlaufbahn befindet
Orbit Speed | float | Geschwindigkeit mit der sich das GameObject pro Frame bewegt
Orbit Trans | float | Legt die Entfernung zwischen dem GameObject und dem CenterObject fest<br/>Wichtig: Das GameObject wird zunächst auf die Position des CenterObjects verschoben und dann an dessen Oberfläche
Line Renderer | LineRenderer | Wird benötigt um die Umlaufbahnen des GameObjects um ein CenterObject darzustellen. Wird kein LineRenderer angegeben, kann das Objekt seine Umlaufbahn nicht abbilden
Renderer Accuracy | int | Abtastrate/Genauigkeit der Umlaufbahnen

### Add new SpaceObjects
#### New Satellite
Aktuell (V. 1.0) bietet die Anwendung zwei vordefinierte Satelliten als Prefab an. Damit ein Objekt um ein anderes Objekt rotieren kann, muss dem Objekt das Script „Kreisbewegung“ hinzugefügt werden. Zu den Einstellungen des Skripts kann man mehr im Abschnitt [SpaceObject Settings](#spaceobject-settings) lesen. Auch sollte dem Objekt die Komponente LineRenderer hinzugefügt und dem Skript "Kreisbewegung" bekannt gemacht werden, damit es die Umlaufbahn visuell darstellen kann. Zu beachten ist an dieser Stelle, dass der LineRenderer am besten deaktiviert werden sollte. Ansonsten kann es vorkommen, dass auch wenn keine Umlaufbahn angezeigt werden soll, eine Linie in der Anwendung abgebildet wird. Das Skript Kreisbewegung schaltet die Komponente LineRenderer automatisch an und aus. Um jedoch generell die Umlaufbahnen darstellen zu können benötigt das Skript einen Toggle, über den die Darstellung ein und ausgeschaltet werden kann.

<img src="https://user-images.githubusercontent.com/24352711/61187129-6275a000-a66e-11e9-8071-663f497847a9.png" alt="Satellit erstellen" width="50%"/><br>Abbildung 15

#### New Planet
In Version 1.0 existieren aktuell lediglich alle Planten des Sonnensystems. Das Erzeugen eines neuen Planeten läuft ähnlich dem Erzeugen eines neuen Satelliten ab. Zunächst erzeugen wir ein neues Objekt (folgend bezeichnet als "Planet") und fügen dem Objekt Planet das Skript "Kreisbewegung" hinzu (Abb. 16). Im Skript des Objekt Planet darf zunächst kein CenterObject zugewiesen werden. Wenn CenterObject „none“ ist, rotiert das Objekt um sich selbst. Dies bedeutet in unserem Fall: Das Objekt Planet dreht sich um sich selbst. Auch benötigt das Objekt Planet an dieser Stelle keine LineRenderer Komponente. Da es keine klassische Umlaufbahn hat, wenn es nur um sich selbst rotiert. Das Skript benötigt ebenfalls keinen Toggle zum An- und Ausschalten der verschiedenen Umlaufbahnen. Einzig, einen Slider zum Verwalten der Welt-Geschwindigkeit wird in diesem Skript benötigt. Ebenfalls muss man im ersten Schritt kaum weitere Eigenschaften des Skripts beachten. Mit Clockwise Direktion kann die Rotationsrichtung eingestellt werden und über die Eigenschaft Orbit Speed die Rotationsgeschwindigkeit. Alle anderen Eigenschaften des Skripts werden nicht beachtet. Dem Objekt Planet können wir nun ein Model oder eine Textur zuweisen, welche am Ende dargestellt werden soll.
Bis jetzt wurde nur ein Objekt erzeugt, dass sich um sich selbst dreht. Der zweite Schritt besteht nun darin ein leeres GameObject (folgend bezeichnet als PlanentenBewegung) zu erzeugen. Dem Objekt PlanentenBewegung wird ebenfalls das Skript "Kreisbewegung" hinzuzufügen und das zuvor erstellte GameObjekt Planet als Child zugewiesen (Abb. 17). Nun weisen wir dem CenterObject (PlanentenBewegung) das Objekt zu, um welches sich der Planet drehen soll. Somit dreht sich das Objekt PlanentenBewegung um das CenterObject und transponiert seine Bewegungen/Rotation ebenfalls auf all seine Child-Objekte. Dadurch erzeugen wir den Effekt, dass sich das Objekt Planet nicht nur um sich selbst dreht, sondern auch um das in PlanentenBewegung hinterlegten CenterObject rotiert. Ebenfalls können wir im Skript Kreisbewegung des Objektes PlanentenBewegung alle weiteren Einstellungen wie beispielsweise die Entfernung (Orbit Trans) oder die Rotationsgeschwindigkeit um das CenterObject einstellen. Da ein Planet prinzipiell eine Umlaufbahn um ein CenterObject besitzt, kann man dem Objekt PlanentenBewegung noch die Komponenten LineRenderer hinzufügen und den LineRenderer im Kreisbewegung Skript hinterlegen. In diesem Fall benötigt PlanentenBewegung ebenfalls Zugriff auf einen Toggle, welcher das Ein- und Ausschalten der Umlaufbahnen verwaltet.
Sollen nun Satelliten um diesen Planeten (Objekt Planet) rotieren, muss darauf geachtet werden, dass diese als Child-Objekte in das Objekt PlanentenBewegung hinzugefügt werden. Damit rotieren sie automatisch mit ihrem Parent-Objekt um dessen CenterObject und gleichzeitig um ihr eigenes CenterObjekt (Objekt Planet). Um dies etwas besser zu verdeutlichen, kann Abbildung 15 betrachtet werden ( [Add new Satellite](#add-new-satellite) ). Hier wurde ein Parent-Objekt „Erde“ erstellt, welches ein Child-Objekt Erde (Planeten) und die Satelliten als Child-Objekte beinhaltet. Die Satelliten wurden zusätzlich nach ihrer Umlaufbahn zusammengefasst, dies ist jedoch nicht zwingend notwendig.


<img src="https://user-images.githubusercontent.com/24352711/61187361-d4e77f80-a670-11e9-9845-052648e2383d.png" alt="Planet erstellen - Step 1" width="43%"/>  &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; <img src="https://user-images.githubusercontent.com/24352711/61187183-edef3100-a66e-11e9-842c-c7dc994804da.png" alt="Planet erstellen - Step 2" width="45%"/><br>Abbildung 16 &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;Abbildung 17

## Materials
* Planets: https://www.solarsystemscope.com/textures/
* Satelliten: https://free3d.com/de/
* Startraum: Asset - Nature Starter Kit 2 

## Perspective
Folgende Komponenten oder Funktionen könnten in künftigen Projekten umgesetzt oder ergänzt werden:
* Monde einfügen
* Planetarium (Sternbilder als Polygonzüge)
* (Ingame) Satelitten können durch den Nutzer beinflusst werden (Flugbahn, Entfernung, Größe, Anzahl pro Umlaufbahn, ...)
* (Ingame) Hinzufügen von Umlaufbahnen durch den Nutzer
* Kollisionsermittler
* Weltraumeinflüsse (auf Satelliten), z.B.: Meteroitenschauer, Sonnenstürme, Gravitation andere Weltraumobjekte, ...
* Mehr Realismus (Skalierung, Saturnringe, etc.)
* Anheften an einen Satelliten oder Planeten
* Raumstation anstatt Pavilion
* Spieler trägt einen Raumanzug (Controller mit Händen, Helm mit visuellen Informationen beispielsweise Menü, ...)
* Spieler befindet sich in einem kleinen Raumschiff
* Startraum gestaltet als ISS-Raumstation, Raumschiff Enterprise, ...
* Animation von Start und Landung (Verbindung aller Räume in einer Szene)

## Versioning
* 1.0: Veröffentlichung
* 1.1: Bugfixes; Update asserts and unity to 2018.04.13f


## Authors
   Alea Ilona Sauer – [GitHub Profil](https://github.com/saalea)<br/>
   Eric Gustav Werner – [GitHub Profil](https://github.com/Gruschtel)
