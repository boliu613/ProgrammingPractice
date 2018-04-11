package myPackage;

import java.util.ArrayList;

public class MyClass {

	public double foo(ArrayList<Integer> x) {
		if (x.size() == 1) {
			return x.get(0);
		}
		if (x.size() == 0) {
			return 0;
		}
		ArrayList<Integer> temp = new ArrayList<Integer>();
		ArrayList<Integer> temp2 = new ArrayList<Integer>();
		for (int i = 0; i <= x.size() / 2 - 1; i++) {
			temp.add(x.get(i));
		}
		for (int i = x.size() / 2; i < x.size(); i++) {
			temp2.add(x.get(i));
		}
		double totalSize = temp.size() + temp2.size();
		double bar1 = foo(temp) * temp.size();
		double bar2 = foo(temp2) * temp2.size();
		return (bar1 + bar2) / totalSize;
	}

	public static void main(String[] args) {
		int r = 1;
		r = ++r;
		System.out.println(r);
	}
}
