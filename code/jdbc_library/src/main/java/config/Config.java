package config;

public class Config {

    private String url; //jdbc:postgresql://<database_host>:<port>/<database_name>
    private String dbName; //db name
    private String user; //db user name
    private String password; //db user password

    public Config(String dbName, String url, String user, String password) {
        this.dbName = dbName;
        this.user = user;
        this.password = password;
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }

    public String getUrl() {
        return url;
    }

    public String getDbName() {
        return dbName;
    }
}
