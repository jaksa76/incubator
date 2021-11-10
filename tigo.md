# TIGO (Text Input Graphical Output) user interfaces

TIGO interfaces are interfaces that primarily use textual commands to operate the OS or program and presenting the information on a complex graphical UI.

While a TIGO interface can have functionality accessible by a mouse, it should always have a keyborad alternative. This is to avoid forcing the user to move the hands away from the keyboard.

TIGO interfaces prefer commands to keyboard shortcuts. All the application functionality must be accessible via commands. Commands take can inputs and produce outputs. This is even more important than the use of the keyboard becuase it enables programmability. Users should be able to define complex processes through sequences of commands. In a TIGO ecosystem, users have a global set of programming constructs (branches, loops, data structures) that allow them to express complex logic.

In order for this to work, commands must be deterministic. Every user workflow should be designed so that it can be automated. Moreover commands should be designed to be composable.

Rather than having a prompt (like bash or powershell) users have access to an editor where they define commands. User defined commands, most of the time will be just invocations of another OS/application provided command. This pushes users to think in terms of automation of their daily work.

Inputs and outputs of commands are structured data parsable by other commands. This promotes command composability. Each data structure is a hypertable. A hypertable can be described as a table with columns organised in a hierarchical fashion. Another way to represent hypertables is as a sequence of structured documents sharing the same schema. Each data structure is also capable of displaying itself on the GUI part of the screen.

