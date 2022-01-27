# LDTS_T02_G08 -> SpaceWars

## Game Description

The SpaceWars is a shooter based game where you have to kill different waves of enemies in order to having the chance to save your spaceship and havving a chance to level up your game.
The variety of levels will have increased difficulty with different strategic enemies and firepower of them.

This project was developed by António Campelo(up201704987@up.pt), Edgar Lourenço(up201604910@up.pt) and José Rodrigues(up201809590@up.pt)

## Implemented Features

- **Connected Menus** - The user has the capability of browsing through the different menus including in game ones. (Ex: Main Menu, Play, Pause and Quit).
- **Buttons** - Functional and interactive buttons.
- **Mouse and Keyboard control** - The mouse and keyboard inputs are received through the respective events and interpreted according to the current game state.
- **Player control** - The player may move with the keyboard control and shoot his gun when space button is pressed.
- **Collisions detection** - Collisions between different objects are verified. (Ex: Player Fire <->Enemies Fire, Player <-> Playground Limits).
- **Different levels** - 3 different levels with an increasing difficulty were implemented.
- **Animations** - Several animations are incorporated in this game, from levels to firing actions.

## Planned Features

All the planned features were successfully implemented without major dificulties.

## Design

### General Structure

#### Problem in Context

The first problem that our group has interested in solve is how the game would be structured. Since our game is dealing with a GUI and has many different states in game, we have some pattern that came to mind to fill that need in the best way.

#### The Pattern

Two main pattern were applied to the project, the **State Pattern** and the **Architetural Pattern**, the first is the behaviour design pattern that lets and object change its behaviour when has internal state changes. The second Pattern is the Model-View-Controller style which is commonly used in a GUI to make a game.

#### Implementation

About the implementation, we have classes which main purpose is to store data(Model), classes that have control of the logic of the game (Controller) and classes that are responsible for the visual effects on the screen(View), these types of classes associate with each other in the following way:

![MVC](uml/ModelViewController.png)
*Fig 1 -> Model-View-Controller*

With the different states of the game, all of them follow the Model-View-Controller style and allow the SpaceWars to modify its behaviour in a simple and effective way.

#### Consequences

- The several states in the game that manage different menus become explicit in the code despite of relying on flags to control
- Easy to add new features in the development stage
- A well organized code acknowledging the Single Responsibility Principle.

### Listeners and Observers

#### Problem in Context

Spacewars is controlled both by the mouse, which controls the menus, and the keyboard, that controls the spaceship and fires, for that many are the ways to receive input from these devices, in example: a thread that is running and every time it catches a signal it sends to the game itself (polling), the game being responsible for asking for input when needed, which is costly for our program since we may not send any signal to the program and unnecessary calls are made or the way we decided to implement which consists of using observers also known as listeners that are responsible for receiving the said input and redistributing it in a nicer and more usefull way to us. This takes some "weight" of the program as it will no longer need to be polling for input, as it will be properly warned when received.

#### The Pattern

We applied in SpaceWars the **Observer Pattern** which is a behaviour design pattern that lets define a mechanism to notify multiple objects about any events that happen to the object they are observing. This pattern allowed us to solve the identified problems and apply a mechanism to receive the game input.

#### Implementation

In SpaceWars, we store the observers in the main class(Game Class) and change the state according to the input processed by the available listener. All listeners were always active, since when creating a MenuButton, the listener would be activate by the new created state.

![OL](uml/ObserversListeners.png)
*Fig 2 -> Observers Listeners*

#### Consequences

- Clean Code
- Only the current game state is avised when input is given
- Promotes the single responsibility principle.

### Level Builder 

#### Problem in Context

A level in SpaceWars consists in an junction of a player(SpaceShip) and a number of enemies, differente from level to level, instead of building level by level, we have a Level Loader that consists in read from a file and inserts into the Builder, that we called LevelBuilder the needed elements to construct the level. This implementation makes it possible to create only the specific elements to the level and generate only the level that the user is.

#### The Pattern

