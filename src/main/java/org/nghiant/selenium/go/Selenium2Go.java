/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.nghiant.selenium.go;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author nghia
 */
public class Selenium2Go {

    public static void main(String[] args) {
        playWithGoogle();
    }

    //mình sẽ search google bằng code
    //test case #1:Check Google function
    //step:
    //      1.Open a certain browser, e.g. Chrome
    //      2.Type https://google.com
    //      3.Hit enter to open google search page
    //      4.Input "Trang nemo" in search box
    //      5.Hit enter to activate the search engine
    //Excepted:
    //          All page with "Trang nemo" keyword included are shown
    //TẤT CẢ CÁC HÀNH ĐỘNG TRÊN NẾU LÀM BẰNG TAY THÌ GỌI LÀ MANUAL TESTING
    //NẾU TỰ ĐỘNG HÓA DC, VIẾT = CODE ĐỂ TỰ ĐỘNG HÓA
    //                  HOẶC XÀI TOOL SELENIUM IDE, KATALONE, RANOREX
    //                                  TEST COMPLETE, TELERIK, AKA TEST (FPT)
    //CODE DÙNG ĐỂ TỰ ĐỘNG HÓA1 TEST CASE ĐC GỌI LÀ TEST SCRIPT
    public static void playWithGoogle() {

        WebDriver myBrowser;// khai báo biến trỏ vào trình duyệt

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        //khai báo thông tin móc vào trình điều khiển trình duyệt
        //gọi là ChromeDriver (kiểu JDBC); coi Browser là CSDL vì chứa tags
        //                                                      xem như data/table

        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--incognito");//ẩn danh
        opt.addArguments("--lang=zh-en-GB");//ngôn ngữ

        myBrowser = new ChromeDriver(opt);//tạo object trình duyệt trong RAM

        myBrowser.get("https://www.youtube.com/");

        myBrowser.manage().window().maximize();//bung  full cửa sổ

        //trình duyệt là 1 object, mình thoải mái chấm để làm gì đó
        //tìm ô search để đưa data vào
        //ta cần bắt 1 tag nào dó trên trang  web đang nắm trong biến myBrower
        //mỗi tag trong trang web là 1 UI object, thuộc class WebElement
        WebElement searchBox = myBrowser.findElement(By.xpath("//input[@id='search']"));

        searchBox.sendKeys("rick roll");

        searchBox.submit();

        WebElement clkSearch = myBrowser.findElement(By.xpath("//button[@id='search-icon-legacy']"));

        clkSearch.click();

        WebElement clkVideo = myBrowser.findElement(By.xpath("//body/ytd-app/div[@id='content']/ytd-page-manager[@id='page-manager']/ytd-search[@role='main']/div[@id='container']/ytd-two-column-search-results-renderer[@class='style-scope ytd-search']/div[@id='primary']/ytd-section-list-renderer[@class='style-scope ytd-two-column-search-results-renderer']/div[@id='contents']/ytd-item-section-renderer[@class='style-scope ytd-section-list-renderer']/div[@id='contents']/ytd-video-renderer[1]/div[1]/ytd-thumbnail[1]/a[1]/yt-image[1]/img[1]"));

        clkVideo.click();

    }
}
