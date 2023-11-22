package com.service.evaluacionSebastianBedoya3.repositorio;




import com.service.evaluacionSebastianBedoya3.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    boolean existsByTitle(String title);
}