The **Factory Method** and **Builder** are two creational design patterns, the first one provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created. The second, allows you to construct complex objects step by step making a simpler code.

#### Implementation

A factory is resposible for constructing the whole but the workers are the ones that actually execute the job. In this case the LevelBuilder is the factory and its subclasses represent the workers. In the implementation, the LevelBuilder is an abstract class which knows how to construct a level, however only its subclasses supply the necessary components of the level. The LevelLoader is one of the subclasses that consists in a worker capable of reading different levels from different files. The level pattern is implemented in all of the classes show in the figure 3.

![Level](uml/Level.png)
*Fig 3 -> Level Builder and Loader*

#### Consequences

- Open/Closed Principals. Can introduce new types of enemies without breaking existing client code.
- Avoid tight coupling between the creator and the products.
- Can construct level step by step, defer run steps recursively.

### Types of Command

#### Problem in Context

The diversity between buttons was not significant for the objective of the game. In the course of the building of the project, the numbers of buttons had to be generalise. That said and kwoning that a good design of software is often based on the principle of separation of concerns.

#### The Pattern

In SpaceWars we applied the **Command** also kwonw as Action Pattern.This pattern turns a request into a stand-alone object that contains all information about the request.

#### Implementation

All the Button Classes are in a single Button with a command attribute. These commands implement the same interface having an execution method that takes no parameters. This interface lets you use various commands with the same request, without coupling it to concrete commands. With that, you can switch command objects linked to the sender, effectively changing the sender's behaviour.

![Buttons](uml/ButtonCommand.png)
*Fig 4 -> Button - Command*

### GUI

#### Problem in Context

SpaceWars is aiming for a structured and unstable code, that means easy to change the code. The lanterna library contains various functions that aren´t useful or important to write (**Interface Segregation Principle violation**). Also, if using the raw library, SpaceWars would be directly depending on a low level module. This is what we called of **(DIP) Dependecy Inversion Principle**. The need to implement an interface that solves the problem was created.

#### The Pattern

We have applied in SpaceWars the **Facade Pattern**, a simple interface to a complex subsystem which contains moving parts, allowing us to only include features that are important to the game.

#### Implementation

![Lanterna](uml/Lanterna.png)
*Fig 5 -> Lanterna Implementation*

#### Consequences

- Promotes testability and replaceability.
- Expand lanterna functionalities as well as respecting the Interface Segregation Principle.
- Isolate code from the complexity of a subsystem.

## Known Code Smells and Refactoring Suggestions

### Data Class

All model classes are Data Classes, as they only contain fields and are *dumb classes*. This is caused by the **MVC** (Model-View-Controller) architetural pattern which has the responsability to the controller to implement the logic funcionalities of the model. This is not bad code smell because the reason to exists is because of the choosen pattern.

### Feature envy and message chains

In result of the **MVC** implementation some of the controllers use is narrowed to its method method calls. The controller in SpaceWars envies its model. In order to access a certain model's parameter it is mandatory to start by making a request to its controller

### Large Class

Some classes, like *Level*, *Game* or *Player* have many fields in them and others like the Interface of GUI contain many methods. In both cases, we find it justifiable because the classes required these fields in them, in one hand the Game class is the main class of the game and it stores a large amount of data, on the other hand various methods are needed for the interface and it wouldn't make sense to split them in two using extract method.

### Alternative Classes and Lazy Classes

We decided to generalize our **Enemy Class** and differenciate, the divergent characteristics. As this classes only differ in the values passed to the **Enemy Class** constructor and have no other significant functions they are an example of **Alternative Classes and Lazy Classes**.

## Testing

In the following figure we show the test with Coverage Report from the SpaceWars

![Tests](tests/tests.png)
*Fig 6 -> Tests of Code with Coverage*

## Pitest

The mutation testing report can be found here : [Pitest](tests/index.html)


## Self-evaluation

The work was divided in a mutual way and we all contributed with our best. It helped us to enrich our java and principle/pattern knwoledge, as well as our team work.

- António Campelo : 1/3
- Edgar Lourenço : 1/3
- José Rodrigues : 1/3






