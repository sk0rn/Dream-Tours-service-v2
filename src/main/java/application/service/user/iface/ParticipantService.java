package application.service.user.iface;

import application.domain.Participant;

import java.util.List;

public interface ParticipantService {
    boolean add(Participant participant);

    Participant getById(Integer id);

    List<Participant> getAllByOrderId(Integer orderId);

    boolean updateById(Participant participant);

    boolean deleteById(Integer id);
}
