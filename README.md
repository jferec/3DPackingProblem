# 3DPackingProblem

There is given a set of cuboids A = {a1, a2, ..., an} represented by Cuboid class and an area, which is the base of the box (Box class) - also a cuboid that we are supposed to fit the Cuboids into (Box class). The goal is to fit the Cuboids into the Box in such way the height of the Box is minimized. There were 4 algorithms implemented: 2 naive ones, Best Area Fit Shelfing and systematic search checking all possible combinations. The results of tests are described in Documentation PDF file. 

To run the visualization we need to run 3DPackingProblem/src/main/java/camera/VisualizationApp.java
with parameters (all required):

(1) algorithm type:
Naive:                      -nn
Naive with sector sorting:  -ns
Shelf Best Area Fit:        -sh
Systematic Search:          -ss

(2) count:
Number of Cuboids (int)

(3) maximum Cuboid edge length

(4) X axis Bin (int)

(5) Y axis Bin (int)

*(4) and (5) must be bigger than (3)

example:
"-sh 200 50 400 400"
translates into running Shelf Best Area Fit algorithm on 200 Cuboids with random edge length (1 to 50) and Bin with base of size 400 x 400. 

