package imdb.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    private final ActorDao actorDao;

    @Autowired
    public ActorService(final ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    public boolean add(final String actor) {
        return this.actorDao.add(actor);
    }

}
