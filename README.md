# Yohan-Pandya-Java-Projects
Thank you for taking a look my Java projects. For some background, these are two projects that I think best represent computer science knowledge and my coding ability. I completed these projects as a part of Princeton's Algorithms Part 1 and 2 courses, on Coursera. 

The first project is 8puzzle2, which is a program that solves an 8 puzzle (feel free to look this up if you don't know what it is). It uses object oriented programming with a "Board" class and a "Solver" class. In addition, it uses SearchNodes as well as two types of distance measurements: manhatten and hamming.  

The second project is Collinear, which aims to find the line with the most amount of points on it when given a set of points on a plane. This project also implements object oriented programming, with each class creating an instance of the point object. In addition, this class finds two ways to create collinear lines: BruteColinnear and FastCollinear. Brute collinear, examines 4 points at a time and checks whether they all lie on the same line segment (by checking if their slopes relative to one another are equal), returning all such line segments. Fast collinear uses arbitrary point p. For each other point q, it determines the slope it makes with p, and sorts the points according to the slopes. It then checks if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p. If so, these points, together with p, are collinear.

Side note: if the "import edu.princeton.cs.algs4.In" line is has a complire error, please download the following JAR and add it as a dependency under modules. 

https://algs4.cs.princeton.edu/code/algs4.jar

Thank you for your time and consideration!

Regards, 
Yohan Pandya.

