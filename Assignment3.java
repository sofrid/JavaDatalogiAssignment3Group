package com.example.assignment3;

import java.util.*;

/**
 *Program for user input to be saved, handled and showed in different ways.
 * Input get saved to ArrayList.
 * Possible to view ArrayList, amount of words and modify by add, updating, remove, sort and encrypt.
 *
 * @author Sofia Ridderstad, Pär Nilsson, Andreas Sanz, Nihad Mujcic
 * @since 2019-02-21
 */

public class Assignment3 {
    private static Scanner in;
    private static ArrayList<stringWithDate> stringArrayList = new ArrayList<>();
    private static ArrayList<Integer> fibonacciArrayList = new ArrayList<>();

    /**
     * This is the main method which executes the program.
     *
     * @param args
     */
    public static void main(String[] args){
        menu();
    }

    /**
     * Method that runs the menu and calls for the anticipated methods to compute tha tasks the user wishes.
     */
    private static void menu(){
        boolean notExit = true;

        while (notExit){
            System.out.print("Would you like to (1 Add). (2 Search). (3 Update). (4. Remove). (5 Sort). (6 Encrypt). " +
                    "(7 Total Words). (8 View). (0 Exit).\nYour choice: ");
            setScanner();
            switch (stringToInt(getScanner().next())) {
                case 1: addToArrayList();
                    break;
                case 2: searchInArrayList();
                    break;
                case 3: updateItemInArrayList();
                    break;
                case 4: removeFromArrayList();
                    break;
                case 5: sortArrayList();
                    break;
                case 6: encryptItemInArrayList();
                    break;
                case 7: showAmountOfWords();
                    break;
                case 8: viewStringArrayList();
                break;
                case 0: notExit = false;
                    break;
                default: System.out.println("Would you like to (1 Add). (2 Search). (3 Update). (4. Remove). (5 Sort). " +
                        "(6 Encrypt). (7 Total Words). (8 View). (0 Exit).\nYour choice: ");
                    menu();
            }
        }
    }

    /**
     * Method that add user input to ArrayList.
     */
    private static void addToArrayList(){
        System.out.print("What would you like to add: ");
        getScanner().nextLine();
        addToStringArrayList(new stringWithDate(getScanner().nextLine()));
        addToFibonacciArrayList(getNewFibonacciNumber(getFibonacciArrayList().size() + 1));
        System.out.println(getFibonacciArrayList().toString());
        isAddedNumberEven(getFibonacciArrayList().get(getFibonacciArrayList().size() - 1));
    }

    /**
     * Method that checks if the number that was added to the fibonacci ArrayList is even or not.
     * Prints out result.
     * @param latestNumberInFibonacciSeries
     */
    private static void isAddedNumberEven(int latestNumberInFibonacciSeries){
        if(latestNumberInFibonacciSeries % 2 == 0){
            System.out.println(latestNumberInFibonacciSeries + " is even.");
        }else{
            System.out.println(latestNumberInFibonacciSeries + " is uneven.");
        }
    }

    /**
     * Method to search for content in ArrayList.
     */
    private static void searchInArrayList(){
        System.out.print("Index to choose from: ");
        for (int i = 0; i < getStringArrayList().size(); i++) {
            System.out.print(i + " ");
        }
        System.out.println("\nWhat index are you searching for: ");
        searchOutput(stringToInt(getScanner().next())); // Method that prints out the content of the search.
    }

    /**
     * Method that if searched number matches an index in the ArrayList prints out the content of that index.
     * @param index
     */
    private static void searchOutput(int index){
        if(index < getStringArrayList().size() && index >= 0){
            System.out.println(getStringArrayList().get(index));
        }else{
            System.out.println("There is nothing on index " + index);
        }
    }

    /**
     * Method for updating content of an index in the ArrayList.
     */
    private static void updateItemInArrayList(){
        displayArrayListWithIndexNr();//Method that prints out current index number and current content for that number.
        System.out.print("What index would you like to change: ");
        updateArrayListWithElement(stringToInt(getScanner().next())); // Method that changes the content of specified
                                                                        // index of the ArrayList.
    }

    /**
     * Method that changes the content of specified index of the ArrayList.
     * @param index
     */
    private static void updateArrayListWithElement(int index){
        System.out.print("What would you like to change it to: ");
        getScanner().nextLine();
        setStringArrayList(index, new stringWithDate(getScanner().nextLine())); // Sets new content i specified index.
    }

    /**
     * Method that deletes content from a specified index in the ArrayList.
     */
    private static void removeFromArrayList(){
        displayArrayListWithIndexNr();//Method that prints out current index number and current content for that number.
        if(getStringArrayList().size() >= 1) {
            System.out.print("What index would you like to delete: ");
            try {
                getStringArrayList().remove(stringToInt(getScanner().next())); // Removes content from specified index.
            }catch (IndexOutOfBoundsException outOfBound){
                System.out.println("There is nothing to delete on this index.");
            }
        }else{
            System.out.println("You cannot delete something from an empty list.");
        }
    }

