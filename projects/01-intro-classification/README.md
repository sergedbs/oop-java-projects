# **Multiverse Individual Classifier – Intro Into Classification**

This project is inspired by *Papers, Please*, with a twist! It classifies individuals from different fictional universes
that accidentally collided with our Moldavian Universe due to a coding mishap. The mission: classify these individuals
correctly and send them home!

---

## **Story Background**

In the distant future, a Technical University of Moldova intern accidentally pushed untested code to the multiverse
repository, merging our universe with several fictional ones:

- **Star Wars**
- **Marvel**
- **The Hitchhiker’s Guide to the Galaxy**
- **The Lord of the Rings**

This classification system helps these characters find their way back to their respective universes!

## **Project Structure**

```
01-intro-classification
│
├── src
│   └── main
│       ├── java
│       │   └── org.sergedb.oop.classification
│       │       ├── classification (contains Classifier and ClassificationRules)
│       │       ├── models (contains Individual and Universe classes)
│       │       └── Main.java (entry point)
│       └── resources
│           ├── input.json (list of individuals to classify)
│           └── output (directory for classified output files)
│
├── pom.xml (Maven configuration)
└── README.md (this file)
```

## **How It Works**

- **Input:** `input.json` contains individuals with attributes like `planet`, `age`, `traits`, etc.
- **Classification Logic:** `ClassificationRules` checks attributes and classifies each individual into one of the four
  universes.
- **Output:** The classified individuals are written into separate JSON files for each universe in the `output` folder.

## **Core Components**

- **`Main.java`**: Starts the classification process.
- **`Classifier.java`**: Reads individuals from JSON, classifies them, and writes output.
- **`ClassificationRules.java`**: Contains rules for classification.
- **`JsonUtils.java`**: Handles JSON read/write operations.
- **`FileUtils.java`**: Manages file operations.
- **`Individual` and `Universe`**: Models representing individuals and universes.

## **Classification Logic**

- **Star Wars**: Kashyyyk (Wookiees) and Endor (Ewoks).
- **Marvel**: Asgardians.
- **Hitchhiker’s**: Betelgeusians and Vogons.
- **Lord of the Rings**: Elves and Dwarves.

*If any attribute is missing (e.g., planet), the system tries all classification rules until a match is found. All the
classification rule can be found [HERE](classification.md).*

## **Output Example**

Each universe will have its JSON file in the `output` folder:

- `STAR_WARS.json`
- `MARVEL.json`
- `HITCHHIKERS.json`
- `LOTR.json`
