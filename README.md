# Java Web Application - CI/CD with Jenkins & Tomcat Deployment

## Project Overview

This project demonstrates an end-to-end CI/CD pipeline for a simple Java web application using Git, GitHub, Maven, Jenkins, Apache Tomcat, GitHub Webhooks, Cloudflare Tunnel, and WSL2.

The application is built as a WAR file using Maven and deployed automatically to Apache Tomcat through a Jenkins Declarative Pipeline.

The current implementation is designed and tested in a local WSL2 environment for learning, development, and hands-on DevOps practice.

> **Project Status:** Local CI/CD pipeline completed successfully.  
> **Next Phase:** Cloud-based deployment using an Azure VM.

---

## Architecture

```text
Developer
    |
    | git push
    v
 GitHub
    |
    | GitHub Webhook
    v
 Jenkins
    |
    | Checkout
    v
 Source Code
    |
    | mvn clean package
    v
 WAR Artifact
    |
    | Automated Deployment
    v
 Apache Tomcat
    | Port 8081
    v
 Java Web Application
```

---

## CI/CD Pipeline Flow

```text
Git Push
   |
   v
GitHub
   |
   | Webhook
   v
Jenkins
   |
   +--> Checkout
   |
   +--> Build
   |      |
   |      +--> mvn clean package
   |
   +--> Test
   |      |
   |      +--> mvn test
   |
   +--> Deploy
   |      |
   |      +--> Copy WAR to Tomcat
   |
   +--> Verify Deployment
          |
          +--> curl application endpoint
                   |
                   v
             Tomcat :8081
                   |
                   v
          Java Web Application
```

---

## Technologies

| Technology         | Purpose                                    |
| ------------------ | ------------------------------------------ |
| Java 21            | Application development                    |
| Maven              | Build and package management               |
| Git                | Source code version control                |
| GitHub             | Remote source code repository              |
| Jenkins            | CI/CD automation                           |
| Apache Tomcat 10.1 | Java web application server                |
| GitHub Webhook     | Automatic Jenkins pipeline trigger         |
| Cloudflare Tunnel  | Temporary external access to local Jenkins |
| Ubuntu 24.04 LTS   | Linux environment                          |
| WSL2               | Local development environment              |

---

## Environment

The current project is developed and tested locally using:

```text
Windows
   |
   v
WSL2
   |
   v
Ubuntu 24.04 LTS
   |
   +--> Jenkins :8080
   |
   +--> Apache Tomcat :8081
```

The current environment is intended for:

- Learning
- Development
- CI/CD practice
- DevOps hands-on experience

It is **not a production deployment**.

---

## Services and Ports

| Service | Port | Purpose |
|---|---:|---|
| Jenkins | 8080 | CI/CD server |
| Apache Tomcat | 8081 | Java web application |

Jenkins:

```text
http://localhost:8080
```

Application:

```text
http://localhost:8081/java-cicd-demo/
```

---

## Application

The application is a simple Java Servlet located under:

```text
src/main/java/com/soundar/app/HelloServlet.java
```

The servlet returns an HTML response.

Expected response:

```text
Hello from Soundar's Automated DevOps Pipeline!

Version: 1.0

Application built using Maven and deployed to Tomcat.
```

---

## Project Structure

```text
java-cicd-demo/
│
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── soundar/
│                   └── app/
│                       └── HelloServlet.java
│
├── pom.xml
├── Jenkinsfile
├── .gitignore
└── README.md
```

The Maven build output is generated inside:

```text
target/
```

The `target/` directory is excluded from Git using `.gitignore`.

---

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

---

## Testing

Maven tests are executed using:

```bash
mvn test
```

The current project does not contain automated test classes, so Maven reports:

```text
No tests to run.
```

The Jenkins pipeline still contains a dedicated Test stage so automated tests can be added later.

---

## Manual Deployment

Before implementing automated deployment, the WAR file was deployed manually to Tomcat:

```bash
cp target/java-cicd-demo.war /opt/tomcat/webapps/
```

Tomcat then detects and deploys the WAR file.

Application URL:

```text
http://localhost:8081/java-cicd-demo/
```

---

# Jenkins CI/CD Pipeline

The Jenkins Declarative Pipeline is defined in:

```text
Jenkinsfile
```

The pipeline contains these stages:

```text
Checkout
   ↓
Build
   ↓
Test
   ↓
Deploy
   ↓
Verify Deployment
```

### Checkout

Jenkins checks out the source code from the GitHub repository.

### Build

Maven builds the application and generates the WAR artifact:

```bash
mvn clean package
```

### Test

Maven executes the project's tests:

```bash
mvn test
```

### Deploy

The generated WAR file is copied to the Tomcat webapps directory:

```bash
cp target/java-cicd-demo.war /opt/tomcat/webapps/
```

### Verify Deployment

Jenkins verifies the deployed application:

```bash
curl -f http://localhost:8081/java-cicd-demo/
```

A successful HTTP response allows the pipeline to complete successfully.

---

## Jenkins Pipeline Result

The complete Jenkins pipeline has been successfully tested.

It successfully performs:

```text
GitHub
   ↓
Jenkins Checkout
   ↓
Maven Build
   ↓
Maven Test
   ↓
WAR Generation
   ↓
Tomcat Deployment
   ↓
Deployment Verification
   ↓
SUCCESS
```

The Jenkins pipeline reports:

```text
CI/CD Pipeline completed successfully!
```

---

# GitHub Webhook Integration

GitHub Webhooks are configured to automatically trigger the Jenkins pipeline when changes are pushed to the repository.

