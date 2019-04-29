# A-Star
An implementation of A-Star done for a coursework in Java with data structure implementation for graph creation and navigation.
Graphs are created by loading formatted comma seperated values in .cav files

### Input Format (.cav file) 

- The first integer gives the number of caverns N
- The next N*2 integers give the coordinates of each of the caverns (positive values)
- The final N*N integers give the connectivity of the tunnels. 1 means connected, 0 means not connected. Only unidirectional.

### Run program

- open command line in folder with caveroute.bat (in src) and type the following:
```
caveroute _filename_
```
- make sure to type _filename_ instead of _filename.cav_

### Output

- Program will print duration the pathfinding took, the total cost and create a .csn files containing the ordered nodes of the shortest path. 

### Notes
The dissertation discusses some path finding algorithms and how A* is not only the fastest but also always optimal for this specific problem.
