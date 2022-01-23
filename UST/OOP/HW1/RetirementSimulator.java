/**
 * RetirementSimulator takes user input & calculates a yearly return investment for a retirement account until the user retires.
 * Contains total 14 methods. 1 main, 10 void, and 3 string methods.
 * Bugs/errors:0
 * @author AndyPhan
 */
import java.util.Scanner;
import java.util.Random;
public class RetirementSimulator {
	
	private static Scanner scan;					//create a scanner variable
	private static Random rand;						//create the random generator
	private static double initVest;					//initial investment in your retirement account
	private static double initSal;					//your initial salary
	private static double newSal;					//your new salary for the following year
	private static double maxSal;					//the maximum salary you have at the end of work career
	private static double salVestUpperRange;		//as a percentage, the max you can invest from your salary
	private static double salVestLowerRange;		//as a percentage, the min you can invest from your salary
	private static double vestReturnUpperRange;		//as a percentage, the max money you expect to return
	private static double vestReturnLowerRange;		//as a percentage, the min money you expect to return
	private static double yearlyRaiseUpperRange;	//as a percentage, the max your salary is raised
	private static double yearlyRaiseLowerRange;	//as a percentage, the min your salary is raised
	private static int yearsTilRetirement;			//the number of years until you plan on retiring
	private static String[] histo;					//String array that holds # signs to display in the histogram

	private static double minSalVest;				//as a percentage, this holds min value for your salary invested  			
	private static double maxSalVest;				//as a percentage, this holds max value for your salary invested
	private static double avgSalVest;				//as a percentage, this holds avg value for your salary invested
	
	private static double minVestReturn;			//as a percentage, this holds min value for your investment returns
	private static double maxVestReturn;			//as a percentage, this holds max value for your investment returns
	private static double avgVestReturn;			//as a percentage, this holds avg value for your investment returns
	
	private static double minYearlyRaise;			//as a percentage, this holds min value for your salary increase
	private static double maxYearlyRaise;			//as a percentage, this holds max value for your salary increase
	private static double avgYearlyRaise;			//as a percentage, this holds avg value for your salary increase
	
	/**
	 * main method, used to call userInputs(), echoUserInput(), 
	 * runSimulator(), and printResults().
	 * @param args takes in command lines
	 */
	public static void main(String[] args) {
		userInputs();
		echoUserInput();
		runSimulation();
		printResults();
	}//end main
	
