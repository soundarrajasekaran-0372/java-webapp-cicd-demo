# Java Web Application - CI/CD with Jenkins & Tomcat Deployment

## Project Overview

This project demonstrates a simple Java web application and the fundamentals of a CI/CD pipeline using Maven, Jenkins, GitHub, and Apache Tomcat.

The project starts with a manual build and deployment process and will progressively be automated using Jenkins.

## Architecture

```text
Developer
    |
    | git push
    v
  GitHub
    |
    | Jenkins
    v
  Jenkins
    |
    | Maven Build
    v
 WAR Artifact
    |
    | Deployment
    v
 Apache Tomcat
    |
    v
 Java Web Application
```

## Technologies

- Java 21
- Maven
- Git
- GitHub
- Jenkins
- Apache Tomcat 10.1
- Ubuntu 24.04 LTS
- WSL2

## Environment

This project is currently developed and tested in a local WSL2 environment running Ubuntu 24.04 LTS.

The current environment is intended for learning, development, and hands-on CI/CD practice rather than production deployment.

The application is currently accessible locally through Apache Tomcat on:

```text
http://localhost:8080/java-cicd-demo/
```

## Current Project Status

### Phase 1 - Manual Build and Deployment

- [x] Create Java web application
- [x] Configure Maven
- [x] Build the application using Maven
- [x] Generate WAR artifact
- [x] Install Apache Tomcat
- [x] Deploy WAR manually to Tomcat
- [x] Verify the application through HTTP

### Phase 2 - Git and GitHub

- [x] Initialize Git repository
- [x] Create `.gitignore`
- [x] Create initial Git commit
- [x] Create GitHub repository
- [x] Configure SSH authentication
- [x] Push source code to GitHub

### Phase 3 - Jenkins CI

- [ ] Install Jenkins
- [ ] Connect Jenkins with GitHub
- [ ] Configure Jenkins job
- [ ] Automatically checkout source code
- [ ] Run Maven build
- [ ] Generate WAR artifact

### Phase 4 - Jenkins CD

- [ ] Deploy WAR automatically to Tomcat
- [ ] Verify deployment
- [ ] Trigger pipeline from GitHub changes

## Maven Build

The application is packaged as a WAR file using Maven.

Build command:

```bash
mvn clean package
```

The generated artifact is:

```text
target/java-cicd-demo.war
```

## Manual Deployment

The WAR artifact can be deployed manually to Tomcat:

```bash
cp target/java-cicd-demo.war /opt/tomcat/webapps/
```

Tomcat automatically detects the WAR file and deploys the application.

The application can then be accessed at:

```text
http://localhost:8080/java-cicd-demo/
```

## Application

The application is a simple Java Servlet that returns an HTML response.

Expected response:

```text
Hello from Soundar's DevOps Pipeline!

Application built using Maven and deployed to Tomcat.
```

## Learning Objectives

The main goal of this project is to understand how the individual DevOps components work together:

```text
Source Code
    ↓
Git
    ↓
GitHub
    ↓
Jenkins
    ↓
Maven
    ↓
WAR Artifact
    ↓
Tomcat
    ↓
Running Application
```

The project will be progressively improved to demonstrate CI/CD automation and eventually cloud deployment.

## Future Improvements

- Jenkins Pipeline
- GitHub webhook integration
- Automated Maven build
- Automated testing
- Automated WAR deployment
- Deployment verification
- Azure VM deployment
- Infrastructure automation
- Docker-based deployment
- Azure DevOps integration
