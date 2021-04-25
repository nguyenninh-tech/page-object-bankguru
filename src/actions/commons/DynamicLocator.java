package commons;

public class DynamicLocator {
    public static void main(String[] arg) {
        String DYNAMIC_MENU_LINK = "//a[text()]='%s'";
        String DYNAMIC_MENU_CONTEXT_LINK="//a[text()='%s']//span[text()='%s']";

        clickToElement(DYNAMIC_MENU_LINK, "Manager");
        clickToElement(DYNAMIC_MENU_LINK, "New Customer");
        clickToElement(DYNAMIC_MENU_LINK, "Edit Customer");
        clickToElement(DYNAMIC_MENU_LINK, "New Account");
        clickToElement(DYNAMIC_MENU_LINK, "Edit Account");
        clickToElement(DYNAMIC_MENU_LINK, "Delete Account");
        clickToElement(DYNAMIC_MENU_LINK, "Logout");
        clickToElement(DYNAMIC_MENU_CONTEXT_LINK,"Edit Customer","Save");
     }

//    public static void clickToElement(String locator) {
//        System.out.println("Click to element" + locator);
//    }
//    public static void clickToElement(String locator, String pageName) {
//        locator = String.format(locator, pageName);
//        System.out.println("Click to element" + locator);
//
//    }
//    public static void clickToElement(String locator, String pageName,String value) {
//        locator = String.format(locator, pageName,value);
//        System.out.println("Click to element" + locator);
//
//    }
     public static void clickToElement(String locator,String... value)
    {
        locator=String.format(locator,(Object[]) value);
        System.out.println("Click to element" + locator);

    }

}
