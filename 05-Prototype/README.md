# Prototype Pattern

## Overview

The Prototype Pattern is a creational design pattern that creates new objects by copying an existing object (the prototype) rather than creating them from scratch. This pattern is useful when object creation is expensive or complex, and you need multiple similar instances.

## Problem

### Code Smell

In the `Problem.java` file, creating a cloned object requires manually copying each field:

```java
Sheep original = new Sheep("Jolly", "Mountain Sheep");
Sheep cloned = new Sheep(original.name, original.category);
cloned.name = "Dolly";
```

### Violation of SOLID Principles

- **Single Responsibility Principle (SRP)**: The client is responsible for copying object state.
- **Open/Closed Principle (OCP)**: Adding new fields to `Sheep` requires updating all cloning code throughout the application.
- **Don't Repeat Yourself (DRY)**: Manual field-by-field copying is repeated whenever cloning is needed.

### Issues

- **Manual Copying**: Developers must remember all fields and manually copy them.
- **Error-Prone**: Forgetting to copy a field leads to incomplete clones with subtle bugs.
- **Tight Coupling**: Client code knows the internal structure of objects.
- **Scalability Issues**: Adding new fields requires updating all cloning code.
- **Complex Object Graphs**: Copying objects with nested references becomes extremely complicated.
- **Maintenance Burden**: Every code location that clones objects must be updated when the class changes.

## Solution

### Approach

We implement the `Cloneable` interface and provide a `cloneSheep()` method that uses `Object.clone()`. This method creates a shallow copy of the object, automatically copying all fields without requiring manual intervention.

### Key Changes

**Problem:**

```java
Sheep original = new Sheep("Jolly", "Mountain Sheep");
Sheep cloned = new Sheep(original.name, original.category);  // Manual copying
cloned.name = "Dolly";
```

**Solution:**

```java
static class Sheep implements Cloneable {
    String name;
    String category;
    
    Sheep(String name, String category) {
        this.name = name;
        this.category = category;
    }
    
    Sheep cloneSheep() {
        try {
            return (Sheep) super.clone();  // Automatic field copying
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}

// Client code - Simple and clean
Sheep original = new Sheep("Jolly", "Mountain Sheep");
Sheep cloned = original.cloneSheep();  // Creates complete copy
cloned.name = "Dolly";
```

### Benefits

- **Automatic Field Copying**: `Object.clone()` automatically copies all fields.
- **Reduced Code Duplication**: No need to manually copy fields everywhere.
- **Easy to Maintain**: Adding new fields automatically includes them in cloning.
- **Cleaner Client Code**: Cloning is a single method call, not multiple field assignments.
- **Performance**: Cloning via `Object.clone()` is typically faster than constructor-based copying.
- **Encapsulation**: Internal structure remains hidden from clients.

### Pattern Benefits

✅ Simplifies object cloning  
✅ Reduces code duplication  
✅ Improves maintainability  
✅ Better performance for complex objects  
✅ Automatic handling of new fields  
✅ Supports polymorphic cloning  

### When to Use

- When object creation is expensive (complex initialization, resource allocation).
- When you need multiple similar instances with minor differences.
- When you want to avoid exposing object creation details to clients.
- When you have a complex object hierarchy and need to create copies.
- When you want to decouple client code from specific class implementations.

### Implementation Considerations

#### Shallow vs. Deep Copy

**Shallow Copy (default Object.clone()):**
- Copies primitives by value
- Copies object references as-is (points to same objects)
- Use when: Objects contain only primitives or shared references are acceptable

**Deep Copy:**
- Recursively copies all nested objects
- Use when: You need completely independent copies with no shared state

```java
Sheep deepClone() throws CloneNotSupportedException {
    Sheep cloned = (Sheep) super.clone();
    // Deep copy any mutable nested objects if needed
    return cloned;
}
```

#### Java's Cloneable Interface

- `Cloneable` is a marker interface (no methods to implement)
- It signals that `Object.clone()` is permitted
- Without implementing `Cloneable`, calling `super.clone()` throws `CloneNotSupportedException`

### Real-World Examples

- Creating game entity copies (characters, enemies, items)
- Configuration object duplication with modifications
- Database connection pooling with template connections
- UI component cloning in GUI frameworks
