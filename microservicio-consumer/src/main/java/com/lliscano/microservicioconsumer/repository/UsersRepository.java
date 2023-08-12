package com.lliscano.microservicioconsumer.repository;



import com.lliscano.microservicioconsumer.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}
