# Pookie User Guide

	** ** ** ** ** Hello from ** ** ** ** **
	__________              __   .__        
	\______   \____   ____ |  | _|__| ____  
	 |     ___/  _ \ /  _ \|  |/ /  |/ __ \ 
	 |    |  (  <_> |  <_> )    <|  \  ___/ 
	 |____|   \____/ \____/|__|_ \__|\___>
	_________________________________________

Pookie is a task tracker with features such as marking and save files.

## Commands
1. Adding **TODO**
   
    Todo tasks do not contain specific deadlines. Give your todo a description.

```
   todo do my homework
```

2. Adding **DEADLINE**

   Give your deadline tasks a description and a deadline to hit using /by.

    Take note of the format for /by. Use dd-mm-yyyy hh:mm

```
    deadline do my homework /by 10-03-2025 03:00
```

3. Adding **EVENT**

   Give your event tasks a description and a start time using /from and end time using /to.

   Take note of the format for /from and /to. Use dd-mm-yyyy hh:mm

```
    event do my homework /from 10-03-2025 13:00 /to 10-03-2025 23:00
```

4. **MARKING** and **UNMARKING** tasks

    Use this to set a task as done(mark) or undone(unmark).

    Give the command an index, corresponding to the targeted task when listing.

    A marked task is given an X when listing tasks.

```
    mark 1
```
```
    unmark 1
```

5. *DELETING* tasks

    Use this to remove a task from being tracked.

    Give the command an index, corresponding to the targeted task when listing.

```
    delete 1
```

6. **FINDING** tasks

    Use this to find tasks containing specified keywords.

    Multiple keywords can be specified.

    The command does not recognize substrings of multiple words, whitespace between characters separate

    them into individual words.

```
    find book run sleep
```
    find command searches for tasks containing EITHER but not limited to: book, run, sleep

7. **LISTING** tasks
    
    Use this to list all currently tracked tasks.
    
    Each task is accompanied by task type in [ ]:
   1. [T] - todo
   2. [D] - deadline
   3. [E] - event

    Each task is accompanied by mark status in [ ]:
   1. \[X] - done
   2. \[ ] - not done

    Deadline and Event tasks are shown their dates.


Example output

```
    list
	Here are all your tasks:
	1. [T][X] chicken mcnugget
	2. [D][ ] do my homework (by: Mar-10-2025 03:00 AM)
	3. [E][ ] fly away (from: Apr-30-2025 12:00 AM || to: May-03-2025 12:00 AM)
		[3 tasks added]
```

8. **EXITING** Pookie
    
    Close Pookie.

    Pookie saves the state of the tracked tasks and writes to a save file upon exiting Pookie.

```
    exit
```

## NOTES
Save file is named tasks.txt
tasks.txt is saved in the working directory where you run Pookie.