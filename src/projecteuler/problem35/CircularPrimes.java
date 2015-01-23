package projecteuler.problem35;

import java.util.*;

public class CircularPrimes {

	/**
	 * Author: James Norcross
	 * Date:1/22/15
	 * Purpose: Project Euler problem 35
	 * Description: Find the total number of circular primes less than 1000000
	 */
	
	static final int MAX_NUMBER = 1000000;
	public static void main(String[] args) {
		
		// get an array holding prime numbers to MAX_NUMBER
		PrimeNumberArray pArray = new PrimeNumberArray(MAX_NUMBER);
		int[] primes = pArray.getPrimeArray();

		
		// put it into an array list since want to reduce the access time as program continues
		// by removing items from list
		ArrayList<Integer> primesList = new ArrayList<Integer>();
		for(int i=0; i<primes.length; i++)
			primesList.add(primes[i]);


		
		ArrayList<Integer> circularPrimes = new ArrayList<Integer>();
		ArrayList<Integer> candidates = null;
		
		// step through primesList
		while(!primesList.isEmpty())
		{
			
			candidates = generateCandidates(primesList.get(0));
			boolean cyclic = false;
			
			if(candidates.isEmpty())
				primesList.remove(0);
			
			else
			{
				cyclic = true;
				
				for (Integer j : candidates)
				{
					int index = primesList.indexOf(j);
					if (index == -1)
					{
						cyclic = false;
					}
					else
						primesList.remove(index);
				}
			}
			
			if (cyclic)
			{
				for (Integer k : candidates)
					circularPrimes.add(k);
			}
			
		}
		
		for (Integer l : circularPrimes)
			System.out.println(l);
		System.out.println("The number of circular primes less than " + MAX_NUMBER + " is " + circularPrimes.size());
	}
	
	// takes an integer i which is prime, if i is a single digit returns a candidate list containing just i,
	// if i is multidigit and contains any digits besides 1,3,7,9 returns an empty candidate list, for other i
	// returns a candidate list consisting of all 'circular' permutations of i
	private static ArrayList<Integer> generateCandidates(Integer i)
	{
		ArrayList<Integer> candidates = new ArrayList<Integer>();
		
		int order = (int)Math.floor(Math.log10((double)i));
		
		// deal with single digit primes
		if (order == 0)
		{
			candidates.add(i);
			return candidates;
		}
		
		int multiplier = (int)Math.pow(10.0, (double)order);
		Integer number = i;
		
		do
		{
			candidates.add(number);
			int digit = number/multiplier;			// the first digit in number
			if ((digit%2 == 0) || (digit%5 == 0))	// all numbers with a digit which is even or 5 cannot be circular primes
			{
				candidates.clear();
				return candidates;
			}
			number = ((number-(digit*multiplier))*10) + digit;	// number with first digit rotated to end
		}
		while (!number.equals(i));
		
		return candidates;
	}

}