    /**
     * Method that sorts the ArrayList in two different ways. By alphabetical order and by date/time.
     */
    private static void sortArrayList(){
        ArrayList<String> listToSort = new ArrayList<>(); // New array to make it possible to sort.
        for(int i = 0; i < getStringArrayList().size(); i++){
            listToSort.add(i, getStringArrayList().get(i).getInputString()); // Adds value to new ArrayList
        }
        System.out.println("Unsorted list: " + listToSort);
        Collections.sort(listToSort); // Sorts alphabetical.
        System.out.println("Sorted list in alphabeticly order: " + listToSort);

        for(int i = 0; i < getStringArrayList().size(); i++){
            listToSort.set(i, getStringArrayList().get(i).getDateTime() + " " +
                    getStringArrayList().get(i).getInputString()); // Add value to new ArrayList with date and time.
        }
        Collections.sort(listToSort);  // Sorts by time/date.
        System.out.println("Sorted list in by date: " + listToSort);
    }

    /**
     * Method that encrypts choosen content in the ArrayList.
     */
    private static void encryptItemInArrayList(){
        displayArrayListWithIndexNr();//Method that prints out current index number and current content for that number.
        System.out.println("What index should be encrypted? ");
        setItemToEncrypted(stringToInt(getScanner().next())); // Encrypts content in specified index.
    }

    /**
     * Method that sets the encrypted content of a specified index of the ArrayList.
     *
     * @param index
     */
    private static void setItemToEncrypted(int index){
        if(index < getStringArrayList().size() && index >= 0){
            setStringArrayList(index, new stringWithDate(encryptString(getStringArrayList().get(index).getInputString())));
                                                                    // Sets the content of specified index in ArrayList.
                                                                    // By using method that encrypts the string.
        }else{
            System.out.println("There is nothing on index " + index);
        }
    }

    /**
     * Method that encrypts the string at specified index of the ArrayList.
     *
     * @param input
     * @return
     */
    private static String encryptString(String input){
        char[] newStringAsChar = new char[input.length()];
        for(int i = 0; i < input.length(); i++){
            newStringAsChar[i] = offsetChar(input.charAt(i), 2);
        }
        return new String(newStringAsChar);
    }

    /**
     * Method that switches chars in the string.
     * @param c
     * @param offset
     * @return
     */
    private static char offsetChar(char c, int offset){
        c += offset;
        if(c > 90 && c < 91+offset || c > 122 && c < 123+offset){
            c -= 25+offset;
        }
        if(c < 97 && c > 96-offset || c < 65 && c > 64-offset){
            c += 25+offset;
        }
        return c;
    }

    /**
     * Method that counts and show the total amount of words existing in the ArrayList.
     */
    private static void showAmountOfWords(){
        StringBuilder allStrings = new StringBuilder();
        for(int i = 0; i < getStringArrayList().size(); i++){
            allStrings.append(getStringArrayList().get(i).getInputString()).append(" ");
        }
        System.out.println("There is a total of " + countWords(allStrings) + " words.");
    }

    /**
     * Method that counts the number of words.
     *
     * @param allStrings
     * @return
     */
    private static int countWords(StringBuilder allStrings){
        String allWords = allStrings.toString();
        if(allWords.length() == 0){
            return 0;
        }else{
            String[] words = allWords.split("\\s+");
            return words.length;
        }
    }

    /**
     * Method that show the content of the ArrayList
     */
    private static void viewStringArrayList() {
        if(!getStringArrayList().isEmpty()){
            displayArrayListWithIndexNr();//Method that prints out current index number and current content for that number.
        }else{
            System.out.println("There is nothing to display");
        }
    }

    /**
     * Method that prints out current index number and current content for that number.
     */
    private static void displayArrayListWithIndexNr(){
        for(int i = 0; i < getStringArrayList().size(); i++){
            System.out.println(i + ". " + getStringArrayList().get(i));
        }
    }

    /**
     * Method that converts string to int.
     * @param stringToInt
     * @return
     */
    private static int stringToInt(String stringToInt){
        try{
            return Integer.parseInt(stringToInt);
        }catch (InputMismatchException | NumberFormatException notAnInt){
            System.out.println("Not a number.");
            menu();
        }
        return Integer.parseInt(stringToInt);
    }

    /**
     * Method that add element to ArrayList.
     * @param element
     */
    private static void addToStringArrayList(stringWithDate element){
        stringArrayList.add(element);
    }

    /**
     * Method that add element to fibonacci ArrayList.
     * @param element
     */
    private static void addToFibonacciArrayList(int element){
        fibonacciArrayList.add(element);
    }

    /**
     * Get method for Scanner.
     * @return
     */
    private static Scanner getScanner(){
        return in;
    }

    /**
     * Set method for Scanner.
     */
    private static void setScanner(){
        in = new Scanner(System.in);
    }

    /**
     * Get method for stringArrayList.
     * @return
     */
    private static ArrayList<stringWithDate> getStringArrayList(){
        return stringArrayList;
    }

    /**
     * Set method for stringArrayList.
     * @param index
     * @param element
     */
    private static void setStringArrayList(int index, stringWithDate element){
        stringArrayList.set(index, element);
    }

    /**
     * Get method for fibonacciArrayList.
     * @return
     */
    private static ArrayList<Integer> getFibonacciArrayList(){
        return fibonacciArrayList;
    }

    /**
     * Get method for newFibonacciNumber.
     * @param numberToGetNewFibonacciNumber
     * @return
     */
    private static int getNewFibonacciNumber(int numberToGetNewFibonacciNumber){
        if(numberToGetNewFibonacciNumber > 2){
            return getNewFibonacciNumber(numberToGetNewFibonacciNumber - 1) + getNewFibonacciNumber(numberToGetNewFibonacciNumber - 2);
        }else{
            return 1;
        }
    }
}