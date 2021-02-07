package constants;

import utils.PropertyReader;

public interface ITestConstants {
    //for login
    String USERNAME = System.getenv().getOrDefault("username", PropertyReader.getProperty("userneme"));;
    String PASSWORD = System.getenv().getOrDefault("password", PropertyReader.getProperty("password"));
    String ERROR_MESSAGE_EMPTY = "Epic sadface: Username is required";
    String ERROR_MESSAGE_EMPTY_PASSWORD = "Epic sadface: Password is required";
    String ERROR_MESSAGE_LOGIN_OR_PASSWORD = "Epic sadface: Username and password do not match any user in this service";

    //for product
    String PRODUCT_NAME = "Sauce Labs Backpack";
    String PRODUCT_NOT_FOUND = "not found";
}
