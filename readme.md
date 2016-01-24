Connect-N
====================

Authors:
* Daniel Beckwith ([dbeckwith](http://github.com/dbeckwith))
* Aditya Nivarthi ([SIZMW](http://github.com/sizmw))

Purpose:
* The purpose of this project is to demonstrate an artificial intelligence that plays Connect-N. It uses Minmax and Alpha Beta pruning to determine what moves to make on each turn.

Execution:
* This program needs to be run as such:
```
anivarthi-djbeckwith-connectn.jar quad
```
where "quad" is the argument that sets the heuristic function and is required for execution.

* To run with the referee, the execution will look like this:
```
java -jar referee.jar "java -jar anivarthi-djbeckwith-connectn.jar quad" "<arguments-for-other-player-jar>" 6 7 4 5 5
```
where <arguments-for-other-player-jar> would be replaced with the opponent JAR and its arguments. The last 5 arguments specify the height of the board, width of the board, consecutive pieces needed to win, time to return player names, and time to make move.

