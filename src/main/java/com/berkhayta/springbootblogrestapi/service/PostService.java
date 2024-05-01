package com.berkhayta.springbootblogrestapi.service;

import com.berkhayta.springbootblogrestapi.dto.request.PostSaveRequestDto;
import com.berkhayta.springbootblogrestapi.dto.request.PostUpdateRequestDto;
import com.berkhayta.springbootblogrestapi.dto.response.PostListAllResponseDto;
import com.berkhayta.springbootblogrestapi.dto.response.PostListByCategoryIDResponseDto;
import com.berkhayta.springbootblogrestapi.dto.response.PostListByUserIDResponseDto;
import com.berkhayta.springbootblogrestapi.dto.response.UserListAllResponseDto;
import com.berkhayta.springbootblogrestapi.entity.Category;
import com.berkhayta.springbootblogrestapi.entity.Post;
import com.berkhayta.springbootblogrestapi.entity.User;
import com.berkhayta.springbootblogrestapi.exceptions.BlogAppException;
import com.berkhayta.springbootblogrestapi.exceptions.ErrorType;
import com.berkhayta.springbootblogrestapi.mapper.PostMapper;
import com.berkhayta.springbootblogrestapi.mapper.UserMapper;
import com.berkhayta.springbootblogrestapi.repository.CategoryRepository;
import com.berkhayta.springbootblogrestapi.repository.PostRepository;
import com.berkhayta.springbootblogrestapi.repository.UserRepository;
import com.berkhayta.springbootblogrestapi.utility.ServiceManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService extends ServiceManager<Post, Long> {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        super(postRepository);
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    //PostSaveRequestDto'yu bir Post nesnesine dönüştüren ve kaydeden metod
    public Post savePost(PostSaveRequestDto dto) {
        // Belirtilen categoryId ile Category nesnesini veritabanından bulur. Bulamazsa hata fırlatır.
        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new BlogAppException(ErrorType.CATEGORY_NOT_FOUND, "Category id bulunamadı: " + dto.categoryId()));

        // Belirtilen userId ile User nesnesini veritabanından bulur. Bulamazsa hata fırlatır.
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new BlogAppException(ErrorType.USER_NOT_FOUND, "User id bulunamadı: " + dto.userId()));

        // Yeni bir Post nesnesi oluşturur ve dto'dan gelen verilerle doldurur.
        Post post = new Post();
        post.setTitle(dto.title());
        post.setContent(dto.content());
        post.setCategory(category);
        post.setAuthor(user);

        // Oluşturulan Post nesnesini veritabanına kaydeder ve kaydedilen nesneyi geri döner.
        return postRepository.save(post);
    }

    public List<PostListAllResponseDto> findAllPostDto() {
        List<PostListAllResponseDto> dtoList = new ArrayList<>();
        findAll().forEach(post -> {
            dtoList.add(PostMapper.INSTANCE.postFindAllResponseDto(post));
        });
        return dtoList;
    }

    public PostListAllResponseDto findPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new BlogAppException(ErrorType.POST_NOT_FOUND));

        return PostMapper.INSTANCE.postFindAllResponseDto(post);
    }

    public PostUpdateRequestDto updatePost(Long id, PostUpdateRequestDto dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new BlogAppException(ErrorType.POST_NOT_FOUND));

        // Kategoriyi bulup günceller
        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new BlogAppException(ErrorType.CATEGORY_NOT_FOUND));
        post.setCategory(category);

        PostMapper.INSTANCE.updatePostFromDto(dto, post);// PostMapper ile Post nesnesini güncelleme işlemi

        Post savedPost = postRepository.save(post);// Güncellenmiş post'u kaydeder

        // Yeni değerlerle DTO oluştur ve döner
        return new PostUpdateRequestDto(savedPost.getTitle(), savedPost.getContent(), savedPost.getCategory().getId());
    }

    public void deletePostById(Long postId) {
        postRepository.deleteById(postId);
    }

    //PostService sınıfında, belirli bir kategoriye ait olan postları bulacak metod.
    //bu metod da PostRepository'den kategorinin postlarını alacak ve her birini PostListDto'ya dönüştürecek.
    public List<PostListByUserIDResponseDto> findPostsByUserId(Long userId) {
        return postRepository.findByAuthorId(userId).stream()
                .map(PostMapper.INSTANCE::postToPostListByUserIDResponseDto)
                .collect(Collectors.toList());
    }

    //PostService sınıfında, belirli bir kategoriye ait olan postları bulacak metod.
    //bu metod da PostRepository'den kategorinin postlarını alacak ve her birini PostListDto'ya dönüştürecek.
    public List<PostListByCategoryIDResponseDto> findPostsByCategoryId(Long categoryId) {
        return postRepository.findByCategoryId(categoryId).stream()
                .map(PostMapper.INSTANCE::postToPostListByCategoryIDResponseDto)
                .collect(Collectors.toList());
    }

    // -------------- LISTING AND FILTERING ---------------------

    //Yazıları yayın tarihine göre sıralama seçeneği (published_at parametresi ile).
    public List<PostListAllResponseDto> findAllPosts(String sortBy) {
        Sort sort = Sort.by(Sort.Direction.ASC, sortBy != null ? sortBy : "publishedAt");
        return postRepository.findAll(sort).stream()
                .map(PostMapper.INSTANCE::postFindAllResponseDto)
                .collect(Collectors.toList());
    }
}
