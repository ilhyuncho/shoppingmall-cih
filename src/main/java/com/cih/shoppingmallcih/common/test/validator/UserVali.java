package com.cih.shoppingmallcih.common.test.validator;

public class UserVali {

    private String userName;

    @Password
    private String password;

    public UserVali(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UserVali{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
