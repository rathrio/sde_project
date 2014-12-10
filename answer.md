#Report SDE Project 2014

##Task 1

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
##Task 2
##Task 3


###Task 3.1

For evaluating the quality of our profiler we did the following:

1. We chose a simple, standalone java application. In our case a _Game of Life_ simulation that exhibits about 25 classes.

2. For a fixed amount of time (10 tics) and a known sequence of operations, we performed a profiling of this application and measured the total time spent inside a method (denoted by tital time) and the time exclusively spent in this method (i.e. without any external calls, denoted by time prime).

3. Using an official, well-established profiler ([ourkid](http://www.yourkit.com/)), we performed for approximitly the same amount of time the identical operation sequence a profiling and measured the same variables (times). 

4. For each measured random variable (total time, time prime) we computed their Spearmean Correlation.

Measured Pofiling data sets:

+ [Ours](https://github.com/rathrio/sde_project/blob/master/homebrew_javassist_results.txt): For this data-set, **Total Time** corresponds to **total** and **Time Prime** to **prime**.
+ [Ourkid's](https://github.com/rathrio/sde_project/blob/master/off_shelf.csv): For this data-set, **Total Time** corresponds to **time** and **Time Prime** to **own time**.

For computing the Spearman Correlation we used the following Matlab script:

```matlab
% compute the spearman correlation for the random variable 'total time'
them_total = sort(theirProfiling(:,1), 'descend');
us_total = sort(ourProfiling(:,1), 'descend');
correlation_total_time = corr(us, them, 'type', 'Spearman')

% compute the spearman correlation for the random variable 'time prime'
them_prime = sort(theirProfiling(:,2), 'descend');
us_prime = sort(ourProfiling(:,2), 'descend');
correlation_time_prime = corr(us, them, 'type', 'Spearman')
```

confidence `0.8997`