Workflow:

```text
Developer
    |
    | git push
    v
GitHub
    |
    | Webhook
    v
Jenkins
    |
    v
Pipeline starts automatically
```

The Jenkins webhook endpoint is:

```text
/github-webhook/
```

The `/github-webhook/` endpoint is used by Jenkins to receive GitHub webhook events.

---

# Cloudflare Tunnel

Because Jenkins is running inside the local WSL2 environment, it is not directly accessible from the public internet.

A Cloudflare Quick Tunnel was used to expose Jenkins temporarily for GitHub webhook testing.

Command:

```bash
cloudflared tunnel --url http://localhost:8080
```

Cloudflare provides a temporary HTTPS URL that can be configured as the GitHub webhook target.

### Important

The Cloudflare Quick Tunnel is used for:

- Learning
- Testing
- GitHub webhook integration

It is **not intended as a production-grade public Jenkins deployment**.

The temporary tunnel URL can change when the tunnel is restarted.

---

# GitHub Repository

Repository:

```text
https://github.com/soundarrajasekaran-0372/java-webapp-cicd-demo.git
```

Repository contents include:

```text
pom.xml
src/
Jenkinsfile
.gitignore
README.md
```

---

# Git Workflow

Typical development workflow:

```bash
git status
git add .
git commit -m "Your commit message"
git push origin main
```

After pushing changes:

```text
Git Push
   ↓
GitHub
   ↓
GitHub Webhook
   ↓
Jenkins
   ↓
CI/CD Pipeline
```

---

# .gitignore

The Maven build directory is excluded from Git.

Current `.gitignore`:

```text
target/
```

This prevents generated Maven build artifacts from being committed to the repository.

The WAR generated by Jenkins is therefore treated as a build artifact rather than source code.

---

# Current Project Status

## Phase 1 - Java Application

- [x] Create Java web application
- [x] Create Java Servlet
- [x] Configure Maven
- [x] Build application
- [x] Generate WAR artifact
- [x] Verify application

## Phase 2 - Manual Tomcat Deployment

- [x] Install Apache Tomcat
- [x] Configure Tomcat
- [x] Configure Tomcat on port 8081
- [x] Deploy WAR manually
- [x] Verify application through HTTP

## Phase 3 - Git and GitHub

- [x] Initialize Git repository
- [x] Create `.gitignore`
- [x] Create Git commits
- [x] Create GitHub repository
- [x] Configure SSH authentication
- [x] Push source code to GitHub

## Phase 4 - Jenkins CI

- [x] Install Jenkins
- [x] Configure Jenkins
- [x] Verify Java 21
- [x] Verify Maven
- [x] Connect Jenkins to GitHub
- [x] Checkout source code
- [x] Run Maven build
- [x] Generate WAR artifact
- [x] Run Maven tests

## Phase 5 - Jenkins CD

- [x] Configure Tomcat deployment
- [x] Configure Jenkins access to Tomcat webapps
- [x] Automatically deploy WAR
- [x] Verify deployment from Jenkins
- [x] Confirm successful pipeline execution

## Phase 6 - GitHub Webhook

- [x] Configure GitHub webhook
- [x] Connect webhook to Jenkins
- [x] Trigger Jenkins automatically after Git push
- [x] Verify automatic pipeline execution

## Phase 7 - Local CI/CD Completion

- [x] Complete CI/CD pipeline
- [x] Test build
- [x] Test deployment
- [x] Test webhook trigger
- [x] Test application version update
- [x] Verify end-to-end automation

---

# End-to-End Result

The local CI/CD project successfully demonstrates:

```text
Developer
    |
    | git push
    v
GitHub
    |
    | GitHub Webhook
    v
Jenkins
    |
    +--> Checkout
    |
    +--> Build
    |
    +--> Test
    |
    +--> Deploy
    |
    +--> Verify
    |
    v
Apache Tomcat :8081
    |
    v
Java Web Application
```

A code change pushed to GitHub can automatically trigger Jenkins, build the application, package the WAR, deploy it to Tomcat, and verify that the application is running.

---

# Learning Objectives

This project demonstrates the fundamentals of:

- Git version control
- GitHub repository management
- SSH authentication
- Maven builds
- Java WAR packaging
- Jenkins CI
- Jenkins Declarative Pipelines
- Jenkins CD
- Apache Tomcat deployment
- GitHub Webhooks
- Automated deployment verification
- Local DevOps environments using WSL2
- Temporary public connectivity using Cloudflare Tunnel

---

# Future Improvements

Possible future improvements include:

- Add automated unit tests
- Improve test coverage
- Add Jenkins credentials management
- Add artifact archiving
- Add deployment rollback
- Add health checks
- Add Docker-based deployment
- Deploy the application to an Azure VM
- Use Azure infrastructure for cloud deployment
- Configure a permanent Cloudflare Tunnel
- Add HTTPS and a custom domain
- Implement Infrastructure as Code
- Integrate Azure DevOps
- Implement production-grade CI/CD practices

---

# Next Project: Cloud-Based Deployment

The current project uses:

```text
Local WSL2
    ↓
Jenkins
    ↓
Tomcat
    ↓
Java Web Application
```

The next phase can move the deployment target to the cloud:

```text
GitHub
    ↓
Jenkins
    ↓
Azure VM
    ↓
Apache Tomcat
    ↓
Java Web Application
```

This will apply the same CI/CD concepts to a cloud-based deployment environment.

---

## Project Status

**Local CI/CD Pipeline: COMPLETED**

**Cloud-Based Azure Deployment: FUTURE PHASE**
