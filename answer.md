#Answers SDE Project 2014

For our design decisions and limitations, please have a look at our [report](https://github.com/rathrio/sde_project/blob/master/report.md).

##Task 1

We picked the jruby 1.5.2 mse file.

```smalltalk
invocations := (MooseModel root first allInvocations) .
classes := (MooseModel root first allClasses) first: 100.

view := RTView new.
elements := (RTBox new color: Color pink) elementsOn: classes.
view addAll: elements.

maxI := 0 .
((MooseModel root first allMethods)) do: [ :m| 
	c_m := m invokedMethods size .
	(c_m > maxI) ifTrue: [ maxI := c_m ]
	 ] .
maxI .

logBase := 2 .
RTNest new for: elements add: [:group :class | 
	|innerShapes|
	
	innerShapes := class methods collect: [ :m |
		|invokedCount c innerShape list colorWeight|
		invokedCount := m invokingMethods size.
		colorWeight :=  ((invokedCount log: logBase) max: 0 - (maxI log: logBase)) asFloat.
		c := Color r: 0 g: colorWeight b: (1 - colorWeight).
		innerShape := RTEllipse new color: c.
		list := OrderedCollection new.
		list add: m.
		innerShape elementsOn: list].

	innerShapes do: [ :shape |
		group addAll: shape
		 ].
	 RTGridLayout on: group. group @ (RTPopup new textElement:[:e | e model signature])].

edges := RTEdge buildEdgesFromObjects: invocations from:#sender toAll: [:i | 


    result := OrderedCollection new. 
    cands := i candidates. 
    result addAll: cands. 
    cands do:[:c | subClasses := c parentType subInheritances collect: [:inh | inh subclass]. 
    subClasses do: [:class | class methods do: [ :m | (m signature = c signature) ifTrue:[result add: m]]]]. result.


] using: RTArrowedLine new inView: view.

RTCircleLayout on: elements.
edges @ (RTPopup new textElement:[:e | ('from: ', e from model belongsTo name asString, '>>', e from model signature asString, Character cr asString, 'to: ', e to model belongsTo name asString, '>>', e to model signature asString)]).
elements @ RTDraggable.
elements @ (RTPopup new textElement:[:e | e model name]).
elements @ (RTMenuActivable new action:#inspect).
view @ RTDraggableView.
view.
```

### Some Screenshots

![Circle](https://github.com/rathrio/sde_project/blob/master/Screen%20Shot%202014-12-10%20at%2002.16.37.png)

* We only considered about 100 classes because our computers froze up when we tried to load all of the 3285 classes in.


![https://github.com/rathrio/sde_project/blob/master/Screen%20Shot%202014-12-10%20at%2002.17.59.png](https://github.com/rathrio/sde_project/blob/master/Screen%20Shot%202014-12-10%20at%2002.17.59.png)

* Arrows point from sender to receiver of a call
* We color-coded all methods on a scale from blue to green (linear log-scaled color gradient). The more a method is invoked, the greener it is.
* All classes are colored in pink because this inceased the power of He-Man.
* You may note that some methods are green even though you can't see many arrows pointing to it. That's also because we only picked the first 100 classes, but considered all Invocations nevertheless. Feel free to up the class count. And get a coffee.


The following snippet creates a list of methods in the requested format:

```smalltalk
Transcript open.
Transcript show: 'Method Full Name, LOC, NOIC, LOC*NOIC'.

((MooseModel root first allMethods)) do: [ :method |
	Transcript show: method asString , ', ' , method numberOfLinesOfCode asString , ', ' , method invokingMethods size asString , ', ' , (method numberOfLinesOfCode * method invokingMethods size) asString , String cr.]
```

Legend: 

+ **LOC** Lines of Code.
+ **NOIC** Number of incoming calls.

**Remark**: Note that execuating this snipped can take a while. 

##Task 2

For solving the this and all successive tasks of this project we are reyling on a [Game of Life](http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life) simulation written in Java. You can find the source of the application at http://www.bitstorm.org/gameoflife/standalone/.

###a) 

See [here](https://github.com/rathrio/sde_project/blob/master/src/sde/Main.java) for source.

###b) 

We couldn't do this, mainly because we looked at two different java projects for reasons defined in the [report](https://github.com/rathrio/sde_project/blob/master/report.md). 

###c)

See [here](https://github.com/rathrio/sde_project/blob/master/homebrew_javassist_results.txt).

###d)

See [here](https://github.com/rathrio/sde_project/blob/master/off_shelf_results.csv)

##Task 3

###a) + b)

For evaluating the quality of our profiler we did the following:

1. We chose a simple, standalone java application. In our case a _Game of Life_ simulation that exhibits about 25 classes.

2. For a fixed amount of time (10 tics) and a known sequence of operations, we performed a profiling of this application and measured the total time spent inside a method (denoted by tital time) and the time exclusively spent in this method (i.e. without any external calls, denoted by time prime).

3. Using an official, well-established profiler ([yourkit](http://www.yourkit.com/)), we performed for approximitly the same amount of time the identical operation sequence a profiling and measured the same variables (times). 

4. For each measured random variable (total time, time prime) we computed their Spearmean Correlation.

Measured Pofiling data sets:

+ [Ours](https://github.com/rathrio/sde_project/blob/master/homebrew_javassist_results.txt): For this data-set, **Total Time** corresponds to **total** and **Time Prime** to **prime**.
+ [Yourkit's](https://github.com/rathrio/sde_project/blob/master/off_shelf.csv): For this data-set, **Total Time** corresponds to **time** and **Time Prime** to **own time**.

For computing the [Spearman Correlation](http://en.wikipedia.org/wiki/Spearman%27s_rank_correlation_coefficient) we implemented the following Matlab script:

```matlab
% compute the spearman correlation for the random variable 'total time'
them_total = sort(theirProfiling(:,1), 'descend');
us_total = sort(ourProfiling(:,1), 'descend');
correlation_total_time = corr(us, them, 'type', 'Spearman')

% compute the spearman correlation for the random variable 'time prime'
us = sort(ourProfiling(:,2), 'descend');
their = sort(theirProfiling(:,2), 'descend');
correlation_time_prime = 1-(6*sum((us-them).^2)) / ((length(us)^2) - 1)*length(us)
```

Correlation for **correlation_total_time** is then equal to `0.8997`. This means that our positive-storng correlate to the measurments resulting from  _yourkit_.

The correlation **correlation_time_prime** is equal to `-0.3500`. This means that our measurements negative-weakly correlate to the measurements resulting from _yourkit_. One possible reason for this is that most of the measured values were equal to zero when using the time unit *miliseconds*. This implies that only few random value events contributed to the correlation (due to the delta summation in the spearman's formula). However, we think that if we would have used nanoseconds (instead of miliseconds) fewer values would be non-zero and thus the correlation would alter (refering to the formula for computing the spearman correlation) closer to a reliable correlation coefficient.



