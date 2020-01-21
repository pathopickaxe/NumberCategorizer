package main;

import java.awt.Point;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
@SuppressWarnings("unchecked")
public class Matrix<Type> {
	Type[][] arr;
	public int rows;
	public int columns;
	
	public int x;
	public int y;
	
	public Matrix(int x, int y) {
		this.arr = (Type[][]) new Object[y][x];
		this.x = x;
		this.y = y;
		
		rows = y;
		columns = x;
	}
	
	public Matrix(Type[][] ar) {
		arr = ar;
		x = ar[0].length;
		y = ar.length;
		rows = y;
		columns = x;
	}
	
	public void setValue(int x, int y, Type value) {
		arr[y][x] = value;
	}
	public Type getValue(int x, int y) {
		return arr[y][x];
	}
	
	public Type[] getRow(int y) {
		return arr[y];
	}
	public Type[] getColumn(int y) {
		Type[] space = (Type[]) new Object[rows];
		for(int x = 0; x < space.length; x++) {
			space[x] = arr[x][y];
		}
		
		return space;
	}
	
	
	public Type[][] getMatrix() {
		return arr;
	}
	
	public void setMatrix(Type[][] x) {
		arr = x;
		this.y = x.length;
		this.x = x[0].length;
		
		rows = this.y;
		columns = this.x;
	}
	
	public void setRow(int r, Type[] row) {
		Type[] size = (Type[]) new Object[arr[r].length];
		for(int x = 0; x < row.length; x++) {
			size[x] = row[x];
		}
		arr[r] = size;
	}
	
	
	public void setColumn(int c, Type[] column) {
		Type[] size = (Type[]) new Object[rows];
		for(int x = 0; x < column.length; x++) {
			size[x] = column[x];
		}
		for(int x = 0; x < rows; x++) {
			this.setValue(c, x, size[x]);
		}
	}
	
	
	
	
	public static int[] append(int[] x, int value) {
		int[] out = new int[x.length + 1];
		
		for(int y = 0; y < x.length; y++) {
			out[y] = x[y];
		}
		out[x.length] = value; 
		return out;
	}
	
	public static int[][] zeroMatrix(int x, int y) {
		int[][] out = new int[y][x];
		
		for(int a = 0; a < x; a++) {
			for(int b = 0; b < y; b++) {
				out[b][a] = 0;
			}
		}
		
		return out;
	}
	
	public void printArr() {
		for(Type[] x : arr) {
			System.out.println(Arrays.toString(x));
		}
	}
	
	public void forEach(Consumer<Type> action) {
		Objects.requireNonNull(action);
		for(Type[] t : this.arr) {
			for(Type ty : t) {
				action.accept(ty);
			}
		}
	}
	
	public void forEachIndex(BiConsumer<Point, Type> action) {
		Objects.requireNonNull(action);
		for(Integer x = 0; x < columns; x++) {
			for(Integer y = 0; y < rows; y++) {
				action.accept(new Point(x,y), arr[y][x]);
			}
		}
		
	}
	
	public Type[][] returnForEach(Function<Type,Type> action) {
		Objects.requireNonNull(action);
		Type[][] arrayToFill = (Type[][]) Array.newInstance(arr[0][0].getClass(), this.y, this.x);
		for(int x = 0; x < columns; x++) {
			for(int y = 0; y < rows; y++) {
				arrayToFill[y][x] = action.apply(arr[y][x]);
			}
		}
		return arrayToFill;
	}
	
	public Type[][] returnForEachIndex(BiFunction<Point, Type, Type> action) {
		Objects.requireNonNull(action);
		Type[][] arrayToFill = (Type[][]) Array.newInstance(arr[0][0].getClass(), this.y, this.x);
		for(int x = 0; x < columns; x++) {
			for(int y = 0; y < rows; y++) {
				arrayToFill[y][x] = action.apply(new Point(x,y), arr[y][x]);
			}
		}
		return arrayToFill;
		
	}
	
}
