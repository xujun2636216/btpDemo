package btpEntity;

import java.io.Serializable;

/**
 * @author xujun
 */
public class Cores implements Serializable {
    private int id;
    private int userid;
    private int score;
    private User user;

    public Cores() {}

    public Cores(int id, int userid, int score, User user) {
        this.id = id;
        this.userid = userid;
        this.score = score;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
