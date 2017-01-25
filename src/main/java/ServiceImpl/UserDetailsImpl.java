package ServiceImpl;

import Dao.UserDetailsDao;

public class UserDetailsImpl {

    private SaveToDb saveToDb;
    public UserDetailsImpl(){
        saveToDb =new SaveToDb();
    }
    public void saveToDb(UserDetailsDao userDetails) {
        saveToDb.saveToDb(userDetails);


    }

}
