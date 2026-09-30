/* 
* Author: Joseph Carter
* Email: jtcarter5@wisc.edu
* Course: CS 300, Fall 2026
* Assignment: Programming Assignment 01
* Citations: have not used any LLMs or discussed the assignment with peer(s).
*/
/**
 * Contains testing methods for each method in CartUtilities.
 * Each testing method returns true when all its test cases pass, otherwise false.
 */

public class CartUtilitiesTester {

  // no need for testing method header comments as these are self-explanatory.
  public static boolean testIndexOfItem() {
      // Define a new utilities manager
      CartUtilities utilities_manager = new CartUtilities();

      // create a cart of size 3
      String[][] cart = { {"cheese", "1"},
                    {"apple", "5"},
                    {"bread", "1"}, 
                      null, null};
      int cartSize = 3;

      // define test result
      boolean result = true;

      // define normal case to be {"apple", "5"} in cart and to see if we do not get -1 (the item is not there)
      if (utilities_manager.indexOfItem(cart, cartSize, "apple") == -1)
          result = false;
    
      // define edge case to be item known as "chicken", which is not in cart. Should return -1.
      if (utilities_manager.indexOfItem(cart, cartSize, "chicken") != -1)
          result = false;
    
      // Return result
      return result;
  }

  public static boolean testAddItemToCart() {
      // Utilities manager
      CartUtilities utilities_manager = new CartUtilities();

      // Cart for testing of size 3
      String[][] cart = { {"cheese", "1"},
                    {"apple", "5"},
                    {"bread", "1"}, 
                      null, null};
      int cartSize = 3;

      // test result
      boolean result = true;  

      // Case where we update cart[0][1]
      // Update cart size and add 1 more piece of cheese
      cartSize = utilities_manager.addItemToCart(cart, cartSize, "cheese");
      // Cart size must not be 3 and the quantity of cheese must equal "2"
      if (cartSize == 3 && !(cart[0][1].equals("2")))
          result = false;

      // CASE WHERE ITEM IS NOT IN CART

      // Keep old cart size
      int oldSize = cartSize;
      cartSize = utilities_manager.addItemToCart(cart, cartSize, "orange");
      // cart size should be bigger than old size
      if (!(cartSize > oldSize))
        result = false;  
      
      // Grab the last item, being where the new item is
      String[] lastItem = cart[cartSize - 1];
      // Is it null? If so, then the item has not been correctly inserted into the array
      if (lastItem == null)
          result = false;
      
      // If it is not null, then check if lastItem[0] equals "orange" and lastItem[1] equals "1"
      if (!(lastItem[0].equals("orange")) && !(lastItem[1].equals("1")))
          result = false;
        
    return result;
  }

  public static boolean testRemoveItemFromCart() {
    // TODO: test a "normal" case for this method

      // Define utilities manager
      CartUtilities utilities_manager = new CartUtilities();

      // Cart for testing of size 3
      String[][] cart = { {"cheese", "1"},
                    {"apple", "5"},
                    {"bread", "1"}, 
                      null, null};
      int cartSize = 3;

      // Test result
      boolean result = true;

      // Remove item cart[1], decrementing the quantity of apples
      cartSize = utilities_manager.removeItemFromCart(cart, cartSize, 1);

      // cartSize should still be 3
      if (cartSize != 3)
        result = false;

      // cart[1] should NOT be null
      String[] item = cart[1];
      if (item == null) 
          result = false;
      
      // cart[1][0] should still be "apple" and cart[1][1] should be "4" 
      if (!(item[0].equals("apple")) && !(item[1].equals("4")))
          result = false;
      
      // EDGE CASE: Attempting to remove an empty spot in cart
      // Keep the old size
      int oldSize = cartSize;

      // Size should not change because cart[3] is null
      cartSize = utilities_manager.removeItemFromCart(cart, cartSize, 3);
      if (cartSize != oldSize) 
          result = false;
      

    return result;
  }

