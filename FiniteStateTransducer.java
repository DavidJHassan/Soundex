import java.util.ArrayList;


/*Big god class that holds all the FA related information*/
public class FiniteStateTransducer
{
	State Initial;
	State One;
	State Two;
	State Three;
	State Four;
	State Five;
	State Six;
	
	ArrayList<State> states;
	
	Condition one;
	Condition two;
	Condition three;
	Condition four;
	Condition five;
	Condition six;

	ArrayList<Condition> conditions;
	
	Transition FromInitialtoOne;
	Transition FromInitialtoTwo;
	Transition FromInitialtoThree;
	Transition FromInitialtoFour;
	Transition FromInitialtoFive;
	Transition FromInitialtoSix;
	
	ArrayList<Condition> oneRelated;
	ArrayList<Condition> twoRelated;
	ArrayList<Condition> threeRelated;
	ArrayList<Condition> fourRelated;
	ArrayList<Condition> fiveRelated;
	ArrayList<Condition> sixRelated;
	
	ArrayList<Transition> transitions;
	
	public FiniteStateTransducer()
	{
		Initial = new State("initial");
		One = new State("one");
		Two = new State("two");
		Three = new State("three");
		Four = new State("four");
		Five = new State("five");
		Six = new State("six");
		
		states = new ArrayList<State>();
		states.add(Initial);
		states.add(One);
		states.add(Two);
		states.add(Three);
		states.add(Four);
		states.add(Five);
		states.add(Six);
		
		one = new Condition("[bBpPfFvV]");
		two = new Condition("[cCsSgGjJkKqQxXzZ]");
		three = new Condition("[dDtT]");
		four = new Condition("[lL]");
		five = new Condition("[mMnN]");
		six = new Condition("[rR]");

		conditions = new ArrayList<Condition>();
		conditions.add(one);
		conditions.add(two);
		conditions.add(three);
		conditions.add(four);
		conditions.add(five);
		conditions.add(six);

		oneRelated = new ArrayList<Condition>();
		twoRelated = new ArrayList<Condition>();
		threeRelated = new ArrayList<Condition>();
		fourRelated = new ArrayList<Condition>();
		fiveRelated = new ArrayList<Condition>();
		sixRelated = new ArrayList<Condition>();

		oneRelated.add(one);
		twoRelated.add(two);
		threeRelated.add(three);
		fourRelated.add(four);
		fiveRelated.add(five);
		sixRelated.add(six);
		
		
		/* initialize the transition table */
		FromInitialtoOne = new Transition(Initial, oneRelated, One);
		FromInitialtoTwo = new Transition(Initial, twoRelated, Two);
		FromInitialtoThree = new Transition(Initial, threeRelated, Three);
		FromInitialtoFour = new Transition(Initial, fourRelated, Four);
		FromInitialtoFive = new Transition(Initial, fiveRelated, Five);
		FromInitialtoSix = new Transition(Initial, sixRelated, Six);
		
		transitions = new ArrayList<Transition>();
		transitions.add(FromInitialtoOne); 
		transitions.add(FromInitialtoTwo);
		transitions.add(FromInitialtoThree);  
		transitions.add(FromInitialtoFour);
		transitions.add(FromInitialtoFive);
		transitions.add(FromInitialtoSix);  
		/*END  initialize the transition table */
	}
	
	ArrayList<State> getStateList()
	{
		return states;
	}
	
	ArrayList<Condition> getConditionList()
	{
		return conditions;
	}
	
	ArrayList<Transition> getTransitionList()
	{
		return transitions;
	}
	
	
	
}
