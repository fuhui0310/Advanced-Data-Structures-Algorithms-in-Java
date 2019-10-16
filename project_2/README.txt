project folder:
fuhui0310-project02/


Brief description of submitted files:

src/stacks/

BrowserNavigation.java
    - Creates an object of type BrowserNavigation which contains two StackList objects:
    - one stack for back links and another stack for forward links.
    - Scans a plain text file to determine which links is the current link and whether to go
    - orward or backward. Also, search to determine whether a given link is the current

Navigator.java
    - Class which provides the navigation feature of class BrowserNavigation.
    
StackList.java
    - Class which implements Iterable. Objects of type StackList manage items in a singly linked list
    - where we can only push() and pop() items from top of the stack.

resources/

links.txt
    - input for testing adding and removing items from backand forward stacks.

popEmptyStackOfLinks.txt
    - input for testing attempts to remove item from empty Back stack.
    
inputFile03.txt
    - input for testing attempts to remove item from empty Forward stack.
    
RUN.txt
    - console output of DemoGit.java

README.txt
    - description of submitted files