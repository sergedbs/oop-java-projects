# **Working with Classes**

This project explores key Object-Oriented Programming concepts such as classes, objects, inheritance, and file handling.
It implements multiple tasks including comparing display devices, processing text files, and managing a virtual
assistant, all while demonstrating clean OOP design.

---

## **Project Overview**

This laboratory is designed to:

- **Compare different display devices** using attributes like resolution, PPI, and model.
- **Parse and analyze text files** to extract data such as word count, frequency, and statistics.
- **Implement a virtual assistant** to interact with the user and perform tasks based on JSON-configured displays data.

## **Project Structure**

```
02-working-with-classes
│
├── src
│   └── main
│       ├── java
│       │   └── org.sergedb.oop.classes
│       │       ├── Main
│       │       ├── tasks
│       │       │   ├── display (Display and DisplayComparator classes)
│       │       │   ├── text (TextParser and TextData classes)
│       │       │   └── assistant (Assistant and AssistantManager classes)
│       │       └── runner (Argument parsing and task execution)
│       └── resources (JSON and text input files)
│
├── pom.xml (Maven configuration)
└── README.md (this file)
```

## **How It Works**

- **Input Files:** JSON files contain data for display devices and assistant configurations, while text files are
  processed for analysis.
- **Task Execution:** `AppRunner` handles the execution of tasks based on command-line arguments.
- **Output:** The results are displayed on the console, including comparisons, text analysis, and user interactions with
  the assistant.

## **Core Components**

- **`Main.java`**: The entry point that runs the application.
- **`AppRunner.java`**: Manages and executes tasks using OOP best practices.
- **`DisplayComparator` and `Display`**: Handle display device comparisons.
- **`TextParser` and `TextData`**: Process and store text analysis data.
- **`Assistant` and `AssistantManager`**: Manage the virtual assistant interactions.
- **Common Library Utilities**:
    - `JsonUtils`: Reads and writes JSON files.
    - `FileUtils`: Manages file operations.
    - `TexxtLoader`: Loads text from files.
    - `ArgumentParser`: Parses command-line arguments.

## **Tasks Implemented**

- **Task 1 – Display Comparison:** Compares displays based on attributes like resolution and PPI.
- **Task 2 – Text Processing:** Analyzes text files to provide insights like word count and frequency.
- **Task 3 – Virtual Assistant:** A JSON-configured assistant that performs tasks and displays information
  interactively.

## **Setup and Run**

### **Prerequisites**

- Java 21
- Maven 3.9+

### **Build the Project**

```bash
mvn clean install
```

### **Run the Project**

```bash
mvn exec:java -Dexec.mainClass="org.sergedb.oop.classes.Main" -- [task_flag]
```

`[OPTIONAL]`

- `--first [displays json path]` – Run display comparison.
- `--second [text file path]` – Run text processing.
- `--third [displays json path]` – Run virtual assistant.
