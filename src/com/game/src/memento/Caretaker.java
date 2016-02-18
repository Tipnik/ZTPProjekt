package com.game.src.memento;

import java.util.ArrayList;

public class Caretaker {

	 private static Memento savedStates ;
	 private static Caretaker caretaker = new Caretaker();
	 
	 
	 public static Caretaker getInstance()
	 {
		 return caretaker;
	 }

	   public void addMemento(Memento m) { this.savedStates=m; }
	   public Memento getMemento(int index) { return savedStates; }
	   
}
