package application.service.tour;

import application.domain.Album;
import application.repository.AlbumRepository;
import application.service.tour.iface.AlbumService;
import application.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public boolean add(Album album) {
        return ServiceHelper.save(albumRepository, album);
    }

    @Override
    public Album getById(Long id) {
        return ServiceHelper.getById(albumRepository, id);
    }
}
