# **Introducing Inheritance – Coffee Shop Simulation**

This project focuses on Object-Oriented Programming concepts such as inheritance, method overriding, class casting, and
encapsulation through a coffee shop simulation. It builds a hierarchy of coffee types and a Barista interface to manage
coffee-making processes interactively, using an animated terminal-based UI.

---

## **Project Overview**

This laboratory aims to:

- **Implement inheritance hierarchies** for different coffee types.
- **Reuse methods through overriding** to follow the DRY principle.
- **Add class-specific behavior** using class casting.
- **Encapsulate core logic** through a Barista class, ensuring access to core classes is restricted.
- **Provide an animated terminal UI** using the JLine library for a more immersive user experience.

## **Project Structure**

```
03-introducing-inheritance
│
├── src
│   └── main
│       └── java
│           └── org.sergedb.oop.inheritance
│               ├── coffee (Coffee classes hierarchy)
│               ├── barista (Barista and core management classes)
│               └── Main.java (entry point)
│
├── pom.xml (Maven configuration)
└── README.md (this file)
```

## **How It Works**

- **Coffee Hierarchy:** A well-structured inheritance tree with coffee classes such as `Coffee`, `Cappuccino`,
  `PumpkinSpiceLatte`, `Americano`, and `SyrupCappuccino`, each extending its parent with additional fields.
- **Barista Class:** Acts as the only interface to the coffee classes, ensuring core logic is hidden from external
  access. The Barista handles coffee orders and interactions.
- **Animated Terminal UI:** Provides an immersive experience using the JLine library, handling animated text and dynamic
  user inputs.
- **Encapsulation:** The `Coffee` class and its hierarchy are only accessible through the `Barista` class, following the
  encapsulation principle.

## **Core Components**

- **`Main.java`**: The main entry point, which initiates the coffee shop simulation.
- **`Barista.java`** & **`CoffeMaker.java`**: Handles coffee orders, user interactions, and coffee-making logic.
- **`Coffee Hierarchy`**: Coffee, Cappuccino, PumpkinSpiceLatte, Americano, and SyrupCappuccino classes with shared and
  specific attributes.
- **Common Library Utilities**:
    - `Animated UI`: Terminal-based animated UI components.

---

## **Setup and Run**

### **Prerequisites**

- Java 21
- Maven 3.9+
- Terminal with ANSI support (since the project runs in terminal mode due to animated UI)

### **Build the Project**

```bash
mvn clean install
```

### **Run the Project**

**This project must be run from the terminal due to the animated UI.**

```bash
java -jar target/03-introducing-inheritance-1.0.0-jar-with-dependencies.jar
```