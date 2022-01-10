package arg.hero.logAndRegJWT.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import arg.hero.logAndRegJWT.model.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser,Long> {
	
	Optional<AppUser> findByUsername(String username);

}
