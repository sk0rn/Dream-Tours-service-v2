package application.service.tour.iface;

import application.domain.Album;

public interface AlbumService {

    boolean add(Album album);

    Album getById(Long id);
}
