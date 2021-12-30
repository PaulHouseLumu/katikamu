package Controller;

public interface User {
    /**
     * Used to login different users of the system in different ways
     * @param username
     * @param password
     * @return
     */
    public boolean loginUser(String username,String password);
}
