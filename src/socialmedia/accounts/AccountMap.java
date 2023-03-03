package socialmedia.accounts;

import socialmedia.AccountIDNotRecognisedException;
import socialmedia.HandleNotRecognisedException;
import socialmedia.IllegalHandleException;
import socialmedia.InvalidHandleException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AccountMap extends Account{
    private static AccountMap instance;
    protected static Map<Integer, Account> accountMap= new HashMap<>();

    public AccountMap() {
        super();
    }

    private static AccountMap getInstance(){
        if (instance==null){
            instance=new AccountMap();
        }
        return instance;
    }
//    public AccountMap(String handle,String description) {
//        super(handle, description);
//    }
//
//    public AccountMap(String handle){
//        super(handle);
//    }

    public void idCreation(Account account){
        int id=accountMap.size()+1;
        account.setid(id);
       accountMap.put(id, account);
    }

    public int idFromHandle(Object handle){
        int id=0;
        for (Account account: accountMap.values()){
            if (account.getHandle().equals(handle)){
                id=account.getId();
            }
        }
        return id;
    }

    public int createAccount(String handle){
        Account account= new Account(handle);
        idCreation(account);
        accountMap.put(id, account);
        return id;
    }

    public int createAccount(String handle, String description){
        Account account= new Account(handle, description);
        idCreation(account);
        accountMap.put(id, account);
        return id;
    }

//    public void removeAccount(int id) {
//        accountMap.containsKey(id)? accountMap.remove(id): throw; //
//
//    }

//    public void removeAccount(String handle){
//        Account account= accountMap.remove(handle);
//
////                get(handle);
////        if (account!=null){
////            id=account.getId();
//        }
//    }

    public void removeAccount(String handle){

        Account toRemove = null;
        for(Map.entry<Integer,Account> entry : accountMap.entrySet()){
            Account account=entry.getValue();
            if (account.getHandle().equals(handle)){
                toRemove=account;
            }
                    handle)) {
                accountMap.remove(entry.get());
                break;
            }
        }
    }

//    public void changeAccountHandle(String oldHandle, String newHandle)
//            throws HandleNotRecognisedException, IllegalHandleException, InvalidHandleException {
//        accountMap.contains
//
//    }
//
//    public void updateAccountDescription(String handle, String description)
//            throws HandleNotRecognisedException {
//        // TODO Auto-generated method stub
//
//    }
//
//    public String showAccount(String handle) throws HandleNotRecognisedException {
//        // TODO Auto-generated method stub
//        return null;
//    }
    public int getNumberOfAccounts() {
        // TODO Auto-generated method stub
        return accountMap.size();
    }

}


}


