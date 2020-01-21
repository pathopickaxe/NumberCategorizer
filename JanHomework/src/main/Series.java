package main;

import java.lang.reflect.Array;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Class to implement 1D arrays better.
 * @author andrewlykken
 *
 */
@SuppressWarnings("unchecked")
public class Series<Type> {
	
	/*
	 * INSTANCE DATA
	 */
	private Type[] arr;
	public int length;
	
	/*
	 * CONSTRUCTORS
	 */
	public Series(int length) {
		arr = (Type[]) new Object[length];
		this.length = length;
	}
	
	public Series(Type[] array) {
		arr = array;
		length = array.length;
	}
	
	
	/*
	 * GETTERS AND SETTERS
	 */

	public Type[] getArray() {
		return arr;
	}
	
	public void setArray(Type[] array) {
		arr = array;
		length = arr.length;
	}
	
	public Type getValue(int index) {
		return arr[index];
	}
	
	public void setValue(Type value, int index) {
		arr[index] = value;
	}
	
	/*
	 * METHODS
	 */
	public void append(Type value) {
		Type[] out = (Type[]) new Object[arr.length + 1];
		
		for(int x = 0; x < arr.length; x++) {
			out[x] = arr[x];
		}
		
		out[arr.length] = value;
		
		arr = out;
		length = arr.length;
	}
	
	public void append(Type value, int position) {
		Type[] out = (Type[]) new Object[arr.length + 1];
		
		for(int x = 0; x < position; x++) {
			out[x] = arr[x];
		}
		out[position] = value;
		
		for(int x = position+1; x < out.length; x++) {
			out[x] = arr[x-1];
		}
		
		arr = out;
		length = arr.length;
	}
	
	
	public void delend() {
		Type[] out = (Type[]) new Object[arr.length - 1];
		
		for(int x = 0; x < arr.length - 1; x++) {
			out[x] = arr[x];
		}
		
		arr = out;
		length = arr.length;
	}
	
	public void delend(int position) {
		Type[] out = (Type[]) new Object[arr.length - 1];
		
		for(int x = 0; x < position; x++) {
			out[x] = arr[x];
		}
		for(int x = position+1; x < out.length; x++) {
			out[x] = arr[x+1];
		}
		
		arr = out;
		length = arr.length;
	}
	
	public static Integer[] zeroArray(int length) {
		Integer[] out = new Integer[length];
		
		for(int x = 0; x < length; x++) { 
			out[x] = 0;
		}
		
		return out;
	}
	
	public void printArr() {
		String out = "[";
		for(int x = 0; x < arr.length - 1; x++) {
			out+= arr[x].toString() + ", ";
		}
		out+= arr[arr.length-1].toString();
		out += "]";
		
		System.out.println(out);
	}

	
	


	public void forEach(Consumer<Type> action) {
		Objects.requireNonNull(action);
		for(Type t : this.arr) {
			action.accept(t);
		}
	}
	public void forEachIndex(BiConsumer<Integer, Type> action) {
		Objects.requireNonNull(action);
		for(Integer x = 0; x < length; x++) {
			action.accept(x, arr[x]);
		}
		
	}
	
	public Type[] returnForEach(Function<Type,Type> action) {
		Objects.requireNonNull(action);
		Type[] returnVals = (Type[]) Array.newInstance(arr[0].getClass(), this.length);
		for(int x = 0; x < arr.length; x++) {
			returnVals[x] = action.apply(arr[x]);
		}
		return returnVals;
	}
	
	public Type[] returnForEachIndex(BiFunction<Integer, Type, Type> action) {
		Objects.requireNonNull(action);
		Type[] returnVals = (Type[]) Array.newInstance(arr[0].getClass(), this.length);
		for(Integer x = 0; x < length; x++) {
			returnVals[x] = action.apply(x, arr[x]);
		}
		return returnVals;
		
	}





}
