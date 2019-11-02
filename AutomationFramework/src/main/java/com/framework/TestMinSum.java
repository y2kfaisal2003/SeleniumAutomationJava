package com.framework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.testConfiguration.TestEnvironmentConfiguration;


public class TestMinSum {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String strVal =TestEnvironmentConfiguration.getTestConfiguration("Browser");
		System.out.println(strVal);
		List<Integer> num = new ArrayList<Integer>();
		for (int i=1;i<=10;i++)
			num.add(i);
		int sum=minSum(num,9);
		System.out.println(sum);
	}

	private static int minSum(List<Integer> num, int k) {
		// TODO Auto-generated method stub
		List<Integer> localNum=new ArrayList<Integer>();
		for(int i=0;i<num.size();i++)
		{
			if(num.get(i)%2!=0)
				localNum.add(i,(num.get(i)/2)+1);
			else
				localNum.add(i,(num.get(i)/2));
		}
		int sum=0;
		for(int i=0;i<k;i++)
		{
			System.out.println(localNum.get(i));
			sum+=localNum.get(i);
		}

		return sum;
	}

}
