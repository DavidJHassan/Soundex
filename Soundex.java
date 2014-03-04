import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.io.*;

public class Soundex
{
	/*checks to see what pattern the current character matches and then lets the State Machine determine if such a transition exists. If it does the SM is now in a new state else a dead state */
	public static int checkCondition(StateMachine SM, ArrayList<Condition> conditions, String currentChar)
	{
		int j = checkLoop(conditions, currentChar);
	
		if(j == -1)
		{
			return -1;//Symbol not recognized.
		}
		else
		{	
			SM.apply(conditions.get(j));
			if(SM.current == null)
			{
				return -2;//Transition from state given symbol not valid
			}
			
			return j;//Everything was succesful we return the accepted condition index for reference and debugging if needed
		}
	}
	
	public static String replaceDuplicates(String currentLine)//Remove duplicate adjacent characters
	{
		String regexs[] = { "[bBpPfFvV]", "[cCsSgGjJkKqQxXzZ]", "[dDtT]", "[lL]", "[mMnN]", "[rR]" };
		
		for(int i = 0; i< 6; i++)
		{
			String r = "("+regexs[i]+"|h|w){2,}";
			Pattern p = Pattern.compile(r);
			Matcher m = p.matcher(currentLine);
			
			if(m.find())
			{
				currentLine = currentLine.replace(currentLine.substring(m.start(), m.end()), ""+currentLine.charAt(m.start()));
			}
		}

		return currentLine;
	}
	
	
	public static String getSoundex(StateMachine SM, State Initial, ArrayList<Condition> conditions,ArrayList<Transition> transitions, String currentLine)
	{
			
			String currentCode ="";	
			currentLine = replaceDuplicates(currentLine);
			char [] chars = currentLine.toCharArray();
			
			currentCode = ""+chars[0];
			for(int i = 1; i<chars.length; i++)
			{
				String currentChar = ""+chars[i];
				int code = checkCondition(SM, conditions, currentChar);
				
				if(code == -1 || code == -2)
				{
					currentChar = "";
					
				}
				else
				{
					currentCode += (code+1);
				}
				
				SM.current = Initial;
			}
			
			currentCode += "0000";
			currentCode = currentCode.substring(0,4);
			
			return currentCode;
	}

	public static void main(String args[]) throws IOException, FileNotFoundException
	{
		FiniteStateTransducer FA = new FiniteStateTransducer();
		ArrayList<State> states = FA.getStateList();
		ArrayList<Condition> conditions = FA.getConditionList();
		ArrayList<Transition> transitions = FA.getTransitionList();//Transition Table
		
		StateMachine SM = new StateMachine("SM", states.get(0), transitions);//states.get(0) is the Initial State
		
		BufferedReader in = null;
		in = new BufferedReader(new FileReader(args[0]));
		String currentLine = null;
		
		while((currentLine = in.readLine()) != null)
		{
			String soundex = getSoundex(SM, states.get(0), conditions, transitions, currentLine);//Finds soundex on currentLine
			System.out.println(currentLine+" -> "+soundex);
		} 
	}
}
