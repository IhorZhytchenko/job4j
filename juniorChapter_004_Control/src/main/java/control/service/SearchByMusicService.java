package control.service;

import control.repository.entity.User;

import java.util.List;
/**
 * class SearchByMusicService.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 30.06.2018
 */
public class SearchByMusicService implements SearchService {
    @Override
    public List<User> search(String condition) {
        return this.service.findUserByMusic(Long.parseLong(condition));
    }
}
