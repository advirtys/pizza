package com.epam.pizza.util;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validation {
    private HttpServletRequest req;
    private Map<String, String> regexs = new HashMap<>();
    private final String email = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private final String login = "^[A-Za-z]([\\.A-Za-z0-9-]{1,18})([A-Za-z0-9])$";
    private final String password = "^[A-Za-z]([\\.A-Za-z0-9-]{1,18})([A-Za-z0-9])$";

    public Validation(HttpServletRequest req) {
        this.req = req;

        regexs.put("login", login);
        regexs.put("email", email);
        regexs.put("password", password);
    }

    public boolean validate() {
        List<Boolean> checkList = new ArrayList<>();

        for (Map.Entry<String, String> entry : regexs.entrySet()) {
            String parameter = req.getParameter(entry.getKey());
            if (parameter != null) {
                boolean matches = parameter.matches(entry.getValue());
                checkList.add(matches);
            }
        }

        return !checkList.contains(false);
    }


}
