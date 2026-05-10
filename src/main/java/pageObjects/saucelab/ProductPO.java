package pageObjects.saucelab;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.saucelab.ProductPageUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductPO extends BasePage {
    private WebDriver driver;

    public ProductPO(WebDriver driver) {
        this.driver = driver;
    }


    public void sortBy(String sortCriteria) {
        waitElementClickable(driver, ProductPageUI.SORT_DROPDOWN);
        selectItemInDropDown(driver, ProductPageUI.SORT_DROPDOWN, sortCriteria);
    }

    public String getSortItemSelected() {
        waitElementVisible(driver, ProductPageUI.SORT_DROPDOWN);
        return getSelectedItemInDropdown(driver, ProductPageUI.SORT_DROPDOWN);
    }

    public boolean isProductNameSortAscending() {
        //Lấy ra hết tất cả element chứa Product name
        List<WebElement> productName = getListElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);

        //Khai báo 1 mảng d/s A
        ArrayList<String> productList = new ArrayList<String>();
        //ArrayList<Date> productListDate = new ArrayList<Date>();

        //Dùng vòng lặp lấy Product name text lưu vào d/s A
        System.out.println("Sort Name tăng dần");
        for (WebElement product:productName){
            System.out.println(product.getText());
            productList.add(product.getText());

            //xly data theo format date
            //productListDate.add(new SimpleDateFormat("dd/MM/yyyy").parse(product.getText().replace(",","")));
        }

        //Khai báo 1 mảng d/s B lấy dữ liệu từ A qua
        ArrayList<String> sortedList = new ArrayList<String>();
        for (String products:productList){
            sortedList.add(products);
        }

        // sort ascending d/s B
        Collections.sort(sortedList);

        // A = B (sau khi đã sort)
        return productList.equals(sortedList);
    }

    public boolean isProductNameSortDescending() {
        //Lấy ra hết tất cả element chứa Product name
        List<WebElement> productName = getListElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);

        //Khai báo 1 mảng d/s A
        ArrayList<String> productList = new ArrayList<String>();

        //Dùng vòng lặp lấy Product name text lưu vào d/s A
        System.out.println("Sort Name giảm dần");
        for (WebElement product:productName){
            System.out.println(product.getText());
            productList.add(product.getText());
        }

        //Khai báo 1 mảng d/s B lấy dữ liệu từ A qua
        ArrayList<String> sortedList = new ArrayList<String>();
        for (String products:productList){
            sortedList.add(products);
        }

        // sort ascending d/s B
        Collections.sort(sortedList);

        //sort lại B thành Descending
        Collections.reverse(sortedList);

        // A = B (sau khi đã sort)
        return productList.equals(sortedList);
    }

    public boolean isProductPriceSortAscending() {
        //Lấy ra hết tất cả element chứa Product price
        List<WebElement> productPrice = getListElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);

        //Khai báo 1 mảng d/s A
        ArrayList<Float> productList = new ArrayList<Float>();

        //Dùng vòng lặp lấy Product price text lưu vào d/s A
        System.out.println("Sort Price tăng dần");
        for (WebElement product:productPrice){
            System.out.println(product.getText());
            productList.add(Float.parseFloat(product.getText().replace("$","")));
        }

        //Khai báo 1 mảng d/s B lấy dữ liệu từ A qua
        ArrayList<Float> sortedList = new ArrayList<Float>();
        for (Float products:productList){
            sortedList.add(products);
        }

        // sort ascending d/s B
        Collections.sort(sortedList);

        // A = B (sau khi đã sort)
        return productList.equals(sortedList);
    }

    public boolean isProductPriceSortDescending() {
        //Lấy ra hết tất cả element chứa Product price
        List<WebElement> productPrice = getListElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);

        //Khai báo 1 mảng d/s A
        ArrayList<Float> productList = new ArrayList<Float>();

        //Dùng vòng lặp lấy Product price text lưu vào d/s A
        System.out.println("Sort price giảm dần");
        for (WebElement product:productPrice){
            System.out.println(product.getText());
            productList.add(Float.parseFloat(product.getText().replace("$","")));
        }

        //Khai báo 1 mảng d/s B lấy dữ liệu từ A qua
        ArrayList<Float> sortedList = new ArrayList<Float>();
        for (Float products:productList){
            sortedList.add(products);
        }

        // sort ascending d/s B
        Collections.sort(sortedList);

        //sort lại B thành Descending
        Collections.reverse(sortedList);

        // A = B (sau khi đã sort)
        return productList.equals(sortedList);
    }
}
