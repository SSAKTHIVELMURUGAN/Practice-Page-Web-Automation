package RahulShettyWebAutomation;

import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.NoSuchElementException;


public class PracticePage {
    WebDriver driver; // Declare WebDriver as a class-level field

    public PracticePage(WebDriver driver) {
        this.driver = driver; // Initialize WebDriver through constructor
    }

    public void RadioButtonExample() {
        // Find all radio buttons
        List<WebElement> radioButtons = driver.findElements(By.className("radioButton"));

        // Click each radio button
        for (WebElement radioButton : radioButtons) {
            radioButton.click();
            // You can perform other actions after clicking each radio button if needed
            // For example, you can add a delay to see the effect
            try {
                Thread.sleep(1000); // 1 second delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void SuggessionClassExample() {
        // Find the input field by its ID
        WebElement inputField = driver.findElement(By.id("autocomplete"));

        // Type text into the input field
        inputField.sendKeys("Hi, This Projec to automate and test the website using selenium");

    }

    public void DropdownExample() throws InterruptedException {
        // Find the dropdown menu by its ID
        WebElement dropdownMenu = driver.findElement(By.id("dropdown-class-example"));

        // Create a Select object
        Select dropdown = new Select(dropdownMenu);

        // Select an option by visible text
        dropdown.selectByVisibleText("Option1");

        // Add a delay (1 second)
        Thread.sleep(1000);

        // Select another option by visible text
        dropdown.selectByVisibleText("Option2");

        // Add a delay (1 second)
        Thread.sleep(1000);

        // Select another option by visible text
        dropdown.selectByVisibleText("Option3");

        // Add a delay (1 second)
        Thread.sleep(1000);

        // You can continue this pattern to select other options as needed

    }

    public void CheckboxExample() throws InterruptedException {
        // Find all checkboxes
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));

        // Click each checkbox
        for (WebElement checkbox : checkboxes) {
            checkbox.click();
            // Add a delay to see the effect
            Thread.sleep(1000); // 1 second delay
        }
    }