  public static boolean testGetCostOfItem() {
    // Define utilities manager
    CartUtilities utilities_manager = new CartUtilities();
    // Test  inventory with 3 items
    String[] inventory = {
      "cheese",
      "bread",
      "juice"
    };
    // Test costs
    int[] costs = {
      1,
      2,
      3
    };
    // Test result
    boolean result = true;
    // Get cost of item "cheese"
    int cost = utilities_manager.getCostOfItem(inventory, costs, "cheese");
    // cost is SUPPOSED to be 1
    if (cost != 1)
      result = false;
    
    // Get cost of item not in inventory with name "roses"
    int badCost = utilities_manager.getCostOfItem(inventory, costs, "roses");
    // badCost SHOULD be -1
    if (badCost != -1)
        result = false;

    // return result
    return result;
  }

  public static boolean testGetTotalCost() {
    // Define utilities manager for testing
    CartUtilities utilities_manager = new CartUtilities();
    // Define test result
    boolean result = true;
    // CASE 1: Cart of items (size of 4) with only one quantity & 4 items in inventory
    String[][] cartOne = {
      {"sour lemons", "1"},
      {"raspberries", "1"},
      {"oranges", "1"},
      {"cheese", "1"},
      null
    };

    String[] inventoryOne = {
      "sour lemons",
      "raspberries",
      "oranges",
      "cheese"
    };

    int[] costsOne = {
      10,
      10,
      10,
      10
    };

    int cartOneSize = 4;

    // Grab total cost
    int totalCostsOne = utilities_manager.getTotalCost(cartOne, cartOneSize, inventoryOne, costsOne);
    // By definition of costsOne, totalCostsOne should be 40
    if (totalCostsOne != 40)
        result = false;
    

    // CASE 2: Cart of size 4 with each item having a quantity of 1. Only "sour lemons" and "raspberries" are inventory items (inventory has 2 items)

    String[][] cartTwo = {
      {"sour lemons", "1"},
      {"raspberries", "1"},
      {"oranges", "1"},
      {"cheese", "1"},
      null
    };

    String[] inventoryTwo = {
      "sour lemons",
      "raspberries"
    };

    int[] costsTwo = {
      10,
      10
    };
    
    int cartTwoSize = 4;

    // Grab total cost
    int totalCostsTwo = utilities_manager.getTotalCost(cartTwo, cartTwoSize, inventoryTwo, costsTwo);

    // totalCostsTwo should be 20
    if (totalCostsTwo != 20)
        result = false;
    
    // CASE 3: Cart with items (size of 4) of more than one quantity with 4 items in inventory.
    String[][] cartThree = {
      {"sour lemons", "2"},
      {"raspberries", "3"},
      {"oranges", "4"},
      {"cheese", "5"},
      null
    };

    String[] inventoryThree = {
      "sour lemons",
      "raspberries",
      "oranges",
      "cheese"
    };

    int[] costsThree = {
      10,
      10,
      10,
      10
    };

    int cartThreeSize = 4;
    // Grab total cost
    int totalCostsThree = utilities_manager.getTotalCost(cartThree, cartThreeSize, inventoryThree, costsThree);
    // By definition of costsThree, totalCostsThree should only be 140
    if (totalCostsThree != 140)
        result = false;

    return result;
  }

  public static void main(String[] args) {
    System.out.println("=== CART UTILITIES TESTER ===");

    boolean allPass = true, testPass = true;

    System.out.println("testIndexOfItem():");
    testPass = testIndexOfItem();
    System.out.println("\t" + (testPass ? "PASS" : "FAIL"));

    allPass &= testPass;

    System.out.println("testAddItemToCart():");
    testPass = testAddItemToCart();
    System.out.println("\t" + (testPass ? "PASS" : "FAIL"));

    allPass &= testPass;

    System.out.println("testRemoveItemFromCart():");
    testPass = testRemoveItemFromCart();
    System.out.println("\t" + (testPass ? "PASS" : "FAIL"));

    allPass &= testPass;

    System.out.println("testGetCostOfItem():");
    testPass = testGetCostOfItem();
    System.out.println("\t" + (testPass ? "PASS" : "FAIL"));

    allPass &= testPass;

    System.out.println("testGetTotalCost():");
    testPass = testGetTotalCost();
    System.out.println("\t" + (testPass ? "PASS" : "FAIL"));

    allPass &= testPass;

    if (allPass) {
      System.out.println("\nCONGRATULATIONS! All of your tests passed.");
    }
  }

}