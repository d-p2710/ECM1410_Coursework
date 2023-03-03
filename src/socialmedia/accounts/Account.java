package socialmedia.accounts;

import socialmedia.*;

public class Account extends SocialMedia {
    protected String handle;
    protected int id;
    protected String description;

    Account(String handle) {
        this.handle = handle;
    }

    Account(String handle, String description){
        this.handle = handle;
        this.description = description;
    }

    public Account() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    protected void setid(int id) {
    }

    public Account getHandle(){
        return Account.handle;
    }

    public void setHandle(String newHandle){
        this.handle=newHandle;
    }
}

