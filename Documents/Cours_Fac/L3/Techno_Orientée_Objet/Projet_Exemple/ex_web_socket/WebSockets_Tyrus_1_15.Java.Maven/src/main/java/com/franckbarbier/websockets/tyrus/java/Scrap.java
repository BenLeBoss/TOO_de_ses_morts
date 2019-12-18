/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.franckbarbier.websockets.tyrus.java;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author bob
 */
public class Scrap {
    String url = null;
    String Titre = null;
    
    Scrap(String url){
        this.url = url;
    }

   
    
    Document CreateDoc () throws IOException{
        
        Document page = Jsoup.connect(this.url).userAgent("Jsoup Scrap").get();
        this.Titre = page.title();
        return page;
    }
    
    
    void Parcours() throws IOException{
        Document page = CreateDoc();
        int i=0;
        System.out.println("TITRE DU SITE: "+this.Titre);
        System.out.println("URL DE LA PAGE: "+this.url);
        System.out.println();
        Elements body = page.select("body.MCC");
        Elements chemin = body.select("div#site.fixed.wide div.wrapper.clearfix.row2 div.wrapper-central.widedisplay div.central article#main div ul.prodlist.clearfix.withPromo li section.tagClick div.inner div.description");
        
        for (Element step : chemin){
            String produits = step.select("a.POP_open").text();
            i++;
            System.out.println(i+") " + produits);
     }  
    }
}
