# Romeo and Juliet ODE TCP/IP Client-Server Architecture

This project implements a client-server architecture for solving the Romeo and Juliet ordinary differential equation (ODE) over a ```TCP/IP``` network. The scenario is inspired by ```William Shakespeare's play Romeo and Juliet```, where the playwriter acts as a matchmaker between the two lovers and exchanges love letters while writing the play. Each verse of the play corresponds to different values of the ODE over time. At the end of the iterations, the novel containing the annotated ODE values is dumped into a ```.csv``` file. 

- The client and servers communicate using a TCP/IP protocol. The client sends requests to the servers, and the servers respond with the calculated values of the ODE. The client then annotates these values in the novel.

## Table of Contents

- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [License](#license)
  
# Project Structure

The project consists of three main components:

- Client (Playwriter): The client application responsible for initiating communication with the servers, requesting ODE values, and annotating them in the novel.

- Server 1 (Romeo): The server application that solves one of the equations of the Romeo and Juliet ODE.

- Server 2 (Juliet): The server application that solves the other equation of the Romeo and Juliet ODE.


# Getting Started

To get started with the ODE-Verse-Matchmaker, ensure that you have [IntelliJ IDEA](https://www.jetbrains.com/idea/) and [JavaFX SDK](https://openjfx.io/) installed. Follow the steps below:

- Clone the repository to your local machine.
```xml
git clone https://github.com/hyperFounder/ODE-Verse-Matchmaker
```
- Open the ODE-Verse-Matchmaker project into IntelliJ IDEA.
- [Setting Up IntelliJ IDEA](#setting-up-intellij-idea)
- Build and Run the main method in ```Playwriter.java```.

# Setting Up IntelliJ IDEA

- Download and install the latest version of IntelliJ IDEA Community Edition or Ultimate Edition from the JetBrains website.
  
- Launch IntelliJ IDEA and configure your Java JDK.
  
  - Go to "File" -> "Project Structure" -> "Project Settings" -> "Project" and select a JDK in the "Project SDK" dropdown.
  
- Ensure that the JavaFX library is properly configured in IntelliJ IDEA.
  - Go to "File" -> "Project Structure" -> "Libraries" and add the ```javafx-sdk/lib``` folder by selecting the JavaFX SDK installation directory.
- Build the project in IntelliJ IDEA and verify that the project compiles successfully.

# License
This project is licensed under the [MIT License](https://github.com/hyperFounder/ODE-Verse-Matchmaker/blob/main/LICENSE).
