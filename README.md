# Corner Store
CPSC 210 term project: r8a9v, 25860198

## Description
The application will function as a store management game. As a player, your objective is to
buy stock, upgrade your store, and
make rent every 5 days. Stock goes on clearance (loses value) after 2 days
so buy responsibly!
- This project is of interest to me since I ultimately want to be a 
game developer.

# *User stories*:
- As a user, I want to be able to add stock items to my inventory.
- As a user, I want to view my inventory and the items I have in stock as well as their value 
and whether they are or clearance or not.
- As a user, I want to unlock better stock items with the money I make.
- As a user, I want to level up and gain access to better items.
- As a user I want to be able to save the state of my game if I want to
- As a user I want to be able to load the last saved state of my game if I want to.

# Instructions for Grader: Phase 3
- You can load the GUI by running the RunGui class under src/main/ui/gui/RunGui.java.


- You can view all Xs (Products) added to Y (Inventory) by clicking the "inventory" button on the main screen, a textbox will pop up with descriptions of every item the player owns.


- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking "buy items" on the main screen and choosing one of the options. If you then go back to the main screen and look at your inventory the product you bought will be described in the text box.


- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking "begin day" on the main screen (once you have some items) this will sell a random pool of your items (Xs removed from Y and sold) and transfer their value into the "money" label. Beside the "begin day" button, a report of your sales and profits will be updated.


- You can locate my visual component by clicking "buy items" on the main screen. A little greeting image will be present above the product options. Based on whether you can afford an item or not this image will change when you try to buy something.


- You can save the state of my application by clicking "save" on the main screen.


- You can reload the state of my application by clicking "load" on the main screen.

# Phase 4: Task 2
### Possible Events
- Player viewed inventory!
- Player failed to buy product: did not exist.
- Player failed to buy product: could not afford.
- Player bought a(n) ITEM NAME for $ITEM PRICE.
- Player sold a(n) ITEM NAME for $ITEM PRICE.


# Phase 4: Task 3
First of all I would refactor my code by making Product abstract
and changing its tests to work with the new class type.
My project only deals with particular instances of products
(ie: Orange) so having product as an instantiable class 
does not seem like the best design.

Secondly, in the current state of my project, whenever a user 
tries to access another screen in the GUI from 
the main screen (ie: buy items, or inventory)
an entirely new screen object is generated for the requested screen,
wasting significant memory as more and more duplicate screens
are made. Currently this implementation
exists to allow the screens to let changes the player made
on the main screen be actualized in other screens
 by rerunning the screens' constructors and redefining their
components. This implementation was made before I knew about the 
observer pattern, so to refactor this design, I would 
make every screen class an object of type Observable, but also observer
since each screen would be watching other screens to look
for updates. As of now, only the generic components that 
every screen shares are updated when the screen changes.

Third, some of my GUI code is cluttered and hard to read.
Specifically, the checkIfBuying method in the OptionsScreen
class has complicated, verbose and nested calls to the
ImageIcon constructor when I try to update my visual component
with a new image, all in one line. This is very hard to understand without comments.
To fix this specific issue I would
refactor this process into its own well documented method
with each step that my current one line solution makes being separate
(Get image from file, resize image, make new icon).

Finally, I would create an abstract class called ConsoleRunner to 
capture the general behaviour that App and RunStore share. Currently
runStore has some identical behaviour to App. Both classes
serve as a textual interface with options to perform actions.
I could capture this in an abstract class by having a Map of
actions and text commands, as well as a general menu/interface
text template that each subclass could fill out. This would 
help make my App and RunStore classes more concise, more related,
and easier to read. Once this is done, I would remove App 
and RunStore's associations to Player and Store and give
them to ConsoleRunner to avoid duplicate fields and
unnecessary coupling.
  
