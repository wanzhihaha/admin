package main.java.com.cellosquare.adminapp.common.util;

import com.bluewaves.lab.message.XmlMessageManager;
import org.springframework.ui.Model;

import java.util.Map;

public class CommonMessageModel {
    public static String setModel(Model model, Map<String, String> hmParam) {
        model.addAttribute("msg_type", ":submit");
        model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
        model.addAttribute("url", "./list.do");
        model.addAttribute("hmParam", hmParam);
        return "admin/common/message";
    }

    public static String setModel(String msg, String url,String method, Model model, Map<String, String> hmParam) {
        model.addAttribute("msg_type", ":submit");
        model.addAttribute("msg", msg);
        model.addAttribute("url", url);
        model.addAttribute("method", method);
        model.addAttribute("hmParam", hmParam);
        return "admin/common/message";
    }
}
