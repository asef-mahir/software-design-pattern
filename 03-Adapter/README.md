# Adapter Pattern

## Overview

The Adapter Pattern is a structural design pattern that allows incompatible interfaces to work together. It acts as a bridge, converting the interface of one class into another interface that clients expect. This pattern is useful for integrating legacy code with modern systems.

## Problem

### Code Smell

In the `Problem.java` file, the `Hunter` class expects objects implementing the `ILion` interface, but `WildDog` has a different interface:

```java
Hunter hunter = new Hunter();
hunter.hunt(new WildDog()); // Compilation error: WildDog doesn't implement ILion
hunter.hunt(new AfricanLion()); // Works fine
```

### Violation of SOLID Principles

- **Open/Closed Principle (OCP)**: The `Hunter` class is closed for extension. We cannot use `WildDog` without modifying the `Hunter` code.
- **Liskov Substitution Principle (LSP)**: `WildDog` cannot be substituted where `ILion` is expected, even though both represent animals.

### Issues

- **Interface Incompatibility**: `WildDog` uses `bark()` method, while `ILion` expects `roar()` method.
- **Cannot Reuse Existing Classes**: You cannot use `WildDog` with `Hunter` without changing the class structure.
- **Tight Coupling**: `Hunter` is tightly coupled to classes implementing `ILion`.
- **Poor Extensibility**: Integrating new animal types requires modifying existing code.

## Solution

### Approach

We create an `WildDogAdapter` class that implements the `ILion` interface and wraps a `WildDog` instance. The adapter translates `roar()` calls to `bark()` calls, making `WildDog` compatible with the `Hunter` class.

### Key Changes

**Problem:**

```java
Hunter hunter = new Hunter();
hunter.hunt(new WildDog()); // Doesn't work - incompatible interface
```

**Solution:**

```java
static class WildDogAdapter implements ILion {
    private final WildDog dog;
    
    WildDogAdapter(WildDog dog) {
        this.dog = dog;
    }
    
    public void roar() {
        dog.bark();  // Adapt WildDog's bark to ILion's roar
    }
}

// Client code
Hunter hunter = new Hunter();
WildDog dog = new WildDog();
ILion adaptedDog = new WildDogAdapter(dog);

hunter.hunt(adaptedDog); // Works! Output: Bark
```

### Benefits

- **Interface Compatibility**: Makes incompatible interfaces work together without modification.
- **Reuse Legacy Code**: Integrate existing classes with new systems seamlessly.
- **Open/Closed Principle**: The `Hunter` class is closed for modification, open for extension via adapters.
- **Loose Coupling**: `Hunter` depends on `ILion` interface, not concrete implementations.
- **Clean Separation**: The adapter is a separate class, keeping adaptation logic isolated.

### Pattern Benefits

✅ Integrates incompatible interfaces  
✅ Enables code reuse  
✅ Maintains clean separation of concerns  
✅ Supports Open/Closed Principle  
✅ Reduces code duplication  

### When to Use

- When you need to use a class with an incompatible interface.
- When integrating legacy code with new systems.
- When working with third-party libraries that don't match your system's interfaces.
- When you want to provide a different interface for a class.
- When you need to make two incompatible interfaces work together.

### Types of Adapters

- **Class Adapter**: Uses inheritance (implements multiple interfaces)
- **Object Adapter**: Uses composition (wraps the adaptee) - Used in this example

The object adapter approach is generally preferred as it's more flexible and avoids the complexity of multiple inheritance.
