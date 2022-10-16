package com.waracle.cakemgr.util;

import com.waracle.cakemgr.model.Cake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CakeManagerUtil {

    public String getCakesAsHtmlString(List<Cake> cakes) {
        String content = "<html><body><h2>Cake List</h2>";
        for (Cake cake : cakes) {
            content += "<div><h4>" + cake.getTitle() + "</h4>" +
                    "<img src=\"" + cake.getImage() + "\" alt=\"" + cake.getDesc() + "\">\n" +
                    "</div>";
        }
        content += "</body></html>";
        log.info("HTML content :{}", content);
        return content;
    }
}
