package ServiceImpl;

import Dao.UserDetailsDao;

public class UserDetailsImpl {

    private SaveToDb saveToDb;
    public UserDetailsImpl(ConfigDB configDB){
        saveToDb =new SaveToDb(configDB);
    }
    public void saveToDb(UserDetailsDao userDetails) {
        saveToDb.saveToDb(userDetails);


    }

}
