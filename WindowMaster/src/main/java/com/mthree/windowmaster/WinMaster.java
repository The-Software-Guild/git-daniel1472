/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.windowmaster;

import java.util.Scanner;

/**
 *
 * @author danie
 */
public class WinMaster {
    public static void main(String[] args){
        
        // declare vars
        float height = 0;
        float width = 0;
        
        String stringHeight, stringWidth;
        
        float area, cost, perimeter;
        
        boolean isValid = false;
        
        // create scanner
        Scanner myScanner = new Scanner(System.in);
        
        // safely get the height 
        do {            
            System.out.println("Please enter window height:"); 
            stringHeight = myScanner.nextLine(); 
            
            if(stringHeight != null && stringHeight.isEmpty() == false)
            {
                try {
                    // parse the string value to a float
                    height = Float.parseFloat(stringHeight);
                    
                    isValid = true;
                } catch (NumberFormatException e) {
                    
                   System.out.println("You entered an invalid value"); 
                }
            }
                
            
        } while (!isValid);
                
        // safely get the width
        isValid = false;
        do {            
            System.out.println("Please enter window width:"); 
            stringWidth = myScanner.nextLine();
            
            if(stringWidth != null && stringWidth.isEmpty() == false)
            {
                try {
                    // parse the string value to a float
                    width = Float.parseFloat(stringWidth);
                    
                    isValid = true;
                } catch (NumberFormatException e) {
                    
                   System.out.println("You entered an invalid value"); 
                }
            }
            
        } while (!isValid);
        
        // calculate the area
        area = height * width;
        
        // calculate the perimeter
        perimeter = 2 * (height + width);
        
        // calculate the total cost
        cost = ((3.50f * area) + (2.25f * perimeter));
        
        // print the results
        System.out.println("Window height = " + stringHeight);
        System.out.println("Window width = " + stringWidth);
        System.out.println("Window area = " + area);
        System.out.println("Window perimeter = " + perimeter);
        System.out.println("Total Cost = " + cost);

    }
}
