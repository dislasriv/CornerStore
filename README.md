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
  