# Romeo and Juliet ODE TCP/IP Client-Server Architecture

This project implements a client-server architecture for solving the Romeo and Juliet ordinary differential equation ```(ODE)``` over a ```TCP/IP network```. The scenario is inspired by ```William Shakespeare's play Romeo and Juliet```, where the playwriter acts as a matchmaker between the two lovers and exchanges love letters while writing the play. At the end, the novel is dumped into a ```.csv``` file.

# Project Structure

The project consists of three main components:

- Client (Playwriter): The client application responsible for initiating communication with the servers, requesting ODE values, and annotating them in the novel.

- Server 1 (Romeo): The server application that solves one of the equations of the Romeo and Juliet ODE.

- Server 2 (Juliet): The server application that solves the other equation of the Romeo and Juliet ODE.

```This is a concurrent system and both servers are single-threaded.```

# Getting Started
To run the client-server system, follow these steps:

- Clone the repository to your local machine.
```xml
git clone https://github.com/hyperFounder/ODE-Verse-Matchmaker
```
- Open the terminal and navigate to the project directory.

- Compile and run the server applications (Romeo and Juliet) on separate terminal windows or machines.

# TCP/IP Communication Protocol
The client and servers communicate using a TCP/IP protocol. The client sends requests to the servers, and the servers respond with the calculated values of the ODE. The client then annotates these values in the novel.

# License
This project is licensed under the ```MIT License```.
