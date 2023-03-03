package socialmedia.posts;

import socialmedia.accounts.Account;

import java.util.HashMap;
import java.util.Map;

public class PostMap extends Post{
    public Map<Integer, Post> postMap=new HashMap<>();

    PostMap(String handle, String message) {
        super(handle, message);
    }

    public void idCreation(Post post){
        int id=postMap.size()+1;
        post.setid(id);
        postMap.put(id, post);
    }
}
