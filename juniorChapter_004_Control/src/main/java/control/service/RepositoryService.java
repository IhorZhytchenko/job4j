package control.service;

import control.repository.AddressDao;
import control.repository.MusicTypeDao;
import control.repository.RoleDao;
import control.repository.UserDao;
import control.repository.entity.Address;
import control.repository.entity.MusicType;
import control.repository.entity.Role;
import control.repository.entity.User;

import java.util.List;
/**
 * class RepositoryService.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 30.06.2018
 */
public class RepositoryService {
    private static final RepositoryService INSTANCE = new RepositoryService();
    private final UserDao userDao = new UserDao();
    private final MusicTypeDao musicDao = new MusicTypeDao();
    private final RoleDao roleDao = new RoleDao();
    private final AddressDao addressDao = new AddressDao();

    private RepositoryService() {

    }

    public List<User> findAllUsers() {
        return this.userDao.findAll();
    }

    public User findUserById(long id) {
        User user = this.userDao.findById(id);
        user.setMusicTypes(this.musicDao.findByUserId(id));
        return user;
    }

    public boolean addUser(String firstName, String lastName, String login, String password, String addr, long roleId, String[] musics) {
        boolean result = false;
        if (this.userDao.findByLoginPassword(login, password) == null) {
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setLogin(login);
            user.setPassword(password);
            Address address = new Address();
            address.setAddr(addr);
            user.setAddress(this.addressDao.add(address));
            user.setRole(this.roleDao.findById(roleId));
            user = this.userDao.add(user);
            for (String musicId : musics) {
                this.musicDao.addUserMusic(user.getId(), Long.parseLong(musicId));
            }
            result = true;
        }
        return result;
    }

    public void deleteUser(long id) {
        long addressId = this.userDao.findById(id).getAddress().getId();
        this.musicDao.deleteByUserId(id);
        this.userDao.delete(id);
        this.addressDao.delete(addressId);
    }

    public void updateUser(String firstName, String lastName, String login, String password, String addr, long roleId, String[] musics, long id) {
        boolean updateAddr = false;
        long oldAddrId = 0;
        User user = this.findUserById(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setPassword(password);
        if (user.getRole().getId() != roleId) {
            user.setRole(this.roleDao.findById(roleId));
        }

        if (!user.getAddress().getAddr().equals(addr)) {
            updateAddr = true;
            oldAddrId = user.getAddress().getId();
            Address address = new Address();
            address.setAddr(addr);
            user.setAddress(this.addressDao.add(address));
        }


        this.userDao.update(user);
        if (updateAddr) {
            this.addressDao.delete(oldAddrId);
        }

        this.musicDao.deleteByUserId(id);
        for (String musicId: musics) {
            this.musicDao.addUserMusic(user.getId(), Long.parseLong(musicId));
        }
    }

    public List<User> findUserByRole(long roleId) {
        return this.userDao.findByRole(roleId);
    }
    public List<User> findUserByMusic(long musicId) {
        return this.userDao.findByMusicType(musicId);
    }
    public List<User> findUserByAddress(String address) {
        return this.userDao.findByAddress(address);
    }

    public List<MusicType> findAllMusicType() {
        return this.musicDao.findAll();
    }

    public void addMusicType(String type) {
        MusicType music = this.musicDao.findByType(type);
        if (music == null) {
            music = new MusicType();
            music.setType(type);
            this.musicDao.add(music);
        }
    }
    public void deleteMusicType(long id) {
        if (this.userDao.findByMusicType(id).size() == 0) {
            this.musicDao.delete(id);
        }
    }

    public MusicType findMusicTypeById(long id) {
        return this.musicDao.findById(id);
    }

    public List<Role> findAllRoles() {
        return this.roleDao.findAll();
    }



    public static RepositoryService getInstance() {
        return INSTANCE;
    }

    public User signin(String login, String password) {
        User user = this.userDao.findByLoginPassword(login, password);
        user.setMusicTypes(this.musicDao.findByUserId(user.getId()));
        return user;
    }
}
