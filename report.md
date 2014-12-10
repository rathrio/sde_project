#Report SDE Project 2014

##Task 1
##Task 2
##Task 3

```
org.bitstorm.gameoflife.CellGridCanvas.update(java.awt.Graphics)
calls = 6
total = 51
prime = 0

org.bitstorm.gameoflife.CellGridCanvas.paint(java.awt.Graphics)
calls = 122
total = 7
prime = 0

org.bitstorm.gameoflife.GameOfLife.nextGeneration()
calls = 4
total = 4
prime = 0

org.bitstorm.gameoflife.GameOfLifeControls.startStopButtonClicked()
calls = 6
total = 3
prime = 1

org.bitstorm.gameoflife.GameOfLifeControls$4.actionPerformed(java.awt.event.ActionEvent)
calls = 2
total = 3
prime = 0

org.bitstorm.gameoflife.GameOfLife.startStopButtonClicked(org.bitstorm.gameoflife.GameOfLifeControlsEvent)
calls = 3
total = 2
prime = 0

org.bitstorm.gameoflife.GameOfLife.showGenerations()
calls = 3
total = 1
prime = 0

org.bitstorm.gameoflife.GameOfLifeControls.setGeneration(int)
calls = 6
total = 1
prime = 0

org.bitstorm.gameoflife.GameOfLife.start2()
calls = 3
total = 1
prime = 1

```


```
Name	Time (ms)	Avg. Time (ms)	Own Time (ms)	Invocation Count
org.bitstorm.gameoflife.GameOfLife.run()	9987	9987	9982	1
org.bitstorm.gameoflife.GameOfLife.nextGeneration()	4	0	0	10
org.bitstorm.gameoflife.GameOfLifeControls.startStopButtonClicked()	3	1	0	2
org.bitstorm.gameoflife.GameOfLifeControls$4.actionPerformed(ActionEvent)	3	1	0	2
org.bitstorm.gameoflife.GameOfLife.showGenerations()	3	0	0	10
org.bitstorm.gameoflife.GameOfLifeControls.setGeneration(int)	3	0	0	10
org.bitstorm.gameoflife.GameOfLife.startStopButtonClicked(GameOfLifeControlsEvent)	3	1	0	2
org.bitstorm.gameoflife.GameOfLife.start2()	2	2	0	1
org.bitstorm.gameoflife.CellGridCanvas.update(Graphics)	2	0	0	10
org.bitstorm.gameoflife.CellGridCanvas.paint(Graphics)	1	0	0	10
org.bitstorm.gameoflife.GameOfLifeControls.start()	1	1	0	1
org.bitstorm.gameoflife.GameOfLife.stop()	0	0	0	1
org.bitstorm.gameoflife.GameOfLifeControls.stop()	0	0	0	1
org.bitstorm.gameoflife.GameOfLifeGrid.next()	0	0	0	10
org.bitstorm.gameoflife.GameOfLifeControlsEvent.<init>(Object)	0	0	0	2
org.bitstorm.gameoflife.GameOfLifeGrid.addNeighbour(int, int)	0	0	0	400
org.bitstorm.gameoflife.AppletFrame.processEvent(AWTEvent)	0	0	0	1
org.bitstorm.gameoflife.Cell.equals(Object)	0	0	0	215
org.bitstorm.gameoflife.Cell.hashCode()	0	0	0	677
org.bitstorm.gameoflife.GameOfLife.isRunning()	0	0	0	2
org.bitstorm.gameoflife.GameOfLifeGrid.getEnum()	0	0	0	10
org.bitstorm.gameoflife.GameOfLifeGrid.setCell(int, int, boolean)	0	0	0	35

```

###Task 3.1

first column corresponds to measured **total time**.
second column corresonds to measured **prime time**.

```matlab
them = sort(theirProfiling(:,1), 'descend');
us = sort(ourProfiling(:,1), 'descend');
confidence = corr(us, them, 'type', 'Spearman')
```

confidence `0.8997`



