package ru.itis.taskmanager.util.constant;

public class Constant {

    public static final String USERNAME_REGEX = "^[a-zA-Z0-9._-]{3,}$";
    public static final String PASSWORD_REGEX  = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";


}
