#Report SDE Project 2014

##Task 1
##Task 2
##Task 3


###Task 3.1

For evaluating the quality of our profiler we did the following:
1. For a chosen standalone java application - in our case a game of life simulation - we measured the total time and time prime during a fixed amount of time. 
2. We performed for the exactly the same amount of time the identical steps and performed a second profiling relying on an official, well-established profiler ([ourkid](http://www.yourkit.com/)).
3. 

For comparing the quality of our profiler we compare its the measurements of some of its profilings - total time and prime
From PLACE 


first column corresponds to measured **total time**.
second column corresonds to measured **prime time**.

```matlab
them = sort(theirProfiling(:,1), 'descend');
us = sort(ourProfiling(:,1), 'descend');
confidence = corr(us, them, 'type', 'Spearman')
```

confidence `0.8997`



