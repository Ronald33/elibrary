package com.ronald.elibrary.helper;

import java.util.HashMap;
import java.util.Map;

public abstract class Success
{
    private static Map get(Map map)
    {
        map.put("success", true);
        return map;
    }
    
    public static Map Redirect(String url)
    {
        Map map = new HashMap();
        map.put("url", url);
        return get(map);
    }
    
    public static Map Status()
    {
        Map map = new HashMap();
        return get(map);
    }
}