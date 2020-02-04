This is the first project I made as a part of DKE bachelors program.

The repository consists of 3 phases. Each one performs slightly dependent tasks in the area of packing problems.

You can get a full explanation in statements.pdf that I was given in the university. Here is a brief explanation:


Phase 1:

Simple backtracking algorithm with some pruning that tries to fill a fiedld of customized size with customized set of pentominos.


Phase 2:

Complete tetris game with user-friendly GUI and login system. Implemented in Java Swing in NetBeans IDE. In this application you can play on your own or watch the bot playing.
The bot works as follows: for each pentomino it assesses all possible positions of it with fitness function and chooses the best one. Greedy bot, that is it.
The coefficients for the function were precalculated with genetic algorithm. 


Controls for the game: 

Q and E for rotating left and right respectively.

SPACE for pentomino drop.

UP, DOWN, LEFT and RIGHT perform one-cell move in respective direction.


Phase 3:

This is where things get a little bit more interesting. The GUI consists of two parts, the first implemented in Java Swing(asks for input data) and the second in JavaFX
(animation based on tutorial by ORACLE). The application performs packing of 3D cuboid with cuboids/pentominos and maximizes sum of costs/minimizes the number of gaps.
Packing is done with greedy algorithm that uses a linear function with coefficients precalculated with genetic algorithm(one of four genetic algorithms, that is, one for each possible problem). 
For the search the user have two options: partial search that uses only one set of precalculated coefficients and full search that uses the whole final population of coefficients.


Controls for the animation:

Hold the left mouse button and drag the mouse right or left and up or down to rotate the camera view of the model around the axes.

Hold the right mouse button and drag the mouse to the left to move camera view away from the model. Drag the mouse to the right to move the camera view closer to the molecule model.

SHIFT for mouse movement acceleration.

Press Z to return the model to its initial position.

Press V to show and hide the module from view.

Press X to show and hide the axes.


Running commands:

Phase 1: java build.Runner

Phase 2: java build.Home

Phase 3: java build.Home

Links:

Oracle tutorial: https://docs.oracle.com/javase/8/javafx/graphics-tutorial/sampleapp3d.htm

Awesome article about genetic algorithms application: https://codemyroad.wordpress.com/2013/04/14/tetris-ai-the-near-perfect-player/