	/**
	 * userInputs will prompt and take info from the user
	 * The scanner is initiated and closed at the end of this method
	 */
	private static void userInputs() {
		scan = new Scanner(System.in);
		getInitialVest();
		getInitialSal();
		salVestRange();
		vestReturnRange();
		getYearlyRaiseRange();
		getYearsTilRetire();
		scan.close();
	}//end userInputs
	/**
	 * runSimulation will calculate the new salary, mins, maxs, avgs, random number, and 
	 * investment and return percentages. It also creates and prints a histogram depicting
	 * the percentage of your returns.  
	 */
	private static void runSimulation() {
		rand = new Random();
		//create a double array to store the investment account balance for every year
		double[] yearlyVestAccountVal = new double[yearsTilRetirement+1];
		//create the histogram array
		histo = new String[yearsTilRetirement+1];
		
		//year 0 will hold all init values
		yearlyVestAccountVal[0] = initVest;
		maxSal = initSal;
		
		//loop runs through every year until the user retires
		for(int i=0; i<=yearsTilRetirement; i++) {
			//generates random values for the year. All code inside this loop is inside if statements. Nothing is calculated for year 0.
			double salVestPercent = rand.nextDouble()*(salVestUpperRange - salVestLowerRange) + salVestLowerRange;
			double vestReturnPercent = rand.nextDouble()*(vestReturnUpperRange - vestReturnLowerRange) + vestReturnLowerRange;
			double yearlyRaisePercent = rand.nextDouble()*(yearlyRaiseUpperRange - yearlyRaiseLowerRange) + yearlyRaiseLowerRange;
			
			//year 1, set the min,max, and avg to the first generated random values
			if(i==1) {
				minSalVest = salVestPercent;
				maxSalVest = salVestPercent;
				avgSalVest = salVestPercent;
				
				minVestReturn = vestReturnPercent;
				maxVestReturn = vestReturnPercent;
				avgVestReturn = vestReturnPercent;
				
				minYearlyRaise = yearlyRaisePercent;
				maxYearlyRaise = yearlyRaisePercent;
				avgYearlyRaise = yearlyRaisePercent;
				
			}//end i==1
			//This happens every year except for the 1st year
			if(i>0) {
			//equation to add to investment account from percent saved
				initVest = (initVest * ((salVestPercent/100)+1));
			//equation to add to investment account from percent from investment returns
				initVest = (initVest * ((vestReturnPercent/100)+1));
			//equation to add to salary from percent raise
				newSal = (initSal * ((yearlyRaisePercent/100)+1));
			//set the maximum salary
				if(maxSal<newSal) {
					maxSal = newSal;
				}
				initSal = newSal;
			//store new value for investment account in the array
				yearlyVestAccountVal[i]=initVest;
			}//end i>0
			//set the mins and maxs after generating new randoms every year by checking if they are > or <
			if(i>=2) {
				if(minSalVest>salVestPercent) {
					minSalVest = salVestPercent;
				}
				if(maxSalVest<salVestPercent) {
					maxSalVest = salVestPercent;
				}
				if(minVestReturn>vestReturnPercent) {
					minVestReturn = vestReturnPercent;
				}
				if(maxVestReturn<vestReturnPercent) {
					maxVestReturn = vestReturnPercent;
				}
				if(minYearlyRaise>yearlyRaisePercent) {
					minYearlyRaise = yearlyRaisePercent;
				}
				if(maxYearlyRaise<yearlyRaisePercent) {
					maxYearlyRaise = yearlyRaisePercent;
				}
			//sum up the averages to divide by the total years. but that comes later 
				avgSalVest = avgSalVest + salVestPercent;
				avgVestReturn = avgVestReturn + vestReturnPercent;
				avgYearlyRaise = avgYearlyRaise + yearlyRaisePercent;
			}//end i>=2
		}//end loop
		//calculate the averages
		avgSalVest = avgSalVest/yearsTilRetirement;
		avgVestReturn = avgVestReturn/yearsTilRetirement;
		avgYearlyRaise = avgYearlyRaise/yearsTilRetirement;
		//populate the histogram array. Year 0 has to have a # sign. for every year after than another # sign is added
		for(int j=0; j<histo.length; j++) {
			if (j == 0) {
				histo[j] = "#";
			}
			else {
				histo[j] = "#";
				for (int k = 0; k < j-1; k++) {
					histo[j] = histo[j] + "#";
				}
			}
		}//end loop
		//put the histogram and the retirement balance together. print out the # sign and the balance next to each other for any given year
		for (int i = 0; i < histo.length; i++) {
			pt(i + ": " + histo[i]);
			prntf(" $",yearlyVestAccountVal[i]);
			pt("\n");
		}
	}//end runSimulation
	
	/**
	 * printResults will display the final values for minimum, maximum, and average using the pt, prnt, and prntf methods.
	 */
	private static void printResults() {
		prnt("\nThe simulation generated the following values:");
		System.out.print("Yearly percentage of the salary saved--");
		prntf("min:", minSalVest);
		pt("%, ");							//Have to print the % sign separately or else the printf function gets confused and I get an error
		prntf("max:", maxSalVest);
		pt("%, ");
		prntf("avg:", avgSalVest);
		prnt("%");
		
		pt("Range of yearly returns--");
		prntf("min:", minVestReturn);
		pt("%, ");
		prntf("max:", maxVestReturn);
		pt("%, ");
		prntf("avg:", avgVestReturn);
		prnt("%");
		
		pt("Yearly percentage of salary increase--");
		prntf("min:",minYearlyRaise);
		pt("%, ");
		prntf("max:", maxYearlyRaise);
		pt("%, ");
		prntf("avg:",avgYearlyRaise);
		prnt("%");
		
		prntf("The maximum salary used in the simulation was: $",maxSal);
		
	}//end printResults
	
	/**
	 * getInitialVest prompts the user for an initial investment and the scanner will take in the 
	 * user's input. The while loop will check to make sure the input is greater than 0.
	 */
	private static void getInitialVest() {
		do {
			prnt("Enter the initial investment (must be positive): ");
			initVest = scan.nextDouble();
		}while(initVest < 0);								//check to make sure its positive	
		
	}//end getInitialVest
	
	/**
	 * getInitialSal prompts the user for an initial salary and the scanner will take in the 
	 * user's input. The while loop will check to make sure the input is greater than 0. 
	 */
	private static void getInitialSal() {
		do {
			prnt("Enter your initial salary (must be positive): ");
			initSal = scan.nextDouble();
		}while(initSal < 0);								//check to make sure its positive
	}//end getInitialSal
	
