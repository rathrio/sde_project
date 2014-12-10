#Report SDE Project 2014

##Task 1
##Task 2
##Task 3


###Task 3.1

For evaluating the quality of our profiler we did the following:
1. We chose a simple, standalone java application. In our case a _Game of Life_ simulation that exhibits about 25 classes. 
2. For a fixed amount of time (10 tics) and a known sequence of operations, we performed a profiling of this application and measured the total time spent inside a method (denoted by tital time) and the time exclusively spent in this method (i.e. without any external calls, denoted by time prime).
3. Using an official, well-established profiler ([ourkid](http://www.yourkit.com/)), we performed for exactly the same amount of time the identical operation sequence a profiling and measured the same variables (times). 


first column corresponds to measured **total time**.
second column corresonds to measured **prime time**.

```matlab
them = sort(theirProfiling(:,1), 'descend');
us = sort(ourProfiling(:,1), 'descend');
confidence = corr(us, them, 'type', 'Spearman')
```

confidence `0.8997`



