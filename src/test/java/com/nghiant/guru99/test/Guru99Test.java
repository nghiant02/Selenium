/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.nghiant.guru99.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author nghia
 */
public class Guru99Test {

    private static WebDriver mybrowser;//khai báo biến trỏ vào trình duyệt

    @BeforeAll//hàm này sẽ chạy trc tất cả @Test
    // thường dùng để khởi động 1 điều gì đó
    public static void setUpClass() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        mybrowser = new ChromeDriver();//tạo object trình duyệt trong RAM
        mybrowser.get("https://google.com");
    }

    @AfterAll//hàm này sẽ chạy sau khi tất cả @Test chạy xong
    //thường dùng để dọn dẹp
    public static void tearDownClass() throws InterruptedException {

        Thread.sleep(3000);//app nghỉ trong 3s

        mybrowser.close();//dọn dẹp trình duyệt,tắt trình duyệt
        //dọn rác trong ram luôn (webdriver.exe)
    }

    @Test//bài pe môn JW, thầy yêu cầu login thành công thì chào
    //fullname, với bài BankGuru99, login thành công thì chào 
    //Manager ID: <username login vào>
    public void checkGuru99Login() throws InterruptedException {
        mybrowser.get("https://demo.guru99.com/V4");

        //đi bắt 2 ô nhập user/pass
        //bắt dc 1 tag là 1 object thuộc WebElement
        WebElement user = mybrowser.findElement(By.xpath("//input[@name='uid']"));

        user.sendKeys("mngr510523");

        WebElement pass = mybrowser.findElement(By.name("password"));

        pass.sendKeys("rugarug");

        WebElement login = mybrowser.findElement(By.name("btnLogin"));

        login.click();

        //login mà thành công thì: chuyển trang và say hello
        //trong bài này thì login thành công, trang wellcome trả về 
        //với câu chào: Manger Id : <username>
        //chuyển trang nếu mạng lag, chưa kịp trả trang về trình duyệt
        //mà câu lệnh dưới đã đi tìm tagwelcome ở trong myBrowser
        //sẽ bị báo lỗi ko tìm thấy TAG, do JVM cứ chạy lệnh hoy, ko chờ
        //trình duyệt đổi trang xong!!!
        //chỗ nào chuyển trang, bắt selenium, JVM chờ 1 chút
        //gọi là wait - chờ, để đồng bộ!!!
        Thread.sleep(3000);

        WebElement welcomeMsg = mybrowser.findElement(By.cssSelector("tr[class='heading3'] td"));

        System.out.println("In thử Welcome: " + welcomeMsg.getText());
        //chạy ngon thì in ra dc text từ trang web
        //ta viết đoạn code dùng selenium để lấy data trong trang web
        //code này dc gọi là con bot để cào data crawl (v), crawler (n)

        assertEquals("Manger Id : mngr510523", welcomeMsg.getText());

    }

    public static Object[][] initData() {
        Object[][] account = {
            {"mngr510523", "rugarug"},
            {"mngr510534", "ErYrEvy"}
        };
        return account;
    }

    @ParameterizedTest
    @MethodSource(value = "initData")
    public void checkGuru99LoginDDT(String username, String password) throws InterruptedException {

        mybrowser.get("https://demo.guru99.com/V4");

        //đi bắt 2 ô nhập user/pass
        //bắt dc 1 tag là 1 object thuộc WebElement
        WebElement user = mybrowser.findElement(By.xpath("//input[@name='uid']"));

        user.sendKeys(username);

        WebElement pass = mybrowser.findElement(By.name("password"));

        pass.sendKeys(password);

        WebElement login = mybrowser.findElement(By.name("btnLogin"));

        login.click();

        //login mà thành công thì: chuyển trang và say hello
        //trong bài này thì login thành công, trang wellcome trả về 
        //với câu chào: Manger Id : <username>
        //chuyển trang nếu mạng lag, chưa kịp trả trang về trình duyệt
        //mà câu lệnh dưới đã đi tìm tagwelcome ở trong myBrowser
        //sẽ bị báo lỗi ko tìm thấy TAG, do JVM cứ chạy lệnh hoy, ko chờ
        //trình duyệt đổi trang xong!!!
        //chỗ nào chuyển trang, bắt selenium, JVM chờ 1 chút
        //gọi là wait - chờ, để đồng bộ!!!
        
        Thread.sleep(3000);

        WebElement lblMessgae = mybrowser.findElement(By.cssSelector("tr[class='heading3'] td"));

        System.out.println("In thử Welcome: " + lblMessgae.getText());
        //chạy ngon thì in ra dc text từ trang web
        //ta viết đoạn code dùng selenium để lấy data trong trang web
        //code này dc gọi là con bot để cào data crawl (v), crawler (n)

        assertEquals("Manger Id : " + username, lblMessgae.getText());
    }
}

//FW và thư viện đều là file .jar cung cấp cho dev import using vào gọi xài
//nhưng thu viện thì xài hàm tự do, đặt việc gọi hàm của thư viện vào bất kì chỗ nào
//JDBC xài ở DBUtil hay ở đâu cũng đc

//FW: phải tuân thủ quy tắc viết, cấu trúc code, class dc đưa ra sẵn
//thường là theo các @ đã quy ước, @Test @Before...
//EF Entity Framework .NET nó sinh sẵn cho mình cái DbContext...
//generate database (code first) phải khai báo những thông số
//trong cái Entity Class theo đúng quy ước
//SpringBoot y chang, có quy tắc viết, có quy tắc xài @...

//3 điểm bài thi PE
//yêu cầu bạn viết ra test case để test chức năng login của
//GURU99 BANK

//Test case #1: Check Gur99 login with valid accunt 
//Steps/Procedures:
//  1. Open a certain browser, e.g. Chrome
//  2. Type the following URL: https://demo.guru99.com/V4
//  3. Hit enter to sê the login page 
//  4. Input a vilid account to logn, e.g. mngr510523/rugarug
//  5. Hit the enter to trigger the login process

//  Expected result:
//  3. The login is shown with username/pass/login buttong included
//  5. The Dashboard or Welcome screen i shown with a welcome message
//     is under the format of "Manager id: <username>"


