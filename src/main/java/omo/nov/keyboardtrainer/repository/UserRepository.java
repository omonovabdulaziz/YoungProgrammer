package omo.nov.keyboardtrainer.repository;


import omo.nov.keyboardtrainer.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumber(String username);

    Boolean existsByPhoneNumber(String phoneNumber);

    Page<User> findAllByStatus(Boolean status, PageRequest pageRequest);
    Boolean existsByDeviceIpAndIsBannedTrue(String deviceIp);
Optional<User> findByDeviceIp(String deviceIp);
}
