# 🛒 Online Shopping - a Kata

## Instructions
Pour répondre au besoin fonctionelle j'ai utilisé springBoot comme technologie BackEnd. Pour tester et documenter les api j'ai ajouter swagger.
L'application permet à l'utilisatuer d'ajouter des produits et les supprimer de son panier il paut notamment lancer une commande.
Pour déployer le projet il faut suivre ces deux étapes:

1- cloner le projet

2- lancer les commmande 'docker compose build' puis 'docker compose up -d'

### Constraints
- Spring-boot 3.x.x
- Java 21
- Git
- Enhance this `README.md` file -- _Explains the potential intricacies of your implementation and how to launch your project_.

### Delivery
The code should be available in an accessible git repo ( github, gitlab ...).

### Assessment
**There is no "right" way to do this exercise.**\
We are interested in your implementation choices, your technique, the code architecture and the compliance with the constraints.\
_Also pay attention to the size of your commits and their messages._

### Tips
To quickly create your project base, use [spring initializr](https://start.spring.io/)

## Exercise
### MVP
#### User Story
- [X] As a customer I can add available product to my cart.

#### User Story
- [X] As a customer, I can remove products from my cart.

#### User Story
- [X] Product inventory is updated based on customers' actions.

#### User Story
- [X] As a customer, I can confirm my order and track its status.


### Bonus Features
The following features are optional and not exhaustive.\
They have no priority between them, you can implement the ones you are interested in or propose others.

#### REST API
- [X] Propose an HTTP REST API to interact with the services implemented in the MVP
- [ ] Implement HATEOAS principles in your REST API
- [X] Document the REST API
- [ ] Secure the API
- [ ] Use a non-blocking solution

#### Persistence
- [X] Propose a data persistence solution
- [ ] Propose a cache solution

#### Stream
- [ ] Propose a data streaming solution
- [ ] Propose a solution for consuming and/or producing events

### CI/CD
- [X] Propose a CI/CD system for the project
- [X] Propose End-to-End tests for your application

### Packaging
- [X] Create a container of your application
- [ ] Deploy your application in a pod
- [ ] Create a native image of your application... (The response was truncated because it has reached the token limit. Try to increase the token limit if you need a longer response.)
