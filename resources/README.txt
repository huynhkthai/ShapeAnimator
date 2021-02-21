It all starts with the Shape interface.
Shape interface declares all the getter methods and all the mutation methods.
The AbstractShape abstract class implement Shape interface.
AbstractShape has 2 constructors,  one takes in 10 variables as follow:
name, type,  x, y, parameter1, appear, disappear, colorR, colorG, colorB
The other takes in 11:
name, type,  x, y, parameter1, parameter2, appear, disappear, colorR, colorG, colorB
This is to support creation of shape that needs only 1 input and shape that needs 2
Both constructors have appropriate conditionals to filter out bad input.
This abstract class implements all getter and mutation methods with appropriate conditionals to filter out bad input.
For move and changeColor, these methods are straight forward and changes appropriate object's variables also with appropriate conditionals.
There are 2 ways to mutate the shape of an object, either by factor or by manually changing its parameters, these are done via scale to mutate by factor or resize to mutate manually.
Resize then call resizeP1(resize parameter1) and resizeP2(resize parameter2) appropriately.
There are 3 concrete classes that extend AbstractShape: Circle, Oval and Rectangle.
For Circle, the constructor is hooked directly to the first constructor in the abstract class and information would be processed there.
Circle has a radius getter function, a toString that would output the "animation text" as close to required as possible.
Circle class has 2 ways to mutate, scale or resize that takes 3 arguments, radius, time from and time to, and will throw if parsed in 4 arguments.

For Oval, the constructor is hooked directly to the first constructor in the abstract class and information would be processed there.
Oval class has 2 getter functions, a toString that would output the "animation text" as close to required as possible.

For Rectangle , the constructor is hooked directly to the first constructor in the abstract class and information would be processed there.
Rectangle class has 2 getter functions, a toString that would output the "animation text" as close to required as possible.

An enum class(ShapeName) is used to determine which type of shape is being generated.

An interface called Animator declares all the create shape methods and its mutation.
The AnimatorImpl concrete class implement the Animator interface and implements all declared methods in the Animator interface, this class utilize the 3 concrete shape classes to create the actual shapes, it exist mainly as a string builder and keep track of all the changes for the animation text output, all the work is done in the models.
There are test classes for each shapes and AnimatorImpl with basic to edge cases initializations and mutations, all the conditional to throw are also to be tested here.


Changes within the second part of the assignment are views. They are structured as followed:
AnimationReader and AnimationBuilderImpl work hand in hand to create all necessary information for the views to show off what is in store animation wise.
AnimationBuilderImpl serves as our model. It creates the canvas, the start and final shapes as well as frames of animation.
Each frame of animation is represented by a Frame object, a Frame object is an object that holds information of a Shape object that holds the before(in before and after) information of a frame (which was executed from the previous assignment).
A Frame object also holds information of the after of the frame but instead of holding it in another shape, which requires creating a different constructor in the AbstractShape and Shapes we store them as loose variables.
Frames of animation is called an animationBook(an arrayList of Frame), this has all the information needed to create a view, simply going through animationBook with a for loop and show the before and after of each frame.
A Frame object also holds an array list of frame to keep track of all motions event on a certain shape.
Visual view utilize JFrame and JPanel in java Swing. Our animation happens in the JPanel.
In the animation, a list of frames that holds array list of frames is used. One will traverse through every item of this list to create a list of Shape to be painted.
List of shapes is traversed and painted by paintComponents, paintComponents is triggered by actionPerform which is triggered by a timer with user's specified delay between frames.

Final Assignment Part 10:
Changes: The model works in a similar fashion as before but with the added compatibility of the Visual view as it was left incomplete last time.
It now creates an array of shape frames as before, which holds an array holding the entire history of the shape to its most current frame.

Additions:
The playback view was added by utilizing the JPanel (Visual Panel) from the Visual view. The JPanel was given certain key features such as an indicator that allows it to be manipulated by the playback view.
The indicator is used to trigger by the ActionHandler based methods in the playback view.
Indicator specifics: 0 would signify the normal course of the animation. 1 allows the animation to rewind and play backwards, 2 signifies that the animation must be paused or stopped and 3 signifies that animation
must be looped.

The playback view utilizes the ActionHandler class called ButtonClicks, which is nested within it so that it can access the information more readily. It then manipulates the VisualPanel in the aforementioned ways to achieve the desired output.

The Controller: The controller acts as the trigger mechanism that ensures that the users input is parsed correctly and the appropriate information from the model is retrieved and passed on to the appropriate builder.
It does so by utilizing several methods that are ultimately called in the correct order to ensure that the animation is viewed.

Testing: Testing for the controller was done in the required test class. It goes through all the methods and use cases to ensure that the controller works effectively
and is passing on the correct information as well as handling errors properly. Two tests have been commented out test the error handling capability for two specific use cases. These tests are functional and correct as they use JOption Panel
for the handling. They are commented out due to the server taking 5 points off. They are included as comments for the completeness of the testing suite.

Note: The Animator and AnimatorImpl class and interface are no longer used as a design choice. We were informed that those were not correct design standards and we created the AnimationBuilderImpl to replace the functionality.
They have not been removed as the test classes from Assignment 7 rely on them. Everything else remains the same.