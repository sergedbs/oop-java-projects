# **Common Library – OOP Java Projects**

The **Common Library** provides reusable utilities and components for all Object-Oriented Programming (OOP) Java projects in this repository. It ensures modularity, code reusability, and consistent functionality across different subprojects.

---

## **Overview**
This library includes essential tools for:
- **JSON operations** using Jackson.
- **File handling and validation.**
- **Command-line argument parsing.**
- **Text file loading.**
- **Terminal-based UI components** with animations using JLine.

## **Project Structure**
```
common
│
├── src
│   └── main
│       └── java
│           └── org.sergedb.oop.common
│               ├── utils (FileUtils, JsonUtils, ArgumentParser, TextLoader)
│               └── ui (Animated UI components: BaseAnimatedUI, DisplayScreenUI, TextInputUI, DisplayScreenUI)
│
├── pom.xml (Maven configuration)
└── README.md (This file)
```

## **Utilities Provided**
- **`JsonUtils`**: Handles JSON file reading, writing, and flexible key extraction.
- **`FileUtils`**: Manages file operations like existence checks, emptiness validation, and directory creation.
- **`ArgumentParser`**: Parses command-line arguments into structured key-value maps.
- **`TextLoader`**: Loads and reads text files from provided paths.
- **`UI Components`**: Terminal-based UI utilities for animations, menu selection with arrow keys, and text input, built with JLine.

## **Usage in Subprojects**
Add the `common` library as a dependency in any subproject's `pom.xml`:
```xml
<dependency>
    <groupId>org.sergedb.oop</groupId>
    <artifactId>common</artifactId>
    <version>1.0.0</version>
</dependency>
```

## **Setup and Build**
- **Build the library independently:**
  ```bash
  mvn clean install
  ```
- This installs the `common` library into the local repository for use by other subprojects.
