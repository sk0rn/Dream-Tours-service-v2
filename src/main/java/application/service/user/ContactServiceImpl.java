package application.service.user;

import application.domain.Contact;
import application.domain.User;
import application.repository.ContactRepository;
import application.repository.UserRepository;
import application.service.user.iface.ContactService;
import application.utils.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }

    @Override
    public boolean add(Contact contact) {
        return ServiceHelper.save(contactRepository, contact);
    }

    @Override
    public boolean checkForMatch(String value) {
        return contactRepository.existsByValue(value);
    }

    @Override
    public List<Contact> getAllByUserId(Long userId) {
        User user;
        return (user = ServiceHelper.getById(userRepository, userId)) != null ?
                new ArrayList<>(user.getContacts()) :
                Collections.emptyList();
    }

    @Override
    public boolean updateById(Contact contact) {
        return ServiceHelper.save(contactRepository, contact);
    }

    @Override
    public boolean deleteById(Long id) {
        return ServiceHelper.delete(contactRepository, id);
    }
}
