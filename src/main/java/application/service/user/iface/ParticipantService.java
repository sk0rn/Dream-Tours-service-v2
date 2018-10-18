package application.service.user.iface;

import application.domain.Participant;

import java.util.List;

public interface ParticipantService {
    boolean add(Participant participant);

    Participant getById(Long id);

    List<Participant> getAllByOrderId(Long orderId);

    boolean updateById(Participant participant);

    boolean deleteById(Long id);
}
