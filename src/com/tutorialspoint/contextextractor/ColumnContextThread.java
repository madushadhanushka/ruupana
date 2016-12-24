/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorialspoint.contextextractor;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author lahiru
 */
public class ColumnContextThread implements Runnable{
Thread t;    
Column col;
String[] DataToProcess;
Set<Integer> set_int = new HashSet<Integer>();
Set<Float> set_float = new HashSet<Float>();
Set<String> set_string = new HashSet<String>();
int intCount =0,floatCount=0,StringCount=0,dateCount=0;

public ColumnContextThread(Column col,String[] data)
{
    this.col = col;
    this.DataToProcess=data;
    t = new Thread(this);
    t.start();
}


    @Override
    public void run() {
    	
        int type = RecognizeType();
        if(RecognizeBoolean(type)){type=4;}//if boolean change type to boolean
        col.DataType = type;
        col.IsCategorical = RecognizeCategorical(type); 
    }
    
    public boolean RecognizeBoolean(int type)
    {
        switch(type)
        {
            case 1: {if(set_int.size()==2 && set_int.contains(1) && set_int.contains(0)){ return true;} break;}
            case 3: {if(set_string.size()==2){return true;}break;}
            default : return false;
        }
        return false;
    }
  
    public boolean RecognizeCategorical(int type)
    {   // .6 must be comming from system settings
         switch(type)
        {
            case 1: {if(set_int.size()<intCount*.6){return true;}break;}
            case 2: {if(set_float.size()<floatCount*.6){return true;}break;}
            case 3: {if(set_string.size()<StringCount*.6){return true;}break;}
            case 4: {return true;}
            default : return false;
        }
        return false;
    } 
    
    public int RecognizeType() // select first 100 rows and deduce the data type
   /*   1 - int
        2 - float
        3 - string
        4 - boolean
        5 - date
        0 - not recognized    */
    {   
        int iterations = 100;
        if(DataToProcess.length<iterations){iterations = DataToProcess.length;}
        for (int i = 1; i < iterations; i++) { // i=1 without header
            String temp = DataToProcess[i];
            if(this.tryParseInt(temp)){set_int.add(Integer.parseInt(temp));intCount++;}
            else if(this.tryParseFloat(temp)){set_float.add(Float.parseFloat(temp));floatCount++;}
            else {set_string.add(temp.toLowerCase());StringCount++;} // getting all strings to a common case
        }
        int returnValue=0;
        // if any type contains more than half of the iterations that type is selected
        //if(intCount>iterations/2 && floatCount>iterations*.4){returnValue=2;}
        if(intCount>iterations/2){returnValue=1;}
        else if(floatCount>iterations/2){returnValue=2;}
        else if(StringCount>iterations/2){returnValue=3;}
        
        return returnValue;
    }
    
    boolean tryParseInt(String value) {  
     try {  
         Integer.parseInt(value);  
         return true;  
        } catch (NumberFormatException e) {  
         return false;  
        }  
    }    
    
    boolean tryParseFloat(String value) {  
     try {  
         Float.parseFloat(value);  
         return true;  
        } catch (NumberFormatException e) {  
         return false;  
        }  
    } 
        
        
}
