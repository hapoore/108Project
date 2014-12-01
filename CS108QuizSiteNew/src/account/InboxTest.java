package account;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.junit.Test;

public class InboxTest {

	@Test
	public void test() {
		/*Account acc1 = new Account("Bob", "w", "w");
		Account acc2 = new Account("Brad", "w", "w");
		
		Account.addOnlineAccount(acc1);
		Account.addOnlineAccount(acc2);
		
		Map<String, String> test = new HashMap<String, String>();
		test.put("(0)Brad", "(0)What the fuck dude??\n\n");
		test.put("(2)Charles", "(1)Hi\n\n");
		test.put("(1)8==D", "(0)Hello?\n\n");
		
		Map<String, String> test2 = new HashMap<String, String>();
		test2.put("(0)Mime", "(0)What the fuck dude2??\n\n");
		test2.put("(2)Bob", "(1)Hi2\n\n");
		test2.put("(1)Charles", "(0)Hello?2\n\n");
		
		acc1.setInbox(test);
		acc2.setInbox(test2);
		
		acc1.getInbox().sendMessage("Brad", "How are you doing?");
		acc1.getInbox().sendMessage("Brad", "Are you there?");
		acc2.getInbox().sendMessage("Bob", "Yes baby. Let's fuck!");
		
		System.out.println(acc1.getInbox().getConversation("Brad"));
		System.out.println(acc2.getInbox().getConversation("Bob"));
		
		System.out.println(acc1.inboxOrder());
		System.out.println(acc2.inboxOrder());
		
		System.out.println(acc1.storeInbox());
		System.out.println(acc2.storeInbox());*/
		
		/*String input = "1 fish 2 fish red fish blue fish";
	     Scanner s = new Scanner(input).useDelimiter("\\s*fish\\s*");
	     System.out.println(s.nextInt());
	     System.out.println(s.nextInt());
	     System.out.println(s.next());
	     System.out.println(s.next());
	     s.close(); */
	     
	     String toSplit = "helloquiznessSucks donkeyballs and iquiznessSucks fucking haQuiznessSuckste, I mean, really haquiznessSuckste this. Lastly, quizness Sucks, q uizznessSucks, quiznessSucks, quiznesssucks";
	     String[] splitted = toSplit.split("\\s*quiznessSucks\\s*");
	     for (String split: splitted) {
	         System.out.println(split);
	     }
	     
	}

}
