/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nakpil.database.h2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Duskmourne
 */
public class MemoryFree {
    private BufferedReader reader = new BufferedReader(new
        InputStreamReader(System.in));
    private List<byte[]> usedMemory = new LinkedList<byte[]>();
    private int totalMB = 0;
    private int gcTimes = 0;

    public void allocate(int howManyMB) {
    	usedMemory.add(new byte[howManyMB * 1024 * 1024]);
    	totalMB += howManyMB;
    	System.out.println(howManyMB + "MB allocated, total allocated: " +
                totalMB + "MB");
    }

    public void free() {
    	usedMemory.clear();
    }

    public void gc() {
    	//System.gc();
        Runtime.getRuntime().gc();
    	System.out.println("GC " + (++gcTimes) + " times (Free : "+Runtime.getRuntime().freeMemory()+")");
        
    }

    public void waitAnswer(String msg) {
    	System.out.println("Press [enter]" + ((msg==null)?"":msg));
    	try {
    		reader.readLine();
    	} catch (IOException e) {
    	}
    }

    public static void main(String[] args) {
    	MemoryFree mf = new MemoryFree();
    	mf.waitAnswer(" to allocate memory");
    	mf.allocate(20);
    	mf.allocate(10);
    	mf.allocate(15);
    	mf.waitAnswer(" to free memory");
    	mf.free();
    	mf.waitAnswer(" to GC");
    	mf.gc();
    	mf.waitAnswer(" to GC");
    	mf.gc();
    	mf.waitAnswer(" to GC");
    	mf.gc();
    	mf.waitAnswer(" to GC");
    	mf.gc();
    	mf.waitAnswer(" to exit the program");

    	try {
    		mf.reader.close();
    	} catch (IOException e) {}
    }
}
