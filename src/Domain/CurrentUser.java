package Domain;

public class CurrentUser {

    private static User user;

    private CurrentUser(User user) {
        CurrentUser.user = user;
    }

    public static void registerCurrentUser(User user){
        new CurrentUser(user);
    }

    public static User getCurrentUser(){
        return user;
    }
}