	/**
	 * salVestRange prompts the user for an upper and lower bound for the percentage of salary you wish to 
	 * invest. The scanner will take in the user's input. The while loop will check to make sure the inputs 
	 * are greater than 0 and greater than the lower bound. 
	 */
	private static void salVestRange() {
		do {
			prnt("As a percentage of salary, enter the min amount you want to save for retirement: ");
			salVestLowerRange = scan.nextDouble();
		}while(salVestLowerRange < 0);						//check to make sure its positive
		
		do {
			prnt("As a percentage of salary, enter the max amount you want to save for retirement: ");
			salVestUpperRange = scan.nextDouble();
		}while(salVestUpperRange < salVestLowerRange);		//check to make sure its bigger than min
	}//end salVestRange
	
	/**
	 * vestReturnRange prompts the user for an upper and lower bound for the percentage of investments you
	 * expect to return to your account. The scanner will take in the user's input. The while loop will 
	 * check to make sure the upper bound is greater than the lower bound.
	 */
	private static void vestReturnRange() {
		prnt("As a percentage, please enter the expected min yearly return for your investments: ");
		vestReturnLowerRange = scan.nextDouble();
		
		do {
			prnt("As a percentage, please enter the expected max yearly return for your investments: ");
			vestReturnUpperRange = scan.nextDouble();
		}while(vestReturnUpperRange < vestReturnLowerRange);	//check to make sure its bigger than min
		
	}//end vestReturnRange
	
	/**
	 * getYearlyRaiseRange prompts the user for an upper and lower bound for the percentage of salary
	 * you expect to be raised per given year. The scanner will take in the user's input. The while
	 * loop will check to make sure the inputs is not lower -100 and the upper bound is greater than
	 * the lower bound.
	 */
	private static void getYearlyRaiseRange() {
		do {
			prnt("As a percentage, the min pay raise you expect to receive in any given year: ");
			yearlyRaiseLowerRange = scan.nextDouble();
		}while(yearlyRaiseLowerRange < -100);				//check to make sure you cannot lose your salary
		
		do {
			prnt("As a percentage, the max pay raise you expect to receive in any given year: ");
			yearlyRaiseUpperRange = scan.nextDouble();
		}while(yearlyRaiseUpperRange < yearlyRaiseLowerRange);		//check to make sure its bigger than min
	}//end getYearlyRaiseRange
	
	/**
	 * getYearsTilRetire prompts the user for the number of years until they are expected to retire.
	 */
	private static void getYearsTilRetire() {
		prnt("In how many years do you plan to retire? ");
		yearsTilRetirement = scan.nextInt();
	}//end getYearsTilRetire
	
	/**
	 * echoUserInput displays what the user entered after the user has finished entering their
	 * numbers. This method uses the pt, prnt, and prntf methods to display the values.
	 */
	private static void echoUserInput() {
		System.out.print("\n\nYou entered:");
		System.out.printf("\nInitial Investment: $%.2f",initVest);
		prntf("\nInitial Salary: $", initSal);
		prnt("\nYearly Percentage of salary saved: "+salVestLowerRange+"% - "+salVestUpperRange+"%");
		prnt("Range of yearly returns: "+vestReturnLowerRange+"% - "+vestReturnUpperRange+"%");
		prnt("Yearly salary increase range: "+yearlyRaiseLowerRange+"% - "+yearlyRaiseUpperRange+"%");
		prnt("Number of years until retirement: "+yearsTilRetirement+"\n");
	}
	
	/**
	 * Ok, so I have a not so funny story behind this method, pt(), and the other two, prnt() and prntf(). 
	 * I used System.out.print(), System.out.println(), and System.out.printf() so many times for testing 
	 * and for displaying values in this program that my fingers cramped up and I said a bunch of no-no 
	 * words because it hurt like hell. Anyways, these next few methods are a shortcut so I can save 
	 * myself a little time and space. Dear reader, I acknowledge the fact that this method header is 
	 * extremely long and wordy and it may hurt my grade, but I want you to know that these methods has
	 * saved me a world of pain and I have no regrets.     
	 * @param s takes in a string
	 * @return the string and it is printed.
	 */
	private static String pt(String s) {
		System.out.print(s);
		return s;
	}
	
	/**
	 * prnt is a shortcut for System.out.println() because typing that got too repetitive.
	 * @param s takes in a string
	 * @return the string and it is printed with a new line.
	 */
	private static String prnt(String s) {
		System.out.println(s);
		return s;
	}
	
	/**
	 * prntf is a shortcut for System.out.printf() for specifically displaying money and percent values
	 * to up to 2 decimal values.
	 * @param s takes in a string
	 * @param d takes in the money amount or percentage in the form of a double
	 * @return the string and the value shorted/elongated to 2 places after the decimal.
	 */
	private static String prntf(String s, double d) {
		System.out.printf(s+"%.2f", d);
		return s;
	}

}//end class
