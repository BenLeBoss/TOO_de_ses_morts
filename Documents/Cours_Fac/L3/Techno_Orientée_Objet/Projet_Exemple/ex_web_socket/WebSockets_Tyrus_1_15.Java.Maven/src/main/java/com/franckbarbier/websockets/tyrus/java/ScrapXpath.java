/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.franckbarbier.websockets.tyrus.java;

import java.io.IOException;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScrapXpath {
    String url;
    String Title;
    String Products_Name;
    
    ScrapXpath(String url){
        this.url=url;
    }
    ScrapXpath(){
        this.url=null;
        this.Title=null;
        this.Products_Name=null;
    }
    
    void set_Title(FirefoxDriver driver){
        this.Title=driver.getTitle();
    }
    
    String get_Title(){
        return this.Title;
    }
    
    void set_Url(FirefoxDriver driver){
        this.url = driver.getCurrentUrl();
    }
    
    String get_Url(){
        return this.url;
    }
    
    void CreateDoc() throws Exception{
        System.setProperty("webdriver.gecko.driver", "./geckodriver.exe");
        FirefoxDriver driver = new FirefoxDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait (driver, 10);
        
        Thread.sleep(3000);
        driver.get("https://www.casinodrive.fr/ecommerce/prehome/drive");
        
        /*WebElement El1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".nos-drives")));
        El1.click();*/
        
        Thread.sleep(3000);
        driver.findElement(By.cssSelector(".nos-drives")).click();
        
        Thread.sleep(3000);
        driver.findElement(By.id("A64904")).click();
        
        Thread.sleep(3000);
        driver.findElement(By.className("lightbox-close")).click();
        
        Thread.sleep(3000);
        driver.findElement(By.linkText("Produits Casino")).click();
        
        Thread.sleep(10000);
        
        
        
        /*WebElement Title = browser.findElement(By.xpath("//title"));*/
        /*this.Title=driver.getTitle();
        System.out.println("TITRE DE LA PAGE FAIT AVEC LE XPATH: "+Titre);*/
        
        set_Url(driver);
        System.out.println("URL DE LA PAGE: " + this.get_Url());
        set_Title(driver);
        System.out.println("TITRE DE LA PAGE FAIT AVEC LE XPATH: "+this.get_Title());
        Products_Name(driver);
        
       /* Close(driver);*/
    }
    
    void Close(FirefoxDriver driver){
        driver.quit();
    }
    
   
    
    void Products_Name(FirefoxDriver driver){
        List<WebElement> ProductsList = driver.findElements(By.className("description"));
        System.out.println("TAILLE DE LA LISTE :" + ProductsList.size());
        
        for (int i=0;i<=ProductsList.size();i++){
            System.out.println(i+") " +ProductsList.get(i).getText());
        }
    }
    
    void Products_Name_JSOUP(FirefoxDriver driver) throws IOException{
        System.out.println("DANS LE PROG");
        Document page = Jsoup.connect(driver.getCurrentUrl()).userAgent("Jsoup Scrap").get();
        int i=0;
        System.out.println("TITRE DE LA PAGE AVEC JSOUP :" + page.title());
        /*
        Elements pageWeb = page.select("html..js.flexbox body.MCC div#site.fixed.wide div.wrapper.clearfix.row2 div.wrapper-central.widedisplay div.central article#main div.lazyload ul.prodlist.clearfix.withPromo li section..tagClick div.inner div.description");
        
        Elements util = page.select("div.description");
        System.out.println("AVANT LA BOUCLE FOR EACH");
        
        for (Element step : util){
            System.out.println("DANS LA BOUCLE FOR EACH");
            String produits = step.select("a.POP_open").text();
            i++;
            System.out.println(i+") " + produits);
            System.out.println("A LA FIN DE LA BOUCLE FOR EACH");
        } 
        */
    }
}
