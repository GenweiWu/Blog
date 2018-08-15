package com.njust.test.css;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSStyleSheet;

import com.steadystate.css.dom.CSSRuleListImpl;
import com.steadystate.css.dom.CSSStyleDeclarationImpl;
import com.steadystate.css.dom.CSSStyleRuleImpl;
import com.steadystate.css.parser.CSSOMParser;

public class CssTest
{
    @Test
    public void readTest()
        throws IOException
    {
        String filePath = "E:\\commonltr.css";

        FileInputStream fileInputStream = new FileInputStream(filePath);
        InputSource source = new InputSource(new InputStreamReader(fileInputStream));
        CSSOMParser parser = new CSSOMParser();
        CSSStyleSheet stylesheet = parser.parseStyleSheet(source, null, null);

        CSSRuleListImpl cssRules = (CSSRuleListImpl)stylesheet.getCssRules();
        List<CSSRule> rules = cssRules.getRules();
        for (CSSRule rule : rules)
        {
            CSSStyleRuleImpl styleRule = (CSSStyleRuleImpl)rule;
            CSSStyleDeclarationImpl style = (CSSStyleDeclarationImpl)styleRule.getStyle();

            String background = style.getBackground();
            if (!background.isEmpty())
            {
                System.out.println(background);
            }

            String backgroundImage = style.getBackgroundImage();
            if (!backgroundImage.isEmpty())
            {
                System.out.println(backgroundImage);
            }
        }

    }

}


//    url(icon-arrowrt.gif) 0px 2px no-repeat
//    url(icon-arrowdn.gif) 0px 4px no-repeat
//    url(icon-cc.png)

