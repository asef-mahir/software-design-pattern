# Simple Factory Pattern

## Overview

The Simple Factory Pattern is a creational design pattern that encapsulates object creation logic, providing a single point for instantiating objects. It simplifies client code by hiding the complexity of object creation.

## Problem

### Code Smell

In the `Problem.java` file, the client code directly instantiates `WoodenDoor` using the `new` keyword:

```java
Door door = new WoodenDoor(200, 100);
```

### Violation of SOLID Principles

- **Dependency Inversion Principle (DIP)**: The main method is tightly coupled to the concrete class `WoodenDoor` rather than depending on an abstraction.
- **Single Responsibility Principle (SRP)**: The main method is responsible for both object creation and business logic.

### Issues

- **Tight Coupling**: Changing the door type requires modifying client code.
- **Hard to Maintain**: Adding new door types (e.g., `SteelDoor`, `GlassDoor`) means updating all client code.
- **Poor Scalability**: Object creation logic is scattered throughout the codebase.

## Solution

### Approach

We introduce a `DoorFactory` class that encapsulates the object creation logic. The factory provides a static method `makeDoor()` that returns `Door` instances.

### Key Changes

**Problem:**

```java
Door door = new WoodenDoor(200, 100);
```

**Solution:**

```java
static class DoorFactory {
    static Door makeDoor(int height, int width) {
        return new WoodenDoor(height, width);
    }
}

// Client code
Door door = DoorFactory.makeDoor(200, 100);
```

### Benefits

- **Decoupling**: Client code depends on the factory method, not concrete classes.
- **Easy Extension**: Adding new door types requires minimal changes:

```java
static Door makeDoor(int height, int width, String type) {
    if ("wooden".equals(type)) {
        return new WoodenDoor(height, width);
    } else if ("steel".equals(type)) {
        return new SteelDoor(height, width);
    }
    return new WoodenDoor(height, width);
}
```

- **Centralized Creation**: All object creation logic is in one place, making it easier to maintain and debug.
- **SOLID Compliance**: Adheres to DIP and SRP by centralizing creation responsibility.

### Pattern Benefits

✅ Simplifies client code  
✅ Centralizes object creation  
✅ Reduces tight coupling  
✅ Enables easy scaling and maintenance  
✅ Improves code readability  

### When to Use

- When you need to create objects without exposing the instantiation logic to the client.
- When you have multiple object types with similar creation processes.
- When you want to centralize object creation for easier maintenance.
