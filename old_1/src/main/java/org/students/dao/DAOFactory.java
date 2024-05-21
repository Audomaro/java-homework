package org.students.dao;

import org.students.services.StudentServiceImpl;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;

public class DAOFactory {
    private static final Map<String, Object> objects = new ConcurrentHashMap<>();

    private static final ResourceBundle bundle = ResourceBundle.getBundle("app");
    private static final String profile;
    static {
        profile = bundle.getString("backend.profile");
    }

    public static void clear() {
        objects.clear();
    }

    public static int getObjectCount() {
        return objects.size();
    }

    public static DAO studentDAO() {
        System.out.println("Profile:" + profile);

        return switch(profile) {
            case "dev" -> (DAO)objects.computeIfAbsent("DAO", k -> new InMemoryDAO());
            case "prod" -> (DAO)objects.computeIfAbsent("DAO", k -> new JPADAO());
            default -> throw new RuntimeException("Unknown profile: " + profile);
        };
    }

    public static StudentServiceImpl studentService() {
        return (StudentServiceImpl) objects.computeIfAbsent("studentService",
                        k -> {
                            StudentServiceImpl as = new StudentServiceImpl();
                            DAO dao = studentDAO();
                            as.setDAO(dao);
                            return as;
                        });


    }
}
