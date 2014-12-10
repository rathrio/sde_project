#Report SDE Project 2014

##Task 1
##Task 2
##Task 3
###Task 3.1

first column corresponds to measured **total time**.
second column corresonds to measured **prime time**.

```matlab
them = sort(theirProfiling(:,1), 'descend');
us = sort(ourProfiling(:,1), 'descend');
confidence = corr(us, them, 'type', 'Spearman')
```

confidence `0.8997`



