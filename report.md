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

###Task 3.1

first column corresponds to measured **total time**.
second column corresonds to measured **prime time**.

```matlab
them = sort(theirProfiling(:,1), 'descend');
us = sort(ourProfiling(:,1), 'descend');
confidence = corr(us, them, 'type', 'Spearman')
```

confidence `0.8997`



