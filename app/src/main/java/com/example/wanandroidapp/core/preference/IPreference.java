package com.example.wanandroidapp.core.preference;

/**
 * The interface Preference.
 *
 * @author: ODM
 * @date: 2019 /7/14
 */
public interface IPreference {

    /**
     * Sets login status.
     *
     * @param isLogin the is login
     */
    void setLoginStatus(boolean isLogin);

    /**
     * Gets login status.
     *
     * @return the login status
     */
    boolean getLoginStatus();

    /**
     * Sets login account.
     *
     * @param username the username
     */
    void setLoginAccount(String username);

    /**
     * Gets login account.
     *
     * @return the login account
     */
    String getLoginAccount();
}
