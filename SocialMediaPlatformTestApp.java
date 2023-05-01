
import socialmedia.SocialMedia;
import socialmedia.SocialMediaPlatform;
import socialmedia.IllegalHandleException;
import socialmedia.Platform;

import java.io.IOException;

import socialmedia.AccountIDNotRecognisedException;
import socialmedia.HandleNotRecognisedException;
import socialmedia.InvalidHandleException;
import socialmedia.InvalidPostException;
import java.lang.ClassNotFoundException;
import socialmedia.HandleNotRecognisedException;
import socialmedia.PostIDNotRecognisedException;
import socialmedia.NotActionablePostException;

/**
 * A short program to illustrate an app testing some minimal functionality of a
 * concrete implementation of the SocialMediaPlatform interface -- note you will
 * want to increase these checks, and run it on your SocialMedia class (not the
 * BadSocialMedia class).
 *
 * 
 * @author Diogo Pacheco
 * @version 1.0
 */
public class SocialMediaPlatformTestApp {

	/**
	 * Test method.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		System.out.println("The system compiled and started the execution...");

		SocialMediaPlatform platform = new SocialMedia();

		
		assert (platform.getNumberOfAccounts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		//assert (platform.getTotalOriginalPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		//assert (platform.getTotalCommentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		//assert (platform.getTotalEndorsmentPosts() == 0) : "Innitial SocialMediaPlatform not empty as required.";
		
		//testing account
		//Account account = new Account();
		//String handle;
		//assert (account.getHandleIsFound(String handle) == false) : "Initial SocialMediaPlatform not empty as required.";



		Integer id,id2,id3,id4,id5,id6;
		try {
			id = platform.createAccount("my_handle");
			assert (platform.getNumberOfAccounts() == 1) : "number of accounts registered in the system does not match";

			platform.removeAccount(id);
			assert (platform.getNumberOfAccounts() == 0) : "number of accounts registered in the system does not match";

		} catch (IllegalHandleException e) {
			assert (false) : "IllegalHandleException thrown incorrectly";
		} catch (InvalidHandleException e) {
			assert (false) : "InvalidHandleException thrown incorrectly";
		} catch (AccountIDNotRecognisedException e) {
			assert (false) : "AccountIDNotRecognizedException thrown incorrectly";
		}

		try {
			id2 = platform.createAccount("my_handle2");
			id3 = platform.createAccount("my_handle3");
			id4 = platform.createAccount("my_handle4");
			id5 = platform.createAccount("my_handle5");
			id6 = platform.createAccount("my_handle6");
			assert (platform.getNumberOfAccounts() == 5) : "number of accounts registered in the system does not match";

			platform.removeAccount(id4);
			assert (platform.getNumberOfAccounts() == 4) : "number of accounts registered in the system does not match";

			platform.removeAccount(id6);
			assert (platform.getNumberOfAccounts() == 3) : "number of accounts registered in the system does not match";

			platform.createPost("my_handle2", "hello world!");
			platform.createPost("my_handle2", "i love compsci");
			platform.createPost("my_handle5", "python is better");
			assert (platform.getTotalOriginalPosts() == 1) : "number of posts of a given account";

			//endorsements
			platform.endorsePost("my_handle2", 0);
			platform.endorsePost("my_handle2", 1);
			platform.endorsePost("my_handle2", 0);
			platform.endorsePost("my_handle2", 1);
			platform.endorsePost("my_handle3", 2);
			platform.endorsePost("my_handle5", 2);
			platform.endorsePost("my_handle3", 2);
			platform.endorsePost("my_handle5", 2);
			platform.endorsePost("my_handle5", 2);
			platform.endorsePost("my_handle2", 1);
			int mostEndorsedPost = platform.getMostEndorsedPost();
			int mostEndorsedAccount = platform.getMostEndorsedAccount();
			assert (platform.getTotalEndorsmentPosts() == 10) : "endorse post";
			assert (mostEndorsedPost == 2) : "id of post with most endorsements";
			assert (mostEndorsedAccount == 4) : "id of account with most endorsements";

			//commenting
			platform.commentPost("my_handle2", 0, "i agree (jk)");
			platform.commentPost("my_handle3", 0, "no");
			assert (platform.getTotalCommentPosts() == 2) : "comment on post";

			//changing handle
			platform.changeAccountHandle("my_handle3", "handle_mine3");
			platform.showAccount("handle_mine3");

			//show post
			String showPost = platform.showIndividualPost(2);
			System.out.println(showPost);

			//saves
			platform.savePlatform("platformSave1.ser");
			platform.erasePlatform();
			assert (platform.getNumberOfAccounts() == 0): "all accounts have been deleted";
			assert (platform.getTotalOriginalPosts() == 0): "all posts have been deleted";
			platform.loadPlatform("platformSave1.ser");
			assert (platform.getNumberOfAccounts() == 3): "all accounts have been loaded";



			platform.erasePlatform();
			platform.createAccount("my_handle2");
			platform.createAccount("my_handle3");
			platform.createAccount("my_handle5");
			platform.createPost("my_handle2", "bbbbbb!");
			platform.createPost("my_handle2", "i love lobsters");
			platform.createPost("my_handle5", "anaconda is better");
			platform.endorsePost("my_handle2", 0);
			platform.endorsePost("my_handle2",1);
			platform.endorsePost("my_handle2", 1);
			platform.endorsePost("my_handle2", 0);
			platform.endorsePost("my_handle3", 1);
			platform.endorsePost("my_handle5", 0);
			platform.endorsePost("my_handle3", 0);
			platform.endorsePost("my_handle5", 0);
			platform.endorsePost("my_handle5", 1);
			platform.endorsePost("my_handle2", 1);
			platform.commentPost("my_handle3", 0, "c is better");
			platform.commentPost("my_handle2", 0, "no");
			platform.commentPost("my_handle3", 0, "shh");
			platform.commentPost("my_handle5", 2, "it is not >:()");
			platform.endorsePost("my_handle3", 14);
			platform.endorsePost("my_handle3", 15);
			platform.endorsePost("my_handle2", 15);
			platform.commentPost("my_handle5", 14, "oygowgowrghwrg)");
			StringBuilder str = platform.showPostChildrenDetails(0);
			System.out.println(str);
			platform.deletePost(1);
			platform.removeAccount("my_handle2");

			//update description change handle show account
			platform.erasePlatform();
			platform.createAccount("donkey", "neigh");
			platform.createAccount("joseph");
			platform.createAccount("mary");
			platform.createPost("donkey", "grass");
			platform.createPost("joseph", "i love carpentry");
			platform.createPost("mary", "gold is better");
			//platform.removeAccount("donkey");
			int totalAccs = platform.getNumberOfAccounts();
			System.out.println(totalAccs);
			System.out.println(platform.showAccount("mary"));
			System.out.println(platform.showAccount("joseph"));
			System.out.println(platform.showAccount("donkey"));
			platform.changeAccountHandle("mary", "newMary");
			platform.updateAccountDescription("newMary", "I changed my handle");
			System.out.println(platform.showAccount("newMary"));
			
			//removing different types of posts
			platform.erasePlatform();
			platform.createAccount("donkey", "neigh");
			platform.createAccount("joseph");
			platform.createAccount("mary");
			platform.createPost("donkey", "grass"); //post 0
			platform.createPost("joseph", "i love carpentry"); //post 1
			platform.createPost("mary", "gold is better"); //post 2
			platform.commentPost("mary", 0, "i agree"); //post 3
			platform.commentPost("joseph", 3, "same)"); //post 4
			platform.endorsePost("donkey", 0);
			platform.commentPost("donkey", 1, "so do i");
			platform.endorsePost("mary", 0);
			System.out.println(platform.showAccount("mary"));
			System.out.println(platform.showAccount("joseph"));
			System.out.println(platform.showAccount("donkey"));
			platform.removeAccount("mary");
			System.out.println(platform.showAccount("joseph"));
			System.out.println(platform.showAccount("donkey"));
			//System.out.println(platform.showAccount("mary")); //she has been deleted

			//show account
			platform.erasePlatform();
			platform.createAccount("donkey", "neigh");
			platform.createAccount("joseph");
			platform.createAccount("mary");
			platform.createPost("donkey", "grass"); //post 0
			platform.createPost("joseph", "i love carpentry"); //post 1
			platform.createPost("mary", "gold is better"); //post 2
			platform.commentPost("mary", 0, "i agree"); //post 3
			platform.commentPost("joseph", 3, "same)"); //post 4
			platform.endorsePost("donkey", 0);
			platform.commentPost("donkey", 1, "so do i");
			platform.endorsePost("mary", 0);
			platform.endorsePost("joseph", 2);
			platform.endorsePost("joseph", 2);
			System.out.println(platform.showAccount("mary"));
			assert(platform.showAccount("mary") == "ID: 2\nHandle: mary\nDescription: \nPost count: 3\nEndorsement count: 2\n") : "mary's acc";




		} catch (InvalidPostException e) {
			assert (false) : "InvalidPostException thrown incorrectly";
		} catch (HandleNotRecognisedException e) {
			assert (false) : "HandleNotRecognisedException thrown incorrectly";
		} catch (IllegalHandleException e) {
			assert (false) : "IllegalHandleException thrown incorrectly";
		} catch (InvalidHandleException e) {
			assert (false) : "InvalidHandleException thrown incorrectly";
		} catch (AccountIDNotRecognisedException e) {
			assert (false) : "AccountIDNotRecognizedException thrown incorrectly";
		}
		catch (PostIDNotRecognisedException e) {
			assert (false) : "PostIDNotRecognisedException thrown incorrectly";
		}
		catch (NotActionablePostException e) {
			assert (false) : "NotActionablePostException thrown incorrectly";
		}
		catch (IOException e) {
			assert (false) : "IOException thrown incorrectly";
		}
	    catch (ClassNotFoundException e) {
			assert (false) : "ClassNotFoundException thrown incorrectly";
		}

	
	try {
		id = platform.createAccount("my_handle");
		assert (id != null) : "createAccount should return a valid accountID";
		System.out.println("createAccount returned a valid accountID");
		assert (id >= 0) : "createAccount should return a positive interger as its ID";
		System.out.println("createAccount returns a positive integer as its ID");
		assert (platform.getNumberOfAccounts() == 1) : "number of accounts registered in the system does not match";
		System.out.println("Number of accounts registered in the system matches");

		try {
			platform.createAccount("my_handle");
			assert (false) : "Expected IllegalHandleException to be thrown";
		} catch (IllegalHandleException e) {
			System.out.println("IllegalHandleException was thrown as expected");
		}

		try {
			platform.createAccount(" handle");
			assert (false) : "Expected InvalidHandleException due to presence of a whitespace";
		} catch (InvalidHandleException e) {
			System.out.println("InvalidHandleException was thrown as expected");
		}

		try {
			platform.createAccount("abcdefghijklmnopqrstuvwxyznowiknowmyabc");
			assert (false) : "Expected InvalidHandleException due to handle > 30 characters";
		} catch (InvalidHandleException e) {
			System.out.println("InvalidHandleException was thrown as expected");
		}

		platform.removeAccount(id);
		assert (platform.getNumberOfAccounts() == 0) : "number of accounts registered in the system does not match";

	} catch (IllegalHandleException e) {
		assert (false) : "IllegalHandleException thrown incorrectly";
	} catch (InvalidHandleException e) {
		assert (false) : "InvalidHandleException thrown incorrectly";
	} catch (AccountIDNotRecognisedException e) {
		assert (false) : "AccountIDNotRecognizedException thrown incorrectly";
	}
	try {
		id = platform.createAccount("randomhandle", "This is my bio");
		assert (id != null) : "createAccount should return a valid accountID";
		System.out.println("createAccount returned a valid accountID");
		assert (id >= 0) : "createAccount should retrurn a positive interger as its ID";
		System.out.println("createAccount returnes a positive interger as its ID");
		assert (platform.getNumberOfAccounts() == 1) : "number of accounts registered in the system does not match";
		System.out.println("Number of accounts registered in the system matches");

		try {
			platform.createAccount("randomhandle", "This is my bio");
			assert (false) : "Expected IllegalHandleException to be thrown";
		} catch (IllegalHandleException e) {
			System.out.println("IllegalHandleException was thrown as expected");
		}

		try {
			platform.createAccount("  randomhandle", "This is my bio");
			assert (false) : "Expected InvalidHandleException due to presence of a whitespace";
		} catch (InvalidHandleException e) {
			System.out.println("InvalidHandleException was thrown as expected");
		}

		try {
			platform.createAccount("abcdefghijklmnopqrstuvwxyznowiknowmyabc", "This is my bio");
			assert (false) : "Expected InvalidHandleException due to handle > 30 characters";
		} catch (InvalidHandleException e) {
			System.out.println("InvalidHandleException was thrown as expected");
		}
		platform.removeAccount("randomhandle");
		assert (platform.getNumberOfAccounts() == 0) : "number of accounts registered in the system does not match";

	} catch (HandleNotRecognisedException e) {
		assert (false) : "HandleNotRecognised thrown incorrectly";
	} catch (IllegalHandleException e) {
		assert (false) : "IllegalHandleException thrown incorrectly";
	} catch (InvalidHandleException e) {
		assert (false) : "InvalidHandleException thrown incorrectly";
	}


	//checking a change to an existing handle is not allowed
	try{
		platform.createAccount("my_handle2");
		platform.createAccount("my_new_handle");
		platform.changeAccountHandle("my_handle2", "my_new_handle");
		assert(false): "IllegalHandleException not thrown";
	} catch(IllegalHandleException|InvalidHandleException|HandleNotRecognisedException e){
	}
	//testing changing to an invaLid handle 
	try{
		int accountID= platform.createAccount("my_handle3");
		platform.changeAccountHandle("my_handle3", "@12345678910abcdefghijklmnopqrstuv");
		assert(false): "InvalidHandleException not thrown";
	} catch(InvalidHandleException|IllegalHandleException|HandleNotRecognisedException e){
	}
	try{
		int accountID= platform.createAccount("my_handle3");
		platform.changeAccountHandle("my_handle3", "");
		assert(false): "InvalidHandleException not thrown";
	} catch(InvalidHandleException|IllegalHandleException|HandleNotRecognisedException e){
	}
	try{
		int accountID= platform.createAccount("my_handle3");
		platform.changeAccountHandle("my_handle3", "my Handle   ");
		assert(false): "InvalidHandleException not thrown";
	} catch(InvalidHandleException|IllegalHandleException|HandleNotRecognisedException e){
	}
	//testing changing handle of an account that doesnt exist
	try{
		platform.changeAccountHandle("acc234", "new_handlefornon-existent");
		assert(false) : "HandleNotRecognisedException not thrown";
		} catch (InvalidHandleException|IllegalHandleException|HandleNotRecognisedException e){
	}

	// Checking correct exception is thrown for updateAccountDescription
	try{
		platform.updateAccountDescription("my_handle", "Testing is fun");
		assert(false) : "HandleNotRecognisedException not thrown";
	} catch (HandleNotRecognisedException e){
	}

	}
}
