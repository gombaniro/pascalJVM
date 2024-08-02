# PascalJVM

PascalJVM is a Pascal programming language interpreter/compiler implemented on the Java Virtual Machine (JVM). This project allows you to write and execute Pascal code using the power of the JVM.

## Table of Contents
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Building the Project](#building-the-project)
- [Contributing](#contributing)
- [License](#license)

## Features
- **Pascal Language Support**: Supports core Pascal syntax and constructs.
- **JVM Integration**: Executes Pascal code on the JVM.
- **Cross-Platform**: Runs on any platform that supports the JVM.
- **Extensible**: Easily extendable to support more Pascal features and libraries.

## Prerequisites
- **Java Development Kit (JDK)**: Version 8 or higher.
- **Apache Maven**: Version 3.6.0 or higher.

## Installation
1. **Clone the repository**:
    ```sh
    git clone https://github.com/gombaniro/pascal-compiler.git
    cd PascalJVM
    ```

2. **Build the project using Maven**:
    ```sh
    mvn clean install
    ```

## Usage
1. **Write your Pascal code** in a file, for example `example.pas`:
    ```pascal
    program Example;
    begin
        writeln('Hello, Pascal on JVM!');
    end.
    ```

2. **Run the Pascal code** using the provided interpreter:
    ```sh
    java -jar target/pascaljvm-1.0-SNAPSHOT.jar example.pas
    ```

## Building the Project
To build the project from source, follow these steps:

1. **Clone the repository**:
    ```sh
    git clone git@github.com:gombaniro/pascal-compiler.git
    cd pascal-compiler
    ```

2. **Build with Maven**:
    ```sh
    mvn clean package
    ```

This will generate a JAR file in the `target` directory which can be used to run Pascal programs.

## Contributing
We welcome contributions to the project! Here are some ways you can help:

- **Reporting Bugs**: If you find a bug, please report it using the issue tracker.
- **Fixing Bugs**: If you can fix a bug, fork the repository, make your changes, and submit a pull request.
- **Adding Features**: If you want to add a feature, please discuss it first by opening an issue.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

## Acknowledgements
- This project is inspired by the classic Pascal language and its implementations.
- Thanks to all contributors and users who have helped improve this project.

---

Feel free to reach out with any questions or feedback. Happy coding!

