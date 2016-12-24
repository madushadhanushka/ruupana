/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorialspoint.contextextractor;

import java.util.ArrayList;

/**
 *
 * @author lahiru
 */
public class ContextIdentifier{
    public ArrayList<String[]> data;
    int ThreadCount=0; // equals to num columns
    int NumRows =0;
    private Table tbl = new Table();
   
    
    public Table Initialize(ArrayList<String[]> data) throws InterruptedException
    {
        this.data = data;
        NumRows = data.get(0).length;
        ThreadCount = data.size();
        tbl.Data = new Column[ThreadCount];
        ColumnContextThread[] threadArr = new ColumnContextThread[ThreadCount];
       
        System.out.println("All threads creted");
        for (int i = 0; i < ThreadCount; i++) {
            tbl.Data[i] = new Column();
            tbl.Data[i].ColumnId = i;
            threadArr[i]= new ColumnContextThread(tbl.Data[i],data.get(i));
        }
        for (int i = 0; i < ThreadCount; i++) {
            threadArr[i].t.join();
        }
        
        System.out.println("Join finished");
        System.out.println(tbl.Data[0].DataType);
        System.out.println(tbl.Data[1].DataType);
        System.out.println(tbl.Data[2].DataType);
        System.out.println(tbl.Data[3].DataType);
        System.out.println(tbl.Data[4].DataType);
      
        return tbl;
    }
}
