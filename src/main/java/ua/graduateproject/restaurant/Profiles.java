package ua.graduateproject.restaurant;

public class Profiles {
    public static final String DATAJPA = "datajpa";
    public static final String POSTGRES_DB = "postgres";

    //  Get DB profile depending of DB driver in classpath
    static String getActiveDbProfile() {
        try {
            Class.forName("org.postgresql.Driver");
            return POSTGRES_DB;
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Could not find DB driver");
        }
    }
}
