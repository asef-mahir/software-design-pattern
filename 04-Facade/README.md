# Facade Pattern

## Overview

The Facade Pattern is a structural design pattern that provides a unified, simplified interface to a complex subsystem. It acts as a single entry point that conceals the complexity of multiple interacting classes, making the system easier to use and understand.

## Problem

### Code Smell

In the `Problem.java` file, the client must directly coordinate multiple subsystem classes to perform a single high-level operation (starting a computer):

```java
Cpu cpu = new Cpu();
Memory memory = new Memory();
HardDrive hardDrive = new HardDrive();

cpu.freeze();
memory.load(0, hardDrive.read(0, 1024));
cpu.jump();
cpu.execute();
```

### Violation of SOLID Principles

- **Single Responsibility Principle (SRP)**: The client is responsible for coordinating complex subsystem interactions.
- **Dependency Inversion Principle (DIP)**: The client depends directly on concrete subsystem classes rather than abstractions.
- **Interface Segregation Principle (ISP)**: The client must know about all low-level subsystem interfaces.

### Issues

- **Complexity Exposure**: Clients must understand the internal workings of multiple subsystem classes.
- **Tight Coupling**: Client code is tightly coupled to subsystem implementations.
- **Difficult Maintenance**: Changes to subsystem interactions require modifying all client code.
- **Poor Readability**: The sequence of operations is unclear without deep knowledge of the system.
- **Error-Prone**: Clients may call operations in the wrong order, leading to unexpected behavior.

## Solution

### Approach

We create a `Computer` facade class that encapsulates all complex subsystem interactions. The facade provides simple, high-level methods like `start()` and `shutdown()` that internally coordinate the subsystem operations.

### Key Changes

**Problem:**

```java
Cpu cpu = new Cpu();
Memory memory = new Memory();
HardDrive hardDrive = new HardDrive();

cpu.freeze();
memory.load(0, hardDrive.read(0, 1024));
cpu.jump();
cpu.execute();
```

**Solution:**

```java
static class Computer {
    private final Cpu cpu = new Cpu();
    private final Memory memory = new Memory();
    private final HardDrive hardDrive = new HardDrive();
    
    void start() {
        cpu.freeze();
        memory.load(0, hardDrive.read(0, 1024));
        cpu.jump();
        cpu.execute();
    }
    
    void shutdown() {
        System.out.println("Computer shutdown");
    }
}

// Client code - Simple and clean
Computer computer = new Computer();
computer.start();
computer.shutdown();
```

### Benefits

- **Simplified Interface**: Clients interact with one simple interface instead of multiple complex subsystems.
- **Loose Coupling**: Client code depends only on the facade, not on internal subsystems.
- **Improved Readability**: The `start()` method clearly expresses intent without exposing implementation details.
- **Easy Maintenance**: Changes to subsystem interactions are localized to the facade.
- **Error Prevention**: Clients cannot accidentally call operations in the wrong order.
- **Reduced Cognitive Load**: Developers don't need to understand the internal complexity to use the system.

### Pattern Benefits

✅ Simplifies complex subsystems  
✅ Provides clear, intuitive interface  
✅ Reduces client-subsystem coupling  
✅ Improves code maintainability  
✅ Enhances system usability  
✅ Isolates subsystem changes  

### When to Use

- When you have a complex subsystem with multiple interacting classes.
- When you want to provide a simplified interface to a library or framework.
- When clients need to understand internal system details to use it.
- When you want to decouple client code from subsystem implementations.
- When you need to orchestrate multiple operations as a single logical unit.

### Facade vs. Other Patterns

| Pattern | Purpose | Focus |
| --- | --- | --- |
| Facade | Simplify complex subsystems | Providing simple high-level interface |
| Adapter | Make incompatible interfaces work | Converting interfaces |
| Decorator | Add behavior to objects | Enhancing individual objects |
| Proxy | Control access to objects | Managing object access |

### Real-World Examples

- Database connection pooling (simple `connect()` method hides connection management)
- REST API clients (single method calls hide HTTP, parsing, error handling)
- Operating system APIs (high-level system calls hide complex kernel operations)
