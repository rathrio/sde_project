Final Report
============

### Design decisions, interesting problems and solutions

* For the first task (static callgraph with roassal), we noticed that the further you zoom out, the less useful the graph gets,
so we decided to color methods with a high call count differently. This way you can identity them from "afar".

* Deciding what and how to profile was harder than expected. We picked the jruby source for our first task and we tried to do the hotspot analysis for it, too, but we felt a little lost in the beginning. Which classes matter in a programming language implementation with such a huge code base? How the heck do we run this thing to get some sensible results?! We then only considered the parser package (~ 20 classes, whereas the main parser class will make your eyes bleed) and ran it on some sample files, but we encountered really random bugs and decided to choose a smaller, not too complex project (a game of life, ~ 25 classes). Another reason for ditching jruby was that we didn't manage to profile it with our off-shelf profiler (yourkit), since it required the java program to run for a while to "attach" to it.

* 



### Limitations

* Radi's Java skills suck pretty bad, but he learned some things about JARs
* Our tPrime calculation is a little shaky. Its implementation pretty straight forward, but the results (tPrime is almost always close to 0) made us question its correctness.
* 

