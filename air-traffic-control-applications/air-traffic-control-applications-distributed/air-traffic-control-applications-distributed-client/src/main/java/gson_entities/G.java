package gson_entities;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class G {
    
    public static void main( String[] args ) {
    
        Gson g = new Gson();
        ArrayList<User2 > i = new ArrayList< User2 >();
        i.add( new User2("user1") );
        i.add( new User2("user2") );
        
        String str = g.toJson( i );
        System.out.println( str );
        
        ArrayList<User> list = g.fromJson(str, new TypeToken<ArrayList<User>>() {}.getType());
        
        for( User user : list ) {
            System.out.println(user);

        }
    }
    
    
    public static class User
    {
        private String username;
        private String fullname;
        
        public String toString()
        {
            return username + ": ola";
        }
    }


    private static class User2
    {
        private String username;
        private String fullname = "full_gg";

        
        public User2(String username)
        {
            this.username = username;
        }
    }
   
}


