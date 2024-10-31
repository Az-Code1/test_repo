import java.util.EmptyStackException;

public class User {


    public boolean verifyLogin(String username, String password){

        String name = "", pass = ""; // set username and password to empty string
        // Some code        get username and password from DB for verification
        if (name.equals("") || pass.equals(""))
            throw new EmptyStackException(); // exit if it fails to get username
        return username.equalsIgnoreCase(name) && password.equals(pass); // return true if username
                                                                         // and password match
    }

    public int addUser(String surname,String firstName, String otherName, String userName, String password,
                       String previledge, int phoneNumber1, int phoneNumber2){

        try { //Add user to database where username is a unique word

            return 1;
        } catch (Exception e) {
            return -1; //if user not successfully added to database
        }
    }

    public int deleteUser(String userName, String adminPassword){
        try {

            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    public int adminModifyUser(String userName, String adminPassword){
        // this method is for changing users details by the admin
        try {

            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    public int userModifySelf(String userName, String password, int phoneNumber){
        // this method is for changing users details User himself
        try {

            return 1;
        } catch (Exception e) {
            return -1;
        }
    }
    public int muteUser(String userName, String adminPassword) {
        // This method is for inactivating a user by the admin
        try {

            return 1;
        } catch (Exception e) {
            return -1;
        }

    }

    public void changePassword(String userName, String oldPassword, String newPassword){
        // This method is for changing user password by the user himself

    }

    public void changePreviledge(String userName, String adminPassword, String oldPreviledge, String newPreviledge){
        // This method is for changing the user previledge by the admin
    }

    public void searchUser(String userName, int phoneNumbers){


    };


}