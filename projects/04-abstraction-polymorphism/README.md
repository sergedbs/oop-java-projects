# **Abstraction, Polymorphism, Composition and Tests – Car Service Application**

This project implements a **Car Service Application** to demonstrate advanced **Object-Oriented Programming concepts** including **abstraction, polymorphism, composition, dependency inversion (DI), inversion of control (IoC)**, and **scheduling**.

---

## **Project Overview**

This project is designed to:

- **Process cars from JSON files** generated by a provided Python script.
- **Implement queue system** for managing cars in FIFO order.
- **Provide car services** for charging/refueling and dining.
- **Utilize dependency Injection and IoC** for flexible, modular service stations.
- **Schedule tasks** for periodic car servicing (separate reader and server schedulers).
- **Perform unit tests** covering all components.

## **Project Structure**

```txt
04-abstraction-polymorphism
│
├── src
│   ├── main
│   │   └── java/org.sergedb.oop.abstraction
│   │       ├── dispatcher (Semaphore dispatcher)
│   │       ├── models (Car model)
│   │       ├── queue (Queue interface and implementations)
│   │       ├── scheduler (ScheduledCarReader, ScheduledCarServer)
│   │       ├── services (Dineable, Refuelable, service implementations)
│   │       ├── station (CarStation class)
│   │       ├── utils (LogBuffer, StatsTracker)
│   │       └── Main.java (Main entry point)
│   ├── test (Unit tests for all components)
│   └── resources
│
├── queue (JSON car files)
├── script (Car generator scripts)
├── pom.xml (Maven configuration)
└── README.md
```

## **How It Works**

- **Queues cars** from JSON files using a scheduled reader.
- **Serves cars** based on type and passenger attributes using a scheduled server.
- **Two scheduled tasks**: one for reading new cars, one for serving cars, both with inactivity timeouts and automatic shutdown.
- **Outputs statistics** matching the generator script.
- **Unit tests** validate queue operations, services, station logic, and scheduler timing.

## **Setup and Run**

### **Prerequisites**

- Java 21
- Maven 3.9+

### **Build and Run**

```bash
mvn clean install
mvn exec:java -Dexec.mainClass="org.sergedb.oop.abstraction.Main"
```

This will initialize the system, process cars, and output final statistics.