    public void SwitchWindowExample() {
        // Capture the handle of the parent window
        String parentWindowHandle = driver.getWindowHandle();

        // Click the button to open the new window
        WebElement openWindowButton = driver.findElement(By.id("openwindow"));
        openWindowButton.click();

        // Switch to the new window
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(parentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // Perform actions on the new window
        // For example, close the new window
        driver.close();

        // Switch back to the parent window
        driver.switchTo().window(parentWindowHandle);

    }

    public void SwitchTabExample() {
        // Store the ID of the original tab
        String originalTab = driver.getWindowHandle();

        // Click on the link to open the new tab
        WebElement openTabButton = driver.findElement(By.id("opentab"));
        openTabButton.click();

        // Switch to the newly opened tab
        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(originalTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }

        // Close the newly opened tab
        driver.close();

        // Switch back to the original tab
        driver.switchTo().window(originalTab);

    }

    public void SwitchToAlertExample() {
        // Find the input field and send keys (enter some name)
        WebElement nameInput = driver.findElement(By.id("name"));
        nameInput.sendKeys("John Doe");

        // Click the "Alert" button to trigger an alert
        WebElement alertButton = driver.findElement(By.id("alertbtn"));
        alertButton.click();

        // Switch to the alert and accept it (click OK)
        Alert alert = driver.switchTo().alert();
        alert.accept();

        // Clear the input field
        nameInput.clear();

        // Send new keys (enter a different name)
        nameInput.sendKeys("Jane Smith");

        // Click the "Confirm" button to trigger a confirm alert
        WebElement confirmButton = driver.findElement(By.id("confirmbtn"));
        confirmButton.click();

        // Switch to the confirm alert and accept it (click OK)
        alert = driver.switchTo().alert();
        alert.accept();

    }

    public void ElementDisplayedExample() {
        // Click the "Hide" button to hide the element
        WebElement hideButton = driver.findElement(By.id("hide-textbox"));
        hideButton.click();

        // Verify if the element is hidden
        WebElement textBox = driver.findElement(By.id("displayed-text"));
        if (!textBox.isDisplayed()) {
            System.out.println("Element is hidden");
        } else {
            System.out.println("Element is still visible");
        }

        // Click the "Show" button to show the element
        WebElement showButton = driver.findElement(By.id("show-textbox"));
        showButton.click();

        // Verify if the element is displayed
        if (textBox.isDisplayed()) {
            System.out.println("Element is visible");
        } else {
            System.out.println("Element is still hidden");
        }

    }

    public void WebTableExample() {
        // Retrieve Table Data
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='product']/tbody/tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() >= 3) { // Check if cells list has at least 3 elements
                String instructor = cells.get(0).getText();
                String course = cells.get(1).getText();
                String price = cells.get(2).getText();
                System.out.println("Instructor: " + instructor + ", Course: " + course + ", Price: " + price);
            } else {
                System.out.println("Invalid row data: " + row.getText());
            }
        }

        // Search for Specific Data
        String searchString = "Rahul Shetty"; // Example search term
        for (WebElement row : rows) {
            WebElement instructorCell;
            try {
                instructorCell = row.findElement(By.xpath("./td[1]")); // Modified XPath expression
            } catch (NoSuchElementException e) {
                // Skip to the next row if the instructor cell is not found
                continue;
            }
            if (instructorCell.getText().equals(searchString)) {
                // Perform actions based on the found row
                // Example: Print the details of the course offered by Rahul Shetty
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if (cells.size() >= 3) {
                    String course = cells.get(1).getText();
                    String price = cells.get(2).getText();
                    System.out.println("Course offered by Rahul Shetty: " + course + ", Price: " + price);
                } else {
                    System.out.println("Invalid row data: " + row.getText());
                }
                break; // Exit loop once the first matching row is found
            }
        }

        // Verify Data
        boolean isDataPresent = driver.findElements(By.xpath("//table[@id='product']//td[contains(text(),'Rahul Shetty')]"))
                .size() > 0;
        System.out.println("Is data present in the table: " + isDataPresent);

        // Count Rows/Columns
        int rowCount = rows.size();
        int columnCount = driver.findElements(By.xpath("//table[@id='product']/tbody/tr[1]/td")).size();
        System.out.println("Row count: " + rowCount + ", Column count: " + columnCount);

        // Click on Links
        WebElement courseLink = driver
                .findElement(By.xpath("//table[@id='product']//td[contains(text(),'Selenium Webdriver with Java Basics')]"));
        courseLink.click(); // Example: Clicking on the course link

        // Perform Actions Based on Table Data
        // Example: If the price of a course is below a certain threshold, add it to a wishlist
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() >= 3) {
                String priceText = cells.get(2).getText();
                // Check if the priceText is numeric
                if (priceText.matches("\\d+")) {
                    int price = Integer.parseInt(priceText);
                    if (price <= 30) {
                        // Add the course to the wishlist
                        // For demonstration purposes, let's print a message indicating that the course has been added to the wishlist
                        String courseName = cells.get(1).getText();
                        System.out.println("Course '" + courseName + "' has been added to the wishlist because its price is below 30.");
                        // You can perform other actions here, such as clicking on a wishlist button or storing the course details in a list
                    }
                } else {
                    System.out.println("Invalid price data: " + priceText);
                }
            } else {
                System.out.println("Invalid row data: " + row.getText());
            }
        }

        // Data Validation
        // Example: Calculate the total price of all courses and validate against an expected value
        int totalPrice = 0;
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() >= 3) {
                String priceText = cells.get(2).getText();
                // Check if the priceText is numeric
                if (priceText.matches("\\d+")) {
                    int price = Integer.parseInt(priceText);
                    totalPrice += price;
                } else {
                    System.out.println("Invalid price data: " + priceText);
                }
            } else {
                System.out.println("Invalid row data: " + row.getText());
            }
        }
        int expectedTotalPrice = 180; // Example expected total price
        System.out.println("Total price of all courses: " + totalPrice);
        System.out.println("Is total price valid: " + (totalPrice == expectedTotalPrice));
    }
    public void WebTableFixedHeaderExample() {
        // Retrieve Table Data
        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='tableFixHead']/table/tbody/tr"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() >= 4) { // Check if cells list has at least 4 elements
                String name = cells.get(0).getText();
                String position = cells.get(1).getText();
                String city = cells.get(2).getText();
                String amount = cells.get(3).getText();
                System.out.println(
                        "Name: " + name + ", Position: " + position + ", City: " + city + ", Amount: " + amount);
            } else {
                System.out.println("Invalid row data: " + row.getText());
            }
        }

        // Search for Specific Data
        String searchString = "Chennai"; // Example search term
        for (WebElement row : rows) {
            WebElement cityCell = row.findElement(By.xpath("./td[3]"));
            if (cityCell.getText().equals(searchString)) {
                // Perform actions based on the found row
                // Example: Print the details of employees from Chennai
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if (cells.size() >= 4) {
                    String name = cells.get(0).getText();
                    String position = cells.get(1).getText();
                    String amount = cells.get(3).getText();
                    System.out.println("Employee from Chennai: Name: " + name + ", Position: " + position + ", Amount: "
                            + amount);
                } else {
                    System.out.println("Invalid row data: " + row.getText());
                }
                break; // Exit loop once the first matching row is found
            }
        }

        // Verify Data
        boolean isDataPresent = driver
                .findElements(By.xpath("//div[@class='tableFixHead']//td[contains(text(),'Chennai')]"))
                .size() > 0;
        System.out.println("Is data present in the table: " + isDataPresent);

        // Count Rows/Columns
        int rowCount = rows.size();
        int columnCount = driver.findElements(By.xpath("//div[@class='tableFixHead']/table/thead/tr/th"))
                .size();
        System.out.println("Row count: " + rowCount + ", Column count: " + columnCount);

        // Total Amount Collected
        String totalAmountText = driver.findElement(By.className("totalAmount")).getText();
        int totalAmount = Integer.parseInt(totalAmountText.split(":")[1].trim());
        System.out.println("Total Amount Collected: " + totalAmount);
    }
    
    public void MouseHoverExample() {
        // Find the button to hover over
        WebElement mouseHoverButton = driver.findElement(By.id("mousehover"));

        // Create an instance of Actions class
        Actions actions = new Actions(driver);

        // Perform mouse hover action
        actions.moveToElement(mouseHoverButton).perform();

        // Find and click on the link within the hover content
        WebElement topLink = driver
                .findElement(By.xpath("//div[@class='mouse-hover-content']/a[contains(text(),'Top')]"));
        topLink.click(); // Example action: Clicking on the "Top" link

    }

    public static void main(String[] args) throws InterruptedException {
        // Set the path to the ChromeDriver executable
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the webpage containing the radio buttons
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // Create an instance of the PracticePage class
        PracticePage practicePage = new PracticePage(driver);

       // Call each method to perform respective actions
        practicePage.RadioButtonExample();
        practicePage.SuggessionClassExample();
        practicePage.DropdownExample();
        practicePage.CheckboxExample();
        practicePage.SwitchWindowExample();
        practicePage.SwitchTabExample();
        practicePage.SwitchToAlertExample();
        practicePage.ElementDisplayedExample();
        practicePage.WebTableExample();
        practicePage.WebTableFixedHeaderExample();
       practicePage.MouseHoverExample();

        // Close the browser
        driver.quit();
    }
}
