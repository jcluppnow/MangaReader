package com.example.mangareader.DataControllers;

public class NavigationSingleton {
    //Singleton Instance.
    private static NavigationSingleton instance;
    private enum NavigationItem { HISTORY, LIBRARY, MORE }
    private NavigationItem currentNavigationItem;

    public static NavigationSingleton getInstance()
    {
        if (instance == null)
        {
            instance = new NavigationSingleton();
        }

        return instance;
    }

    public void changeToHistory()
    {
        currentNavigationItem = NavigationItem.HISTORY;
    }

    public void changeToLibrary()
    {
        currentNavigationItem = NavigationItem.LIBRARY;
    }

    public void changeToMore()
    {
        currentNavigationItem = NavigationItem.MORE;
    }

    public boolean isCurrentItemMore()
    {
        if (currentNavigationItem == NavigationItem.MORE)
        {
            return true;
        }

        return false;
    }

    public boolean isCurrentItemHistory()
    {
        if (currentNavigationItem == NavigationItem.HISTORY)
        {
            return true;
        }

        return false;
    }

    public boolean isCurrentItemLibrary()
    {
        if (currentNavigationItem == NavigationItem.LIBRARY)
        {
            return true;
        }

        return false;
    }

    protected NavigationSingleton()
    {
        //Default navigation item is the library option.
        currentNavigationItem = NavigationItem.LIBRARY;
    }
}
