package imdb.actor.service;

import imdb.actor.dao.ActorDao;
import imdb.actor.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    private final ActorDao actorDao;

    @Autowired
    public ActorService(final ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    public boolean add(final Actor actor) {
        return this.actorDao.add(actor);
    }

    public Actor findByName(final String name) {
        return this.actorDao.findByName(name);
    }
}
