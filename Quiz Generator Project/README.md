# CPSC210 Personal Project


## A Desktop Quiz Generator

### Overview

The proposed *Quiz Application* is designed to provide an interactive platform for users to test their knowledge on various topics. Users will be able to **create** their own tests by constructing questions and corresponding answers in various formats, such as multiple-choice and short answers. The application will provide immediate feedback on performance, and include **a scoring system** to track user progress. The app will also support **storing** existing quizzes for each user, allowing them to track and review their progress over time.

### User Base
This application will be particularly useful for students who can use it to prepare for exams and reinforce their learning. 
### Reasons for Construction this Project
As a student myself, I often struggle to find a platform that allows me to create a comprehensive problem bank, especially when questions are too lengthy to record by hand. A quiz application will not only enable me to store questions for constant review but also to create similar problems to practice and address gaps in my knowledge. This project interests me as it combines my passion for education with practical software development, enhancing my Java programming skills and providing a valuable tool for myself and others.

### User Stories
Phase1 work:
- As a user, I want to be able to *select the type (short answer/ multiple choice)* of quesion that I want to create.

For ShortAnswer Quiz: 
- As a user, I want to be able to *add a new short answer questions* to my question list.
- As a user, I want to be able to *remove a new short answer questions* to my question list.
- As a user, I want to be able to *view a list of questions* from my question list.
- As a user, I want to be able to *view the correct answer* of the question. 

- (This phase of works have been attributed to the following sources: 
    - Flashcard.java)

Phase2 Work: 
- As a user, when I select the quit option from the application menu, I want to be reminded to save my question list to file and have the option to do so or not.
- As a user, when I start the application, I want to be given the option to load my question list from file.

- (This phase of works have been attributed to the following sources: 
    - JsonSerializationDemo.java)

Phase3 Work: 
- As a user, I want to be able to have a add button that allow me to add questions to the quiz.
- As a user, I want to be able to have a remove button that allow me to remove questions from the list.
- As a user, I want to be able to have save/load buttons that allow me to save/load questions from the quiz file.
- As a user, I want to be able to view all the questions created and view the right answer of each. 

- (This phase of works have been attributed to the following sources: 
    - AlarmSystem.java
    - DrawingPlayer.java
    - Java Tutorials Code Sample – ListDemo.java)

Phase 4: 

Task 2: 
Wed Aug 07 12:22:09 PDT 2024
Added questions: 1: What is 1+1? 2
Wed Aug 07 12:22:11 PDT 2024
View Questions
Wed Aug 07 12:22:21 PDT 2024
Remove question by ID: 1
Wed Aug 07 12:22:23 PDT 2024
View Questions

Task 3: UML Diagram: [UML](UML_Design_Diagram.pdf)

**Reflection:**
Since there can be multiple types of questions presented in the quiz, a potential refactoring would be to introduce an interface or abstract class for managing collections of questions. Currently, the ShortAnswerQuiz class directly manages a list of questions. By introducing a QuestionCollection interface, the ShortAnswerQuiz class can be decoupled from the specific implementation details of managing the question list. This interface could include methods such as addQuestion, removeQuestion, and getQuestions, allowing for interchangeable implementations. Additionally, separating the concerns of the ShortAnswerReviewerGUI where the EventLog is printed can be achieved by creating a Printer class to handle message printing. This class could also be used by the ChoiceReviewerGUI(which will be intruduced later on) to perform similar tasks.

More to add on later: 
For Multiple Choice Quiz: 
- As a user, I want to be able to *add new choices* to my answer choice list.
- As a user, I want to be able to *add and remove new MC questions* to my question list.
- As a user, I want to be able to *check my answer* and get a *mark* on my attempt.