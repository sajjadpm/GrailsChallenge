package my.app

import org.grails.datastore.gorm.GormEntity

class User implements GormEntity<User> {

    String userId;
    String userName;
    Coin coin;

    static hasMany = [coin :Coin]

    static constraints = {
        userName blank: false
    }

   public boolean equals(Object obj){
        User user = (User)obj;
        if (this.userId.equals(user.userId))
            return true;
        else
            return false;
    }
}
