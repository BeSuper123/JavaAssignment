# Student Graduation Predictor

**Author:** Blessing Ugochukwu  
**Theme:** To predict if a student will graduate based on the 4 features

## Project Description
This Java Project is a predictor tool using the GUI to determine if a student is going to pass or not.
It is based on data from a file which is catagorised has data from a training dataset.

---

## Class Descriptions

### `FileProcessor` Class:
The purpose of this class is to handle reading and writing from the frequency table
This means *opening* the file, *reading* it and *writing* to it. It also builds a *frequency table* data inside.

### `MyWindow` Class:
The main GUI class and the purpose of it is to *display* all the text fields and buttons on the screen and allows users to add more data to the csv file.
This class also *displays* all the predictive data for the student features, this means containing the display version of the frequency table, and also having an option to check what chance a student has of graduating

### `Student:`
A model class representing one student's data and purpose of it is to *take in* student information and *format* the new student data from the user to append into the file.

### `Predictor:`
It trains a Naive Bayes model dynamically using the dataset given. It predicts the labels (yes or no) for the students. 
Calculates probabilities for each possible label (16 probable oucomes).

### `Control:`
This is where the main method is and the purpose of this class is to *display* the screen with all the details of it from the **MyWindow** table

---
## Functionality
- The GUI is used for the text fields *(user input)* and submitting student data.
- Predictions using the hardcoded information from given csv.
- Dynamic Naive Bayes clasifier training.
- Display the frequency table with features using percentages.
- Predict button predicts the percentage of an outcome using the hardcoded csv
- Acurracy checker trains 150 students and tests on 50 students.
- Displays correct predictions as well as the model's accuracy.

---

## Future Improvements
- Add error checking to restrict input values to the expected values (e.g., studyhours = high/low).
- Auto update the dataset after a new entry.
- Show prediction probabilities alongside the predicted label.
- Save the results to a log file.
- Style the GUI better with a better flow and design inluding better colour contrast.

---

## Demo Video
YouTube Video - .....

---

## Git Repository
Link - [.....](https://github.com/BeSuper123/JavaAssignment)

---