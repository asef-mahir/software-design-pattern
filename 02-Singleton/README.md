# Singleton Pattern

## Overview

The Singleton Pattern is a creational design pattern that ensures a class has only one instance and provides a global point of access to it. It's commonly used for resources that should exist in a single instance throughout the application's lifetime.

## Problem

### Code Smell

In the `Problem.java` file, the `President` class can be instantiated multiple times:

```java
President first = new President();
President second = new President();

System.out.println(first == second); // Output: false
```

### Violation of SOLID Principles

- **Single Responsibility Principle (SRP)**: The class allows unrestricted instantiation, violating the principle of having only one instance.
- **Control Inversion**: There's no control over how many instances are created.

### Issues

- **Multiple Instances**: Multiple instances of President can be created, violating real-world constraints (only one president at a time).
- **State Inconsistency**: Different parts of the application may reference different instances, leading to inconsistent state.
- **Resource Waste**: Creating unnecessary instances consumes memory and system resources.
- **No Guaranteed Single Instance**: There's no enforcement mechanism to prevent duplicate instances.

## Solution

### Approach

We restrict the constructor to be private and provide a static method `getInstance()` that returns the single instance of the class. The instance is created only when first requested and reused thereafter.

### Key Changes

**Problem:**

```java
President first = new President();    // Creates instance 1
President second = new President();   // Creates instance 2

System.out.println(first == second);  // false
```

**Solution:**

```java
static class President {
    private static President instance;
    
    private President() {  // Private constructor
    }
    
    static President getInstance() {
        if (instance == null) {
            instance = new President();
        }
        return instance;
    }
}

// Client code
President first = President.getInstance();   // Creates single instance
President second = President.getInstance();  // Returns same instance

System.out.println(first == second);         // true
```

### Benefits

- **Single Instance Guarantee**: Only one instance exists throughout the application's lifetime.
- **Global Access Point**: The static `getInstance()` method provides a global point of access.
- **Lazy Initialization**: The instance is created only when first needed, saving resources.
- **Thread-Safe Implementation**: In multi-threaded environments, proper synchronization ensures only one instance:

```java
static synchronized President getInstance() {
    if (instance == null) {
        instance = new President();
    }
    return instance;
}
```

- **State Consistency**: All parts of the application reference the same instance.

### Pattern Benefits

✅ Guarantees single instance  
✅ Provides global access point  
✅ Lazy initialization  
✅ Ensures state consistency  
✅ Improves resource efficiency  

### When to Use

- When you need exactly one instance of a class throughout the application.
- For shared resources like database connections, logging services, or configuration managers.
- When you want centralized control over a resource.
- For managing global state in a controlled manner.

### Important Considerations

⚠️ **Caution**: Singleton can make unit testing difficult due to global state. Consider using dependency injection as an alternative in test-driven environments.
