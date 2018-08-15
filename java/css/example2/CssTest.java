package com.njust.test.css;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSStyleSheet;

import com.steadystate.css.dom.CSSRuleListImpl;
import com.steadystate.css.dom.CSSStyleDeclarationImpl;
import com.steadystate.css.dom.CSSStyleRuleImpl;
import com.steadystate.css.dom.CSSStyleSheetImpl;
import com.steadystate.css.format.CSSFormat;
import com.steadystate.css.parser.CSSOMParser;

public class CssTest
{
    @Test
    public void readAndWrite()
        throws IOException
    {
        String filePath = "E:/commonltr.css";

        FileInputStream fileInputStream = new FileInputStream(filePath);
        InputSource source = new InputSource(new InputStreamReader(fileInputStream));
        CSSOMParser parser = new CSSOMParser();
        CSSStyleSheetImpl stylesheet = (CSSStyleSheetImpl)parser.parseStyleSheet(source, null, null);

        CSSRuleListImpl cssRules = (CSSRuleListImpl)stylesheet.getCssRules();
        List<CSSRule> rules = cssRules.getRules();
        for (CSSRule rule : rules)
        {
            //java.lang.ClassCastException: com.steadystate.css.dom.CSSMediaRuleImpl cannot be cast to com.steadystate.css.dom.CSSStyleRuleImpl
            if (rule instanceof CSSStyleRuleImpl)
            {
                CSSStyleRuleImpl styleRule = (CSSStyleRuleImpl)rule;
                CSSStyleDeclarationImpl style = (CSSStyleDeclarationImpl)styleRule.getStyle();

                String background = style.getBackground();
                if (!background.isEmpty())
                {
                    System.out.println(background);
                    String target = background.replaceAll("url\\((?<name>.+)\\)", "url(@@@${name})");
                    style.setBackground(target);
                }

                String backgroundImage = style.getBackgroundImage();
                if (!backgroundImage.isEmpty())
                {
                    System.out.println(backgroundImage);
                    String target = backgroundImage.replaceAll("url\\((?<name>.+)\\)", "url(@@@${name})");
                    style.setBackgroundImage(target);
                }
            }
        }

        //write to new file
        String cssText =
            stylesheet.getCssText(new CSSFormat().setPropertiesInSeparateLines(4).setUseSourceStringValues(true));
        FileOutputStream fileOutputStream = new FileOutputStream("e:/new.css");
        fileOutputStream.write(cssText.getBytes());
        fileInputStream.close();
    }

    @Test
    public void changeStyle()
    {
        String style1 = "url(icon-arrowrt.gif) 0px 2px no-repeat";
        String style2 = "url(icon-arrowdn.gif) 0px 4px no-repeat";
        String style3 = "url(icon-cc.png)";

        for (String source : Arrays.asList(style1, style2, style3))
        {
            String target = source.replaceAll("url\\((?<name>.+)\\)", "url(@@@${name})");
            System.out.println(source);
            System.out.println(target);
        }

        //console
        //        url(icon-arrowrt.gif) 0px 2px no-repeat
        //        url(@@@icon-arrowrt.gif) 0px 2px no-repeat
        //        url(icon-arrowdn.gif) 0px 4px no-repeat
        //        url(@@@icon-arrowdn.gif) 0px 4px no-repeat
        //        url(icon-cc.png)
        //        url(@@@icon-cc.png)
    }

}


