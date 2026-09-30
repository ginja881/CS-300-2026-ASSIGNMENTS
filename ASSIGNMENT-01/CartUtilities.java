/* 
* Author: Joseph Carter
* Email: jtcarter5@wisc.edu
* Course: CS 300, Fall 2026
* Assignment: Programming Assignment 01
* Citations: have not used any LLMs or discussed the assignment with peer(s).
*/

/**
 * 
 * CartUtilities
 * Provides an interface for users with a cart such that they can obtain the indices of their items,
 * get individual costs, 
 * remove or add items, 
 * and obtain the overall cost of items in their cart.
 */
public class CartUtilities {

  /**
   * Method for obtaining any item (an array of strings with an item name and quantity) within the non-null oversize array cart at index i. 
   * @param cart non-null oversize array containing items (arrays of strings with an item name and quantity)
   * @param cartSize size of the oversize array/how many items are currently in the array "cart."
   * @param description the item name.
   * @return an index i representing the index of the item in cart.
   */

  public static int indexOfItem(String[][] cart, int cartSize, String description) {
      // Index to return
      int i = 0;

      // Iterate over all items currently in cart
      for (; i < cartSize; i++) {
          // Current item and its name
          String[] item = cart[i];
          String item_name = item[0];

          // If the item name is identical to the string referred to by description, then we break because we have our desired index.
          if (item_name.equals(description)) {
              break;
          }

      }

      // If we iterated throughout the entire cart, but did not find the desired item, then we just return -1;
      if (i > cartSize - 1)
          return -1;
      return i;
  }

  /**
   * Method for adding any item to the array cart, such that it has a name of description/ 
   * @param cart a non-null oversize array containing references to other arrays that contain two strings:  an item name and how many items there are.
   * @param cartSize how many items exist in cart.
   * @param description the name of the item we want to add to cart
   * @return the new cart size.
   */
  public static int addItemToCart(String[][] cart, int cartSize, String description) {
    
      // If no item exists and there is room within cart, then we add a new array {description, "1"} at the cartSize index and return the incremented size of cart.
      if (cart[cartSize] == null) {
            cart[cartSize] = new String[2];
            cart[cartSize][0] = description;
            cart[cartSize][1] = "1";
            return cartSize + 1;
      }
        
    
    // index of item with name description
      int itemIndex = indexOfItem(cart, cartSize, description);
      // If the item does already exist in cart, then we increment the current amount of items that is recorded in cart and return the incremented size of cart.
      if (itemIndex != -1) {
        int amount = Integer.parseInt(cart[itemIndex][1]);
        cart[itemIndex][1] = Integer.toString(amount + 1);
        return cartSize;
      }

      // If there is no room in the cart and the item does not exist, then we return the current size for cart  (cartSize) without any additional modifications. 
      return cartSize;
  }

  /**
   * Method for removing any item from the array cart, such that it has the name of whatever string description refers to.
   * @param cart a non-null oversize array cart containing references to arrays of Strings that contain an item name and how much of the item is in the cart.
   * @param cartSize how many items are in/the size of the array cart.
   * @param index the index of the item for removal
   * @return the modified size of cart: cartSize. 
   */


  public static int removeItemFromCart(String[][] cart, int cartSize, int index) {
      // First, check if index is out of bounds
      if (index >= cartSize)
          return cartSize;

      // Grab the item for removal and it's item quantity for checking
      String[] itemForRemoval = cart[index];
      int itemQuantity = Integer.parseInt(itemForRemoval[1]);


      // If the item quantity is larger than one, we decrement it and update the value recorded in cart and return the decremented cartSize
      if (itemQuantity > 1) {
          itemForRemoval[1] = Integer.toString(itemQuantity - 1);
          return cartSize;
      }
      // Alternatively, if it IS 1, we set the item for removal to be a null reference and shift all items after the chosen item to the left.
      else {
          
          for (int i = index; i < cartSize; i++) {
            cart[i] = cart[i+1];
          }
    }

    // After updating cart, we return the decremented cart size
    return cartSize - 1; 
  }

  /**
   * Method that grabs the item in inventory named description its cost.
   * @param inventory a perfect size array of names of items in inventory
   * @param costs a perfect size array of associated costs with items in inventory
   * @param description the name of the desired item
   * @return an integer representing the cost associated with the desired item
   */
  public static int getCostOfItem(String[] inventory, int[] costs, String description) {

      // itemIndex of chosen item
      int itemIndex = 0;
      // Iterate through entire inventory in order to find the desired item with name description
      for (; itemIndex < inventory.length; itemIndex++) {
        // If we did find the desired item, then we break!!
        if (inventory[itemIndex].equals(description))
            break;
      }
    
      // If no item matching description is found, then return -1
      if (itemIndex == inventory.length)
          return -1;

      // Else, return the cost of the associated item
      return costs[itemIndex];
  }

  /**
   * Method that sums up the cost of all of the cart's items  
   * @param cart non-oversize array of string arrays that each contain an item name and a string representing the item quantity
   * @param cartSize the amount of items in the cart/the array size of cart
   * @param inventory the names of all items in inventory
   * @param costs  the associated costs of items in inventory. 
   * @return an integer representing the total cost of all items in the cart
   */
  public static int getTotalCost(String[][] cart, int cartSize, String[] inventory, int[] costs) {
      //   sum of all costs to return
      int totalCost = 0;

      // Iterates over all items in cart to sum up the total cost
      for (int i = 0; i < cartSize; i++) {
          // Grab the current item and its cost
          String[] item = cart[i];
          int itemCost = getCostOfItem(inventory, costs, item[0]);

          // If the cost does NOT equal -1, then we add it multiplied by its quantity to the total cost. 
          if (itemCost != -1) {
              int itemQuantity = Integer.parseInt(item[1]);
              totalCost += itemQuantity * itemCost;
          }
      }
      return totalCost;
  }

}