package projecteuler.problem35;

// Class providing prime number array using sieve of eratosthenes
public class PrimeNumberArray {
	
	private int[] primeArray;
	private int greatestValue;
	private int numberOfPrimes;
	
	// constructor
	public PrimeNumberArray(int greatestValue) {
		this.greatestValue = greatestValue;
		sieveEratosthenes();
	}

	// accessors and mutators
	public int[] getPrimeArray() {
		return primeArray;
	}

	public int getNumberOfPrimes() {
		return numberOfPrimes;
	}
	
	// sieve of eratosthenes used to generate prime array
	private void sieveEratosthenes() {
		
		boolean[] primes = new boolean[greatestValue + 1];
		
		// initially all numbers prime
		for (int i=0; i<=greatestValue; i++) 
			primes[i] = true;
		numberOfPrimes = greatestValue+1;
		
		// 0 and 1 not prime
		primes[0] = false;
		primes[1] = false;
		numberOfPrimes -= 2;
		
		// step through remainder of sieve array starting at 2
		// if the value considered is prime (true), remove its multiples
		// from primes (set them false).  After all multiples of value
		// considered less than maximum value have been removed, consider
		// next greater potential prime (true)
		for (int i = 2; i*i<= greatestValue; i++) {					// only need to consider i up to sqrt(greatestValue)
			if (primes[i]) {
				for (int j = i; i*j <= greatestValue; j++) {		// can start multiples a i since smaller multiples already dealt with at smaller i
					if (primes[i*j])
					{
						primes[i*j] = false;
						numberOfPrimes--;
					}
				}
			}
		}
				
		// create array of primes from sieve array
		int primeArrayIndex = 0;
		primeArray = new int[numberOfPrimes];
		for (int i = 0; i<= greatestValue; i++) {
			if (primes[i]) {
				primeArray[primeArrayIndex] = i;
				primeArrayIndex++;
			}
		}
	}
}
