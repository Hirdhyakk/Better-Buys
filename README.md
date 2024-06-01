# Laptop Store Project

Welcome to my Laptop Store project repository. This project is a web-based application for purchasing laptops, developed using HTML, CSS, and Java.

## Introduction

This project is a Laptop Store management system that allows users to browse and purchase laptops. It includes functionality for viewing laptop details and making purchases.

## Features

- View a list of available laptops
- View details of individual laptops
- Add laptops to the cart
- Purchase laptops

## Technologies Used

- HTML5
- CSS3
- Java

## Installation

To get a local copy up and running, follow these simple steps:

1. **Clone the repository**

    ```bash
    git clone https://github.com/Hirdhyakk/Better-Buys.git
    ```

2. **Navigate to the project directory**

    ```bash
    cd Better-Buys
    ```

3. **Set up the database**

    - Create a MySQL database (or use any other database) and configure it in the project.
    - Run the SQL scripts provided in the `sql/` directory to set up the necessary tables.

4. **Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse "preferrend is Netbeans")**

5. **Configure the database connection**

    - Update the database connection settings in the `config.properties` file or directly in the Java code where the connection is established.

6. **Compile and run the application**

    - Use your IDE's build tools to compile the project, or run the following command in the terminal:

    ```bash
    javac -d bin src/*.java
    java -cp bin com.yourpackage.Main
    ```

## Usage

Open `index.html` in a web browser to view the Laptop Store website. Use the interface to browse laptops, view details, and make purchases.
