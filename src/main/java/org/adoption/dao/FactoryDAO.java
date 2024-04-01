package org.adoption.dao;

import org.adoption.services.AdopterService;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

public class FactoryDAO {
    private static final Map<String, Object> objects = new ConcurrentHashMap<>();

    private static final ResourceBundle bundle = ResourceBundle.getBundle("app");
    private static String profile;
    static {
        profile = bundle.getString("backend.profile");
    }

    public static void clear() {
        objects.clear();
    }

    public static int getObjectCount() {
        return objects.size();
    }

    public static AdopterDAO adopterDAO() {
        System.out.printf("===== [ Profile used: %s ] =====%n", profile.toUpperCase());

        return switch(profile) {
            case "dev" -> (AdopterDAO)objects.computeIfAbsent("DAO", k -> new InMemoryDAO());
            case "prod" -> (AdopterDAO)objects.computeIfAbsent("DAO", k -> new JPADAO());
            default -> throw new RuntimeException("Unknown profile: " + profile);
        };
    }

    private static AdopterService adopterService() {
        return (AdopterService) objects.computeIfAbsent("adopterService",
                k -> {
                    AdopterService as = new AdopterService();

                    AdopterDAO dao = adopterDAO();

                    as.setDAO(dao);
                    return as;
                });
    }
}
