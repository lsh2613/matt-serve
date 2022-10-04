package mat.mat_t.web.service;

import lombok.RequiredArgsConstructor;
import mat.mat_t.domain.class_.Wish;
import mat.mat_t.web.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishService {
    private final WishRepository wishRepository;

    public void save(Wish wish){
        wishRepository.save(wish);
    }

    public void delete(Long wishId){
        wishRepository.deleteById(wishId);
    }

    public boolean duplicate(Long classId,Long userId){
        return wishRepository.existsByClassCs_ClassIdAndUserCS_Id(classId,userId);
    }

    public List<Wish> findByUserId(Long userId){
        return wishRepository.findWishByUserCS_Id(userId);
    }

    public void deleteByClassIdAndUserId(Long classId,Long userId){
        wishRepository.deleteWishByClassCs_ClassIdAndUserCS_Id(classId,userId);
    }
}
