package socialmedia.posts;

import socialmedia.accounts.Account;

public class Post extends Account{
    super(handle);
    public String message;
    public int id;

    Post(String handle, String message){
        this.handle=handle;
        this.message=message;
    }

    public void setid(int id){
        this.id=id;
    }
}

