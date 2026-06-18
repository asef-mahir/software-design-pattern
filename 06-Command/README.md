# Command Pattern

## Overview

The Command Pattern is a behavioral design pattern that encapsulates a request as an object, allowing you to parameterize clients with different requests, queue requests, log requests, and support undoable operations. It decouples the object that invokes an operation from the one that performs it.

## Problem

### Code Smell

In the `Problem.java` file, the `main` method contains multiple conditional branches for different actions:

```java
Chef chef = new Chef();
String order = "burger";

if ("burger".equals(order)) {
    chef.cook("burger");
} else if ("pizza".equals(order)) {
    chef.cook("pizza");
} else if ("salad".equals(order)) {
    chef.cook("salad");
}
```

### Violation of SOLID Principles

- **Open/Closed Principle (OCP)**: The code must be modified every time a new dish type is added.
- **Single Responsibility Principle (SRP)**: The main method has multiple reasons to change (one for each new dish).
- **Dependency Inversion Principle (DIP)**: The client directly depends on the `Chef` class, not an abstraction.

### Issues

- **Hard-Coded Conditionals**: Adding new dishes requires modifying the main method with additional if-else branches.
- **Poor Extensibility**: Every new command requires code modification in multiple locations.
- **Tight Coupling**: The main method is tightly coupled to the `Chef` class.
- **Limited Flexibility**: Difficult to queue, undo, or log commands.
- **Testing Difficulty**: Multiple paths make unit testing complex.
- **Violates OCP**: Closed for modification, but constantly requires modification.

## Solution

### Approach

We introduce a `Command` interface that encapsulates each request as an object. Each command (like `CookBurgerCommand`) implements the `Command` interface with an `execute()` method. A `Waiter` class accepts commands and executes them, decoupling the invoker from the actual operation.

### Key Changes

**Problem:**

```java
Chef chef = new Chef();
if ("burger".equals(order)) {
    chef.cook("burger");
} else if ("pizza".equals(order)) {
    chef.cook("pizza");
}
```

**Solution:**

```java
interface Command {
    void execute();
}

static class CookBurgerCommand implements Command {
    private final Chef chef;
    
    CookBurgerCommand(Chef chef) {
        this.chef = chef;
    }
    
    public void execute() {
        chef.cook("burger");
    }
}

static class Waiter {
    void takeOrder(Command command) {
        command.execute();
    }
}

// Client code - No conditionals!
Chef chef = new Chef();
Waiter waiter = new Waiter();

waiter.takeOrder(new CookBurgerCommand(chef));
```

### Benefits

- **No Conditionals**: The command pattern eliminates if-else chains.
- **Easy Extension**: Adding new commands only requires creating a new class implementing `Command`.
- **Loose Coupling**: The `Waiter` is decoupled from specific command implementations.
- **Encapsulation**: Each command encapsulates the details of its operation.
- **Flexibility**: Commands can be queued, logged, undone, or scheduled:

```java
// Queue commands
Queue<Command> commandQueue = new LinkedList<>();
commandQueue.add(new CookBurgerCommand(chef));
commandQueue.add(new CookPizzaCommand(chef));

// Execute all
while (!commandQueue.isEmpty()) {
    commandQueue.poll().execute();
}
```

- **Supports Undo**: Each command can implement `undo()` for reversible operations.

### Pattern Benefits

✅ Eliminates conditional logic  
✅ Supports Open/Closed Principle  
✅ Enables command queuing  
✅ Supports undo/redo operations  
✅ Allows command logging and auditing  
✅ Decouples invoker from receiver  
✅ Makes testing easier  

### When to Use

- When you need to parameterize objects with operations.
- When you want to queue, log, or schedule command execution.
- When you need to support undo/redo functionality.
- When you have multiple related requests that differ only in parameters.
- When you want to decouple the object invoking an operation from the one performing it.


### Pattern Variations

| Variation         | Use Case                                   |
|-------------------|--------------------------------------------|
| Simple Command    | Execute immediately                        |
| Queued Command    | Deferred execution                         |
| Composite Command | Combine multiple commands                  |
| Undoable Command  | Support undo/redo                          |
| Scheduled Command | Execute at specific times                  |

### Real-World Examples

- GUI buttons and menu items (each button is a command)
- Database transactions (commit/rollback)
- Macro recording (record and replay commands)
- Job queues in background processing
- Undo/redo functionality in text editors
