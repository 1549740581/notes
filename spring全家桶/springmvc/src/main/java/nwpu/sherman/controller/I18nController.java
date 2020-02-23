package nwpu.sherman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

/**
 * @author sherman
 */

@Controller
public class I18nController {
    @Autowired
    MessageSource messageSource;

    @ResponseBody
    @RequestMapping("/i18n")
    public String i18n(Locale locale) {
        /**
         * 为浏览器切换不同语言，输出不同国际化密码
         */
        String res = messageSource.getMessage("i18n.password", null, locale);
        System.out.println(res);
        return "i18n";
    }

    /**
     * 该Controller处理两个超链接，这两个超链接携带了locale参数
     */
    @RequestMapping("/i18n/change")
    public String i18nChange( ){
        return "success";
    }
}
