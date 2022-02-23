# MicroHub

MicroHub is a framework for managing TCP client-server communication. It can be used as a hub for IoT devices, but also for generic client server applications.
It follows a particular pattern where the various clients connect to the server through TCP connections.
Clients will connect to the server on a specified port using TCP connections.
Communication happens through an exchange of messages.
Messages can be sent in both directions.
Some messages require replies somne don't.
Messages can be several Mb.
The same TCP connections is maintained for several requests.

## Ease of use implementation

Each connection is handled by a controller.
A controller:
- handles messages from the client (handlers can send a reply)
- can be invoked by other parts of the application
- can have threads that periodically perform something
- has a unique id assigned by the framework (not the client id)
- can have any number of dynamic properties (client id, status, etc.)


## Strict implementation

Each connection is handled by an actor called a controller.
A controller:
- can receive messages from its client
- can receive messages from other parts of the application (other controllers or singleton services)
- can send messages to it's client in a non blocking way
- can send messages to other actors in the application
- can subscribe for a timer message (repeated or not)
- will never be accessed by multiple threads simultaneously
- should never block
- has properties that can be changed while processing a message

ControllerManager:
- keeps track of all controllers
- can index controllers by properties
- allow for querying on controllers based on properties
- allow sending messages to controllers (one or many)
- accepts new connections and instantiates controllers

