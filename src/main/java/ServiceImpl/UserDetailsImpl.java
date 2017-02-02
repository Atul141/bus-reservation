package ServiceImpl;

import Dao.UserDetailsDao;

public class UserDetailsImpl {

    private SaveImpl saveImpl;
    public UserDetailsImpl(ConfigDB configDB){
        saveImpl =new SaveImpl(configDB);
    }
    public void saveToDb(UserDetailsDao userDetails) {
        saveImpl.saveToDb(userDetails);


    }

}
